package rd.huma.dashboard.servicios.web;

import java.util.StringJoiner;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.transaccional.EntRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.utilitarios.UtilJSON;

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

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonArray getRepositorios(){
		return	servicio.repositorios()
				.stream().map(s->toJSON(s))
				.collect(UtilJSON.toJsonArray())
                .build();
	}

	private JsonObjectBuilder toJSON(EntRepositorioDatos repositorio){
		return Json.createObjectBuilder()
				.add("id", repositorio.getId())
				.add("nombreRepositorio", new StringJoiner("  ").add(repositorio.getSchema()).add(repositorio.getServicio()).toString() );
	}
}