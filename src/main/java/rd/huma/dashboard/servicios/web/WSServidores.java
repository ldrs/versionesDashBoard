package rd.huma.dashboard.servicios;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.model.EntServidor;
@Path("/servidores")
public class WSServidores {

	
	@GET
	public String servidores(@QueryParam("ambiente") String ambiente){
		JsonArrayBuilder builder = createArrayBuilder();
		getServidores().stream().forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getNombre())
																				.add("css", "")
																				.add("id",s.getId())
														 )
										);
		
		return builder.build().toString();
	}
	
	private List<EntServidor> getServidores(){
		List<EntServidor> lst = new ArrayList<>();
		lst.add(nuevoServidor("172.16.7.30:7777"));
		lst.add(nuevoServidor("172.16.7.30:8888"));
		return lst;
	}
	
	private EntServidor nuevoServidor(String nombre){
		EntServidor servidor = new EntServidor();
		servidor.setNombre(nombre);
		return servidor;
	}
}
