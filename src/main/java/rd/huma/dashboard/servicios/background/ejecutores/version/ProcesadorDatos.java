package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraEstado;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.EntVersionScriptJira;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;
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
		procesadorTickets.getJiras().forEach(j->  jiraGrabados.add(procesarJira(j.getNumero(),j.getJiraEstado())));
		jiraGrabados.forEach(this::grabarVersionJira);
		Set<String> duenos = procesadorTickets.getDuenos();
		duenos.remove(version.getAutor().getUsuarioSvn());
		duenos.stream().forEach(d ->  servicioVersion.crearVersionParticipante(d, version) );
		procesadorTickets.getTicketSysAid().stream().forEach(t -> manejaTicketsSysAid(t.getNumero()));

		procesadorTickets.getParticipantes().stream().forEach(servicioJira::salvarParticipante);

		procesadorTickets.getReportes().forEach((urlReporte,reportesJira)-> servicioVersion.crearVersionReporteYJiras(urlReporte, version, reportesJira));
		procesadorTickets.getScripts().forEach(this::adicionarScript);
		grabarBranch();
	}

	private void grabarBranch() {
		if (procesadorTickets.getBranch()!=null){
			ServicioBranch servicio =  ServicioBranch.getInstanciaTransaccional();
			EntBranch branch = servicio.procesarOrigen(procesadorTickets.getBranch(), procesadorTickets.getAplicacion(), version.getBranchOrigen());
			branch.setRevisionUltima(Long.valueOf(version.getRevisionSVN()));
			branch.setMerge(false);
			servicio.actualizar(branch);
		}
	}

	private void adicionarScript(EntVersionScript script, List<EntVersionScriptJira> reportesJira){
		 EntVersionScript scriptGrabado = servicioVersion.crearVersionScript(version, script.getUrlScript(), script.getTipoScript());
		 for (EntVersionScriptJira jiraScript : reportesJira) {
			 jiraScript.setScript(scriptGrabado);
			 jiraScript.setJira(servicioJira.encuentra(jiraScript.getJira().getNumero()).get());
			 servicioVersion.crearScriptJira(jiraScript);
		}
	}

	private EntJira procesarJira(String numeroJira, EntJiraEstado entJiraEstado){
		return servicioJira.encuentraOSalva(numeroJira, entJiraEstado);
	}

	private void manejaTicketsSysAid(long numero){
		if (!servicioVersion.crearVersionTicketSysAid(numero, version)){
			EjecutorVersion.LOGGER.warning(String.format("el ticket %s no fue encontrado, y fue mencionado para la version %s",numero,version.getNumero()));
		}
	}

	private void grabarVersionJira(EntJira jira){
		jiras.put(jira.getNumero(), servicioVersion.crearVersionJira(jira, version));
	}
}