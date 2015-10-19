package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rd.huma.dashboard.model.jira.AJiraPerson;
import rd.huma.dashboard.model.jira.Creator;
import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersionReporteJira;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.EntVersionScriptJira;
import rd.huma.dashboard.model.transaccional.dominio.ETipoParticipante;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.integracion.svn.util.ServicioUltimaRevisionSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.UltimaRevision;

public class ColectorInformacionFieldsJira {

	private Fields fields;
	private Set<String> duenos;
	private Set<EntJiraParticipante> participantes;
	private Map<EntVersionScript, List<EntVersionScriptJira>> scripts;
	private Set<EntTicketSysAid> ticketSysAids;
	private EntJira jira;
	private Map<String, List<EntVersionReporteJira>> reportes;

	public ColectorInformacionFieldsJira(Fields fields, Set<String> duenos,
			Set<EntJiraParticipante> participantes,
			Map<EntVersionScript, List<EntVersionScriptJira>>scripts, EntJira jira,
			Set<EntTicketSysAid> ticketSysAids, Map<String, List<EntVersionReporteJira>> reportes) {
		this.fields = fields;
		this.duenos = duenos;
		this.participantes = participantes;
		this.scripts = scripts;
		this.jira = jira;
		this.ticketSysAids = ticketSysAids;
		this.reportes = reportes;
	}


	public void procesar(){
		String numero = fields.getSysAidTicket();
		if (numero!=null){
			nuevoTicketSysAid(Integer.valueOf(numero));
		}
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
			String urlReporte = rep+".rdf";
			if (existeReporte(urlReporte)){
				List<EntVersionReporteJira> reportes = this.reportes.get(urlReporte);
				if (reportes==null){
					reportes = new ArrayList<>();
					this.reportes.put(urlReporte, reportes);
				}
				EntVersionReporteJira versionReporteJira = new EntVersionReporteJira();
				versionReporteJira.setJira(jira);
				reportes.add(versionReporteJira);
			}
		}
	}

	private boolean existeReporte(String url){
		UltimaRevision ultimaRevision = new ServicioUltimaRevisionSVN(url).revision();
		if (ultimaRevision == null){
			return false;
		}
		return true;
	}

	private void adicionarScript(String datoScript, ETipoScript tipoScript){
		if (datoScript == null){
			return;
		}


		EntVersionScript script = new EntVersionScript();
		script.setTipoScript(tipoScript);
		script.setUrlScript(datoScript);
		List<EntVersionScriptJira> jiras = scripts.get(script);
		if (jiras == null){
			jiras = new ArrayList<>();
			scripts.put(script, jiras);
		}
		EntVersionScriptJira versionScriptJira = new EntVersionScriptJira();
		versionScriptJira.setJira(jira);
		versionScriptJira.setScript(script);
		jiras.add(versionScriptJira);
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


	private void nuevoTicketSysAid(Integer numero){
		if (numero == null){
			return;
		}

		EntTicketSysAid ticketSysAid = new EntTicketSysAid();
		ticketSysAid.setNumero(numero);
		this.ticketSysAids.add(ticketSysAid);
	}
}
