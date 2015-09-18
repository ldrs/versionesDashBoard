package rd.huma.dashboard.servicios.background.ejecutores.version;

import static rd.huma.dashboard.servicios.transaccional.ServicioVersion.getInstanciaTransaccional;

import java.util.Optional;
import java.util.logging.Logger;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.integracion.sysaid.ServicioIntegracionSYSAID;
import rd.huma.dashboard.servicios.transaccional.ServicioTicketSysaid;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class ProcesadorTicketSysaid extends AEjecutor {

	private static final Logger LOGGER = Logger.getLogger(ProcesadorTicketSysaid.class.getSimpleName());

	private EntVersion version;
	private ServicioIntegracionSYSAID servicioSysaid;
	private ServicioVersion servicio;

	private ServicioTicketSysaid servicioTicketSysaid;

	public ProcesadorTicketSysaid(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		this.servicio = getInstanciaTransaccional();
		this.servicioSysaid =  ServicioIntegracionSYSAID.instancia();
		this.servicioTicketSysaid = ServicioTicketSysaid.getInstanciaTransaccional();
		servicio.buscaTickets(version).forEach(this::actualizaEstado);

		servicio.gestionarFila(version);
	}

	private void actualizaEstado(EntVersionTicket versionTicket){
		try{
			Optional<Ticket> intentalTicket = servicioSysaid.getTicket(versionTicket.getTicketSysAid().getNumero());
			if (intentalTicket.isPresent()){
				Ticket ticket = intentalTicket.get();
				EntTicketSysAid t = versionTicket.getTicketSysAid();
				t.setEstado(String.valueOf(ticket.getEstado()));
				servicioTicketSysaid.actualizar(t);
			}
		}catch(Exception e){
			LOGGER.warning(String.format("Buscando el ticket %s de la version %s ocurrio el error %s",versionTicket.getTicketSysAid().getNumero(), version.getNumero(), e.getMessage()));

		}
	}

}
