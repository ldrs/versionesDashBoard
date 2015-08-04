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
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.ETipoParticipante;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
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
	private Set<EntJiraParticipante> participantes = new TreeSet<>();
	private Set<EntVersionScript> scripts = new  HashSet<>();

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
		nuevoTicketSysAid(fields.getSysAidTicket());
		Assignee asignado = fields.getAssignee();
		if (asignado!=null){
			duenos.add(asignado.getName());
			EntJiraParticipante participante = new EntJiraParticipante();
			participante.setJira(jira);
			EntPersona persona =  new EntPersona();
			persona.setCorreo(asignado.getEmailAddress());
			persona.setUsuarioSvn(asignado.getName());
			participante.setTipo(ETipoParticipante.ASIGNADO);
			participante.setParticipante(persona);
			participantes.add(participante);
		}

		duenos.add(fields.getReporter().getName());
		Creator creator = fields.getCreator();
		if (creator!=null && !"pds".equals(creator.getName())){
			duenos.add(creator.getName());


			EntJiraParticipante participante = new EntJiraParticipante();
			participante.setJira(jira);
			EntPersona persona =  new EntPersona();
			persona.setCorreo(creator.getEmailAddress());
			persona.setUsuarioSvn(creator.getName());
			participante.setTipo(ETipoParticipante.REPORTADOR);
			participante.setParticipante(persona);
			participantes.add(participante);
		}
		if (fields.getScriptAntesSubida()!=null){
			EntVersionScript script = new EntVersionScript();
			script.setTipoScript(ETipoScript.ANTES_SUBIDA);
			script.setUrlScript(fields.getScriptAntesSubida());
			scripts.add(script);
		}

		if (fields.getScriptDespuesSubida()!=null){
			EntVersionScript script = new EntVersionScript();
			script.setTipoScript(ETipoScript.DESPUES_SUBIDA);
			script.setUrlScript(fields.getScriptDespuesSubida());
			scripts.add(script);
		}
		jira.setEstado(issues.getFields().getStatus().getStatusCategory().getName());
	}

	private void nuevoTicketSysAid(String numero){
		if (numero == null){
			return;
		}

		EntTicketSysAid ticketSysAid = new EntTicketSysAid();
		ticketSysAid.setNumero(numero);
		this.ticketSysAid.add(ticketSysAid);
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

	public Set<EntVersionScript> getScripts() {
		return scripts;
	}
}