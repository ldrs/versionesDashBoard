package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;

@Path("/personaConsulta")
public class WSPersonas {

	@Inject
	private @Servicio ServicioPersona  servicioPersona;

	@GET
	public String personas(){
		JsonArrayBuilder builder = Json.createArrayBuilder();

		servicioPersona.buscarPersonas().forEach(p -> builder.add(	Json.createObjectBuilder()
																	.add("Nombre", p.getNombre())
																	.add("svn", p.getUsuarioSvn())
																	.add("correo", p.getCorreo())
																));
		return builder.build().toString();
	}
}