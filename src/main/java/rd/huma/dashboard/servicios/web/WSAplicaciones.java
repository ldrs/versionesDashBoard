package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;

@Path("/aplicaciones")
public class WSAplicaciones {

	private @Servicio @Inject ServicioAplicacion servicioAplicacion;

	@GET
	public String aplicaciones(){
		JsonArrayBuilder builder = createArrayBuilder();
		getAplicaciones().stream().forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getNombre())
																				.add("css", "")
																				.add("id",s.getId())
																				.add("jiraKey",s.getJiraKey())
																				.add("svnPath",s.getSvnPath())
																				.add("orden",s.getOrden())
														 )
										);

		return builder.build().toString();
	}

	private List<EntAplicacion> getAplicaciones(){
		return servicioAplicacion.getAplicaciones();
	}
}