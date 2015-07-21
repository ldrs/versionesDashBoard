package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioAmbiente;

@Path("/ambientes/{aplicacion}")
public class WSAmbientes {

	private @Servicio @Inject ServicioAmbiente servicioAmbiente;

	@GET
	public String ambientes(@PathParam("aplicacion") String aplicacion){
		JsonArrayBuilder builder = createArrayBuilder();
		getAmbientes(aplicacion).stream().forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getAmbiente().getNombre())
																				.add("css", "")
																				.add("id",s.getId())
																				.add("orden",s.getAmbiente().getOrden())
														 )
										);

		return builder.build().toString();
	}

	private List<EntAmbienteAplicacion> getAmbientes(String id){
		return servicioAmbiente.getAmbientesAplicacion(id);
	}
}