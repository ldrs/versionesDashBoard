package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.Collections;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.servicios.web.simulacion.SimulacionAmbientes;

@Path("/ambientes/{aplicacion}")
public class WSAmbientes {

	@GET
	public String ambientes(@PathParam("aplicacion") String aplicacion){
		JsonArrayBuilder builder = createArrayBuilder();
		getAmbientes(aplicacion).stream().forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getNombre())
																				.add("css", "")
																				.add("id",s.getId())
																				.add("orden",s.getOrden())
														 )
										);

		return builder.build().toString();
	}

	private List<EntAmbiente> getAmbientes(String id){
		return SimulacionAmbientes.getAmbientes().getOrDefault(id, Collections.emptyList());
	}
}