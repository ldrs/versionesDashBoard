package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.List;
import java.util.StringJoiner;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioAmbiente;
import rd.huma.dashboard.servicios.utilitarios.UtilJSON;

@Path("ambientes")
public class WSAmbientes {

	private @Servicio @Inject ServicioAmbiente servicioAmbiente;

	@GET
	@Path("/{aplicacion}")
	public String ambientes(@PathParam("aplicacion") String aplicacion){

		JsonArrayBuilder builder = createArrayBuilder();

		getAmbientes(aplicacion).stream().forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getAmbiente().getNombre())
																				.add("css", "")
																				.add("id",s.getId())
																				.add("idAmbiente",s.getAmbiente().getId())
																				.add("orden",s.getAmbiente().getOrden())
														 )
										);

		return builder.build().toString();
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonArray getAmbientes(){
		return servicioAmbiente.getAmbientes()
				.stream().map(s->toJSON(s))
				.collect(UtilJSON.toJsonArray())
                .build();
	}

	private List<EntAmbienteAplicacion> getAmbientes(String id){
		return servicioAmbiente.getAmbientesAplicacion(id);
	}

	private JsonObjectBuilder toJSON(EntAmbienteAplicacion ambiente){
		return Json.createObjectBuilder()
				.add("id", ambiente.getId())
				.add("nombreAmbiente", new StringJoiner("  ")
									  .add(ambiente.getAmbiente().getNombre().toUpperCase())
									  .add(ambiente.getAplicacion().getNombre().toUpperCase()).toString() );
	}
}