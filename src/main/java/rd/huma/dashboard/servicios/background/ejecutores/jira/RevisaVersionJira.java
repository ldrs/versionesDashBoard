package rd.huma.dashboard.servicios.background.ejecutores.jira;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.servicios.background.ejecutores.version.ColectorInformacionFieldsJira;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class RevisaVersionJira {

	private EntJira jira;
	private Set<EntVersionScript> scripts = new HashSet<>();
	private Set<String> duenos = new HashSet<>();
	private Set<EntJiraParticipante> participantes = new HashSet<>();
	private Set<EntTicketSysAid> ticketSysAids = new HashSet<>();
	private Set<EntVersionReporte> reportes = new HashSet<>();
	private EntVersion version;
	private Fields fields;
	private ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();

	public RevisaVersionJira(EntJira jira, EntVersion version, Fields fields) {
		this.jira = jira;
		this.version = version;
		this.fields = fields;
	}

	void ejecutar(){
		new ColectorInformacionFieldsJira(fields, duenos, participantes, scripts, jira, ticketSysAids, reportes).procesar();


		manejaCambioParticipantes();
		manejaCambiaReportes();
		manejaCambioScripts();
	}

	private Optional<EntVersionJira> buscarJira(EntVersionScript versionScript, String numero){
		return servicioVersion.buscaJiras(version).stream().filter(j -> j.getJira().getNumero().equals(numero)).findFirst();
	}

	private void manejaCambioScripts() {
		for(EntVersionScript versionScript : servicioVersion.buscaScript(version)){

			if (scripts.contains(versionScript)){
				scripts.remove(versionScript);
				EntVersionScript found = scripts.stream().filter(s -> s.equals(versionScript)).findFirst().get();
				if (versionScript.getTipoScript()!=found.getTipoScript()){
					versionScript.setTipoScript(found.getTipoScript());
					servicioVersion.actualizarVersionScript(versionScript);
				}

			}else{
				servicioVersion.eliminarScript(versionScript);
			}
		}
		scripts.forEach(this::crearScript);
	}

	private void crearScript(EntVersionScript versionScript){
		Optional<EntVersionJira> jira = buscarJira(versionScript, versionScript.getJira().getNumero());
		if (jira.isPresent()){
			EntVersionJira versionJira = jira.get();
			servicioVersion.crearVersionScript(version, versionJira.getJira(), versionScript.getUrlScript(), versionScript.getTipoScript());
		}
	}

	private void manejaCambiaReportes() {
		for(EntVersionReporte versionReporte : servicioVersion.buscaReportesVersion(version)){
			if (reportes.contains(versionReporte.getReporte())){
				reportes.remove(versionReporte.getReporte());
			}else{
				servicioVersion.eliminarVersion(versionReporte);
			}
		}
		reportes.forEach(this::creaVersionReporte);
	}

	private EntVersionReporte creaVersionReporte(EntVersionReporte reporte){
		EntVersionReporte versionReporte = new EntVersionReporte();
		versionReporte.setReporte(reporte.getReporte());
		versionReporte.setVersion(version);
		versionReporte.setAutor(reporte.getAutor());
		versionReporte.setJira(jira);
		versionReporte.setRevision(reporte.getNumeroRevision());
		servicioVersion.crearVersionReporte(versionReporte);
		return versionReporte;
	}

	private void manejaCambioParticipantes(){
		Set<String> versionParticipantes = getParticipantes();

		participantes.stream().filter(p -> !versionParticipantes.contains(p.getParticipante().getUsuarioSvn()))
		.map(EntJiraParticipante::getParticipante)
		.map(EntPersona::getUsuarioSvn)
		.forEach(this::creaNuevoParticipantes);
	}

	private void creaNuevoParticipantes(String persona){
		servicioVersion.crearVersionParticipante(persona, version);
	}

	private Set<String> getParticipantes(){
		Set<String> versionParticipantes = new HashSet<>();

		servicioVersion.buscaParticipantes(version).stream().forEach( participante ->  versionParticipantes.add(participante.getParticipante().getUsuarioSvn()));
		return versionParticipantes;
	}
}