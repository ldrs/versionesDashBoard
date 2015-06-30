package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntJira;
import rd.huma.dashboard.model.EntTicketSysAid;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.jira.Issues;
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
		new BuscadorJiraRestApi(new JiraQuery(configuracionGeneral, ETipoQueryJira.KEY, jira.getNumero())).getIssues().stream().forEach(this::collectInformationTicket);
	}

	private void collectInformationTicket(EntJira jira){

		Optional<Issues> issueFound = buscadorJiraQuery.getIssues().stream().filter(j -> j.getKey().equals(jira.getNumero())).findFirst();
		if (issueFound.isPresent()){
			collectInformationTicket(issueFound.get());
		}else{
			paraEncontrarInformacionJira.add(jira);
		}
		jiras.add(jira);
	}

	private void collectInformationTicket(Issues issues){
		Fields fields = issues.getFields();
		ticketSysAid.add(nuevoTicketSysAid(fields.getSysAidTicket()));
		duenos.add(fields.getAssignee().getName());
		duenos.add(fields.getReporter().getName());
		duenos.add(fields.getCreator().getName());
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

}