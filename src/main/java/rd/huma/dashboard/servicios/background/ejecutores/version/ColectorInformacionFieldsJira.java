package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.Set;

import rd.huma.dashboard.model.jira.AJiraPerson;
import rd.huma.dashboard.model.jira.Creator;
import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.ETipoParticipante;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;

public class ColectorInformacionFieldsJira {

	private Fields fields;
	private Set<String> duenos;
	private Set<EntJiraParticipante> participantes;
	private Set<EntVersionScript> scripts;
	private Set<EntTicketSysAid> ticketSysAids;
	private EntJira jira;
	private Set<String> reportes;

	public ColectorInformacionFieldsJira(Fields fields, Set<String> duenos,
			Set<EntJiraParticipante> participantes,
			Set<EntVersionScript> scripts, EntJira jira,
			Set<EntTicketSysAid> ticketSysAids, Set<String> reportes) {
		this.fields = fields;
		this.duenos = duenos;
		this.participantes = participantes;
		this.scripts = scripts;
		this.jira = jira;
		this.ticketSysAids = ticketSysAids;
		this.reportes = reportes;
	}


	public void procesar(){
		nuevoTicketSysAid(fields.getSysAidTicket());
		adicionarParticipante(fields.getAssignee(), ETipoParticipante.ASIGNADO);

		adicionarParticipante(fields.getReporter(), ETipoParticipante.REPORTADOR);


		Creator creator = fields.getCreator();
		if (creator!=null && !"pds".equals(creator.getName())){
			adicionarParticipante(fields.getCreator(), ETipoParticipante.CREADOR);
		}
		adicionarScript(fields.getScriptAntesSubida(), ETipoScript.ANTES_SUBIDA);
		adicionarScript(fields.getScriptDespuesSubida(), ETipoScript.DESPUES_SUBIDA);
		adicionarReportes(fields.getReportes());

		jira.setEstado(fields.getStatus().getStatusCategory().getName());
	}

	private void adicionarReportes(String grupoReportes) {
		if (grupoReportes == null){
			return;
		}
		for (String rep : grupoReportes.split(".rdf")) {
			this.reportes.add(rep+".rdf");
		}
	}




	private void adicionarScript(String datoScript, ETipoScript tipoScript){
		if (datoScript == null){
			return;
		}

		EntVersionScript script = new EntVersionScript();
		script.setTipoScript(tipoScript);
		script.setUrlScript(datoScript);
		script.setJira(jira);
		scripts.add(script);
	}

	private void adicionarParticipante(AJiraPerson person, ETipoParticipante tipoParticipante){
		if (person == null){
			return;
		}

		duenos.add(person.getName());
		EntJiraParticipante participante = new EntJiraParticipante();
		participante.setJira(jira);
		EntPersona persona =  new EntPersona();
		persona.setCorreo(person.getEmailAddress());
		persona.setUsuarioSvn(person.getName());
		participante.setTipo(tipoParticipante);
		participante.setParticipante(persona);
		participantes.add(participante);
	}


	private void nuevoTicketSysAid(String numero){
		if (numero == null){
			return;
		}

		EntTicketSysAid ticketSysAid = new EntTicketSysAid();
		ticketSysAid.setNumero(numero);
		this.ticketSysAids.add(ticketSysAid);
	}
}
