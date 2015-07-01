package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

@Path("/versionConsultaDuenos")
public class WSVersionesDuenos {

	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@GET
	@Produces("text/plain")
	public String consulta(){
		JsonArrayBuilder builder = Json.createArrayBuilder();

		servicioVersion.buscaDuenos().stream().forEach( p -> builder.add(	Json.createObjectBuilder()
																	.add("Nombre", p.getDueno().getUsuarioSvn())
																	.add("svn", p.getVersion().getNumero())

																));
		return builder.build().toString();
	}
}