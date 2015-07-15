package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.Path;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;

@Path("repositorioDatos")
public class WSRepositorioBaseDatos {

	private @Inject @Servicio ServicioRepositorioDatos servicio;
	
	@Path("actualizar/{servicio}/{schema}")
	public String actualizar(String nombreServicio, String schema){
		return servicio.actualizar(nombreServicio,schema);
	}
}