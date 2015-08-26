package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.servicios.transaccional.ServicioJira;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

class ProcesadorDatos {

	private ProcesadorTickets procesadorTickets;
	private EntVersion version;
	private ServicioJira servicioJira;
	private ServicioVersion servicioVersion;
	private Map<String, EntVersionJira> jiras = new HashMap<>();

	public ProcesadorDatos(ProcesadorTickets procesadorTickets) {
		this.procesadorTickets = procesadorTickets;
		this.version = procesadorTickets.getVersion();
		this.servicioJira = ServicioJira.getInstanciaTransaccional();
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
	}

	public void grabarDatos(){
		List<EntJira> jiraGrabados = new ArrayList<>();
		procesadorTickets.getJiras().forEach(j->  jiraGrabados.add(procesarJira(j.getNumero(), j.getEstado())));
		jiraGrabados.forEach(this::grabarVersionJira);
		Set<String> duenos = procesadorTickets.getDuenos();
		duenos.remove(version.getAutor());
		duenos.stream().forEach(d ->  servicioVersion.crearVersionParticipante(d, version) );
		procesadorTickets.getTicketSysAid().stream().forEach(t -> manejaTicketsSysAid(t.getNumero()));

		procesadorTickets.getParticipantes().stream().forEach(servicioJira::salvarParticipante);

		procesadorTickets.getReportes().forEach(this::adicionarReporte);
	}

	private void adicionarReporte(EntVersionReporte reporte){
		EntVersionReporte versionReporte = new EntVersionReporte();
		versionReporte.setReporte(reporte.getReporte());
		versionReporte.setVersion(version);
		versionReporte.setAutor(reporte.getAutor());
		versionReporte.setJira(jiras.get(reporte.getJira().getNumero()).getJira());
		versionReporte.setRevision(reporte.getNumeroRevision());

		servicioVersion.crearVersionReporte(versionReporte);
	}

	private EntJira procesarJira(String numeroJira, String estado){
		EntJira jira = servicioJira.encuentraOSalva(numeroJira, estado);
		Set<EntVersionScript> scriptsQuitar = new HashSet<>();
		procesadorTickets.getScripts().stream().filter(s -> s.getJira().getNumero().equals(numeroJira)).forEach(s ->  scriptsQuitar.add(procesaScript(s, jira)));
		procesadorTickets.getScripts().removeAll(scriptsQuitar);
		return jira;
	}

	private EntVersionScript procesaScript(EntVersionScript script, EntJira jira){
		servicioVersion.crearVersionScript(version, jira, script.getUrlScript(), script.getTipoScript());
		return script;
	}

	private void manejaTicketsSysAid(String numero){
		if (!servicioVersion.crearVersionTicketSysAid(numero, version)){
			EjecutorVersion.LOGGER.warning(String.format("el ticket %s no fue encontrado, y fue mencionado para la version %s",numero,version.getNumero()));
		}
	}

	private void grabarVersionJira(EntJira jira){
		jiras.put(jira.getNumero(), servicioVersion.crearVersionJira(jira, version));
	}
}