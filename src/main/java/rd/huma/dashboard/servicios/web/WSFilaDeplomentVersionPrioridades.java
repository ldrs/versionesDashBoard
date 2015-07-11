package rd.huma.dashboard.servicios.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.servicios.web.simulacion.SimulaFila;

@Path("filasPrioridad/")
public class WSFilaDeplomentVersionPrioridades {

	@GET
	@Path("sube/{id}")
	public String subePrioridad(@PathParam("id") String id){
		SimulaFila.subePrioridad(id);
		
		return "{}";
	}

	@GET
	@Path("baja/{id}")
	public String bajaPrioridad(@PathParam("id") String id){
		SimulaFila.bajaPrioridad(id);
		
		return "{}";
	}
	
}
