package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

@Path("filasPrioridad/")
public class WSFilaDeplomentVersionPrioridades {

	private @Servicio @Inject ServicioFila fila;

	@GET
	@Path("sube/{id}")
	public String subePrioridad(@PathParam("id") String id){
		fila.subePrioridad(id);

		return "{}";
	}

	@GET
	@Path("baja/{id}")
	public String bajaPrioridad(@PathParam("id") String id){
		fila.bajaPrioridad(id);

		return "{}";
	}

	@GET
	@Path("elimina/{id}")
	public String eliminaFila(@PathParam("id") String id){
		fila.eliminarFilaVersion(id);
		return "{}";
	}

}
