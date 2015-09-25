package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.jira.Subtasks;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionReporteJira;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.EntVersionScriptJira;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraEnComentario;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.SVNOrigenBranch;
import rd.huma.dashboard.servicios.integracion.svn.util.ServicioSvnBuscaOrigenBranch;

public class ProcesadorTickets {
	private EntConfiguracionGeneral configuracionGeneral;
	private EntVersion version;
	private EntAplicacion aplicacion;
	private BuscadorJiraRestApi buscadorJiraQuery;
	private EntBranch branch;
	private Set<EntTicketSysAid> ticketSysAid = new TreeSet<>();
	private List<EntJira> jiras = new ArrayList<>();
	private Set<EntJira> paraEncontrarInformacionJira = new TreeSet<>();
	private Set<String> duenos = new TreeSet<>();
	private Set<EntJiraParticipante> participantes = new TreeSet<>();
	private Map<String, List<EntVersionReporteJira>> reportes = new HashMap<>();
	private Map<EntVersionScript, List<EntVersionScriptJira>> scripts = new  TreeMap<>( new Comparator<EntVersionScript>() {

		@Override
		public int compare(EntVersionScript o1, EntVersionScript o2) {
			return o1.getUrlScript().compareTo(o2.getUrlScript());
		}
	});


	private ProcesadorTickets(EntConfiguracionGeneral configuracionGeneral,EntVersion version, EntAplicacion aplicacion) {
		this.configuracionGeneral = configuracionGeneral;
		this.version = version;
		this.aplicacion = aplicacion;
	}

	public ProcesadorTickets procesaJiras(){
		EjecutorVersion.LOGGER.info("Buscando los jiras el jira en el comentario de la version :" + version.getNumero());
		List<EntJira> jirasEncontradoComentarios = new ArrayList<>();
		try{
			SVNOrigenBranch origen = new ServicioSvnBuscaOrigenBranch(ServicioSVN.para(aplicacion).toBranchCompleto(version.getBranchOrigen()), aplicacion.getJiraKey(),version.getBranchOrigen()).getOrigen();
			jirasEncontradoComentarios.addAll(origen.getJiras());

			branch = new EntBranch();
			branch.setAplicacion(aplicacion);
			branch.setBranch(version.getBranchOrigen());
			branch.setOrigen(origen.getOrigenBranch());
			branch.setRevisionOrigen(origen.getRevision());
		}catch(Exception e){
			e.printStackTrace();
			EjecutorVersion.LOGGER.warning("No se pudo interpretar el origen");
		}

		jirasEncontradoComentarios.addAll(BuscadorJiraEnComentario.of(version.getComentario(), aplicacion.getJiraKey()).encuentraJira());
		this.buscadorJiraQuery =  new BuscadorJiraRestApi(new JiraQuery(configuracionGeneral, ETipoQueryJira.BRANCH, version.getBranchOrigen()));


		List<EntJira> jirasEncontradosBranches = buscadorJiraQuery.encuentra();
		List<EntJira> todos = new ArrayList<>();
		todos.addAll(jirasEncontradoComentarios);
		todos.addAll(jirasEncontradosBranches);
		todos.stream().distinct().forEach(this::procesarJira);

		paraEncontrarInformacionJira.stream().forEach(this::buscarInformacionJira);
		return this;
	}

	private void procesarJira(EntJira jira){
		Optional<Issues> issueFound = buscadorJiraQuery.getIssues().stream().filter(j -> j.getKey().equals(jira.getNumero())).findFirst();
		if (issueFound.isPresent()){
			collectInformationTicket(jira,issueFound.get());
		}else{
			paraEncontrarInformacionJira.add(jira);
		}
	}

	private void buscarInformacionJira(EntJira jira){
		try{
			new BuscadorJiraRestApi(new JiraQuery(configuracionGeneral, ETipoQueryJira.KEY, jira.getNumero())) .getIssues().stream().forEach( i-> collectInformationTicket(jira, i));
		}catch(Exception e){
			EjecutorVersion.LOGGER.warning("Buscando el Jira " + jira.getNumero() + " dio problemas" );
			e.printStackTrace();
		}
	}

	private void collectInformationTicket(EntJira jira,Issues issue){
		if (jiras.stream().filter(j -> jira.getNumero().equals(j.getNumero())).collect(Collectors.counting())==0){
			jiras.add(jira);
		}

		Fields fields = issue.getFields();
		new ColectorInformacionFieldsJira(fields, duenos, participantes, scripts, jira, ticketSysAid,reportes).procesar();

		if (fields.getSubtasks()==null){
			return;
		}

		for(Subtasks subTask : issue.getFields().getSubtasks()){

			EntJira jiraSubTask =  jiras.stream().filter(j -> j.getNumero().equals(subTask.getKey())).findFirst().orElse(new EntJira());
			if (jiraSubTask.getNumero() == null){
				jiraSubTask.setNumero(subTask.getKey());
				jiras.add(jiraSubTask);
			}

			buscarInformacionJira(jiraSubTask);
		}
	}

	public static ProcesadorTickets of(EntConfiguracionGeneral configuracionGeneral,EntVersion version, EntAplicacion aplicacion){
		return new ProcesadorTickets(configuracionGeneral, version, aplicacion);
	}

	public EntVersion getVersion() {
		return version;
	}

	public Iterable<EntJira> getJiras() {
		return jiras;
	}

	public Set<String> getDuenos() {
		return new HashSet<>(duenos);
	}

	public Set<EntJiraParticipante> getParticipantes() {
		return participantes;
	}

	Set<EntTicketSysAid> getTicketSysAid() {
		return ticketSysAid;
	}

	public Map<EntVersionScript, List<EntVersionScriptJira>> getScripts() {
		return scripts;
	}

	public EntBranch getBranch() {
		return branch;
	}

	Map<String, List<EntVersionReporteJira>> getReportes() {
		return reportes;
	}
}