package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;

@Path("repositorioDatos")
public class WSRepositorioBaseDatos {

	private @Inject @Servicio ServicioRepositorioDatos servicio;

	@GET
	@Path("inicioActualizar/{host}/{servicio}/{schema}/{puerto}")
	public String inicioActualizar(@PathParam("host") String host,@PathParam("servicio") String nombreServicio,@PathParam("schema") String schema ,@PathParam("puerto") String puerto){
		if (nombreServicio.toLowerCase().endsWith(".sigef.gov.do")){
			nombreServicio = nombreServicio.substring(0,nombreServicio.indexOf(".sigef.gov.do"));
		}

		if (nombreServicio.toLowerCase().endsWith("_srv")){
			nombreServicio = nombreServicio.substring(0,nombreServicio.indexOf("_srv"));
		}

		return servicio.inicioActualizar(host.toUpperCase(),nombreServicio.toUpperCase(),schema.toUpperCase(),puerto);
	}

	@GET
	@Path("finalizarActualizar/{host}/{servicio}/{schema}/{puerto}")
	public String finalizarActualizar(@PathParam("host") String host,@PathParam("servicio") String nombreServicio,@PathParam("schema") String schema ,@PathParam("puerto") String puerto){
		if (nombreServicio.toLowerCase().endsWith(".sigef.gov.do")){
			nombreServicio = nombreServicio.substring(0,nombreServicio.indexOf(".sigef.gov.do"));
		}

		if (nombreServicio.toLowerCase().endsWith("_srv")){
			nombreServicio = nombreServicio.substring(0,nombreServicio.indexOf("_srv"));
		}

		return servicio.finalizarActualizar(host.toUpperCase(),nombreServicio.toUpperCase(),schema.toUpperCase(),puerto);
	}
}