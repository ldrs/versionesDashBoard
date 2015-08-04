package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import rd.huma.dashboard.model.sysaid.ExceptionTicketNoEncontrado;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntVersion;
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
		Set<String> duenos = procesadorTickets.getDuenos();
		duenos.remove(version.getAutor());
		duenos.stream().forEach(d -> { servicioVersion.crearVersionDueno(d, version);  }  );
		procesadorTickets.getTicketSysAid().stream().forEach(t -> manejaTicketsSysAid(t.getNumero()));

		procesadorTickets.getParticipantes().stream().forEach(servicioJira::salvarParticipante);
	}

	private void manejaTicketsSysAid(String numero){
		try {

			servicioVersion.crearVersionTicketSysAid(numero, version);
		}catch(ExceptionTicketNoEncontrado e){//TODO ponerlo en notificacion?
			EjecutorVersion.LOGGER.warning(String.format("el ticket %s no fue encontrado, y fue mencionado para la version %s",numero,version.getNumero()));
		}
	}

	private void grabarVersionJira(EntJira jira){
		servicioVersion.crearVersionJira(jira, version);
	}
}