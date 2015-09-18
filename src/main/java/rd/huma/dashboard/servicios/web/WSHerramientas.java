package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.Path;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioTicketSysaid;

@Path("herramientas")
public class WSHerramientas {

	@Servicio @Inject
	private ServicioTicketSysaid servicioTicketSysaid;


	@Path("recalcularTicketSysaid")
	public void calcularTicketSysaid(){
		servicioTicketSysaid.buscarTodos().forEach(servicioTicketSysaid::refrescarEstado);
	}
}