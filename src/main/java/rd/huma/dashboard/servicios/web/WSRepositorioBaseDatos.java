package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;

@Path("repositorioDatos")
public class WSRepositorioBaseDatos {

	private @Inject @Servicio ServicioRepositorioDatos servicio;

	@Path("actualizar/{servicio}/{schema}")
	public String actualizar(String nombreServicio, String schema){
		return servicio.actualizar(nombreServicio,schema);
	}


	@Path("inicioActualizar/{host}/{servicio}/{schema}/{puerto}")
	public String inicioActualizar(@PathParam("host") String host,@PathParam("servicio") String nombreServicio,@PathParam("schema") String schema ,@PathParam("puerto") String puerto){
		return servicio.inicioActualizar(host,nombreServicio,schema,puerto);
	}

	@Path("finalizarActualizar/{host}/{servicio}/{schema}/{puerto}")
	public String finalizarActualizar(@PathParam("host") String host,@PathParam("servicio") String nombreServicio,@PathParam("schema") String schema ,@PathParam("puerto") String puerto){
		return servicio.finalizarActualizar(host,nombreServicio,schema,puerto);
	}
}