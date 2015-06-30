package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.model.EntAmbiente;

@Path("/ambientes")
public class WSAmbientes {

	@GET
	public String ambientes(@QueryParam("aplicacion") String aplicacion){
		JsonArrayBuilder builder = createArrayBuilder();
		getAmbientes().stream().forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getNombre())
																				.add("css", "")
																				.add("id",s.getId())
																				.add("orden",s.getOrden())
														 )
										);

		return builder.build().toString();
	}

	private List<EntAmbiente> getAmbientes(){
		List<EntAmbiente> lst = new ArrayList<>();
		lst.add(nuevoAmbiente("desarollo"));
		lst.add(nuevoAmbiente("prueba"));
		lst.add(nuevoAmbiente("mesa de ayuda"));
		lst.add(nuevoAmbiente("produccion"));
		return lst;
	}

	private EntAmbiente nuevoAmbiente(String nombre){
		EntAmbiente servidor = new EntAmbiente();
		servidor.setNombre(nombre);
		return servidor;
	}
}