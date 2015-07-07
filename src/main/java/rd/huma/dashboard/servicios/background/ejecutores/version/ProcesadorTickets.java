package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import rd.huma.dashboard.model.jira.Assignee;
import rd.huma.dashboard.model.jira.Creator;
import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;

public class ProcesadorTickets {
	private EntConfiguracionGeneral configuracionGeneral;
	private EntVersion version;
	private EntAplicacion aplicacion;
	private BuscadorJiraRestApi buscadorJiraQuery;
	private Set<EntTicketSysAid> ticketSysAid = new TreeSet<>();
	private List<EntJira> jiras = new ArrayList<>();
	private Set<EntJira> paraEncontrarInformacionJira = new TreeSet<>();
	private Set<String> duenos = new TreeSet<>();

	private ProcesadorTickets(EntConfiguracionGeneral configuracionGeneral,EntVersion version, EntAplicacion aplicacion) {
		this.configuracionGeneral = configuracionGeneral;
		this.version = version;
		this.aplicacion = aplicacion;
	}

	public ProcesadorTickets procesaJiras(){
		List<EntJira> jirasEncontradoComentarios = BuscadorJiraEnComentario.of(version.getComentario(), aplicacion.getJiraKey()).encuentraJira();
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
		collectInformationTicket(jira);
	}

	private void buscarInformacionJira(EntJira jira){
		new BuscadorJiraRestApi(new JiraQuery(configuracionGeneral, ETipoQueryJira.KEY, jira.getNumero())).getIssues().stream().forEach( i-> {collectInformationTicket(jira, i); });
	}

	private void collectInformationTicket(EntJira jira){

		Optional<Issues> issueFound = buscadorJiraQuery.getIssues().stream().filter(j -> j.getKey().equals(jira.getNumero())).findFirst();
		if (issueFound.isPresent()){
			collectInformationTicket(jira,issueFound.get());
		}else{
			paraEncontrarInformacionJira.add(jira);
		}
		jiras.add(jira);
	}

	private void collectInformationTicket(EntJira jira,Issues issues){
		Fields fields = issues.getFields();
		ticketSysAid.add(nuevoTicketSysAid(fields.getSysAidTicket()));
		Assignee asignado = fields.getAssignee();
		if (asignado!=null){
			duenos.add(asignado.getName());
		}

		duenos.add(fields.getReporter().getName());
		Creator creator = fields.getCreator();
		if (creator!=null && !"pds".equals(creator.getName())){
			duenos.add(creator.getName());
		}

		jira.setEstado(issues.getFields().getStatus().getStatusCategory().getName());
	}

	private EntTicketSysAid nuevoTicketSysAid(String numero){
		EntTicketSysAid ticketSysAid = new EntTicketSysAid();
		ticketSysAid.setNumero(numero);
		return ticketSysAid;
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

	Set<EntTicketSysAid> getTicketSysAid() {
		return ticketSysAid;
	}
}