package rd.huma.dashboard.servicios.background.ejecutores.sysaid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.integracion.sysaid.ServicioIntegracionSYSAID;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioTicketSysaid;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorSysAidBuscaActualizacion extends AEjecutor {

	@Override
	public void ejecutar() {
		ServicioTicketSysaid servicio =  ServicioTicketSysaid.getInstanciaTransaccional();
		List<EntTicketSysAid> tickets =  servicio.buscarTodosQueNoEstenEnProduccion();


		Map<Long, EntTicketSysAid> mapa = tickets.stream().collect(Collectors.toMap(EntTicketSysAid::getNumero, Function.identity()));
		List<EntTicketSysAid> ticketsPorCambiarse = new ArrayList<>();

		List<Ticket> ticketValores = ServicioIntegracionSYSAID.instancia().getTickets(ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get(), tickets.stream().map(EntTicketSysAid::getNumero).collect(Collectors.toSet()).toArray(new Long[]{}));
		for (Ticket ticket : ticketValores) {
			EntTicketSysAid entidad = mapa.get(ticket.getTicket());
			if (ticket.getEstado()!=entidad.getEstado()){
				entidad.setEstado(entidad.getEstado());
				ticketsPorCambiarse.add(entidad);
			}
		}
		ServicioFila servicioFilas = ServicioFila.getInstanciaTransaccional();
		ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();

		for (EntTicketSysAid ticket : ticketsPorCambiarse) {
			ticket = servicio.actualizar(ticket);
			List<EntFilaDespliegueVersion> filas = servicioFilas.getFilaPorTicket(ticket);
			for (EntFilaDespliegueVersion fila : filas) {
				servicioVersion.gestionarFila(fila);
			}
		}
	}
}