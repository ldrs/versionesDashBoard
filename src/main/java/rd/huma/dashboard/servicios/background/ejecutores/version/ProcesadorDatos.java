package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.List;

import rd.huma.dashboard.model.EntJira;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioJira;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

class ProcesadorDatos {

	private ProcesadorTickets procesadorTickets;
	private EntVersion version;
	private ServicioJira servicioJira;
	private ServicioVersion servicioVersion;

	public ProcesadorDatos(ProcesadorTickets procesadorTickets) {
		this.procesadorTickets = procesadorTickets;
		this.version = procesadorTickets.getVersion();
		this.servicioJira = ServicioJira.getInstanciaTransaccional();
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
	}

	public void grabarDatos(){

		List<EntJira> jiraGrabados = new ArrayList<>();
		procesadorTickets.getJiras().forEach(j->  {jiraGrabados.add(servicioJira.encuentraOSalva(j.getNumero(), j.getEstado()));});
		jiraGrabados.forEach(this::grabarVersionJira);

	}

	private void grabarVersionJira(EntJira jira){
		servicioVersion.crearVersionJira(jira, version);
	}

}