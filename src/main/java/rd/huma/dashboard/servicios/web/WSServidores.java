package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.Collections;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.web.simulacion.SimulaServidores;
import rd.huma.dashboard.servicios.web.simulacion.SimulaVersion;
@Path("/servidores/{ambiente}")
public class WSServidores {


	@GET
	public String servidores(@PathParam("ambiente") String ambiente){
		JsonArrayBuilder builder = createArrayBuilder();
		getServidores(ambiente).stream().forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getNombre())
																				.add("css", "")
																				.add("id",s.getId())
																				.add("version", s.getVersionActual() == null ? "NONE" : s.getVersionActual().getNumero())
																				.add("estado", s.getEstadoServidor().name())
																				.add("tickets", tickets(s.getVersionActual()))
																				.add("jiras", jiras(s.getVersionActual()))
														 )
										);

		return builder.build().toString();
	}

	private List<EntServidor> getServidores(String id){
		return SimulaServidores.getServidores(id);
	}
	
	private JsonArrayBuilder tickets(EntVersion version){
		JsonArrayBuilder arreglo = Json.createArrayBuilder();
		if (version == null){
		 return arreglo;
		}
		SimulaVersion.getTickets().getOrDefault(version, Collections.emptyList()).forEach(j -> arreglo.add(j.getTicketSysAid().getNumero()));
		return arreglo;
	}
	
	private JsonArrayBuilder jiras(EntVersion version){
		JsonArrayBuilder arreglo = Json.createArrayBuilder();
		if (version == null){
		 return arreglo;
		}
		SimulaVersion.getJiras().getOrDefault(version, Collections.emptyList()).forEach(j -> arreglo.add(j.getJira().getNumero()));
		return arreglo;
	}
}