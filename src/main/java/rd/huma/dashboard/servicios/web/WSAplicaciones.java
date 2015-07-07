package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.transaccional.EntAplicacion;

@Path("/aplicaciones")
public class WSAplicaciones {

	@GET
	public String aplicaciones(){
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
	
	private List<EntAplicacion> getAmbientes(){
		List<EntAplicacion> lst = new ArrayList<>();
		lst.add(nuevoAmbiente("sigef"));
		lst.add(nuevoAmbiente("esigef"));
		return lst;
	}
	
	private EntAplicacion nuevoAmbiente(String nombre){
		EntAplicacion servidor = new EntAplicacion();
		servidor.setNombre(nombre);
		return servidor;
	}
}
