package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
@Path("/servidores/{ambiente}")
public class WSServidores {

	private @Servicio @Inject ServicioServidor servicioServidor;
	private @Servicio @Inject ServicioVersion servicioVersion;


	@GET
	public String servidores(@PathParam("ambiente") String ambiente){
		JsonArrayBuilder builder = createArrayBuilder();
		getServidores(ambiente).stream().filter(s -> s.getBaseDatos()!=null) .forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getNombre())
																				.add("css", "")
																				.add("ruta", s.getRutaEntrada())
																				.add("id",s.getId())
																				.add("version", s.getVersionActual() == null ? "Sin Version" : s.getVersionActual().getNumero())
																				.add("estado", s.getEstadoServidor().name())
																				.add("tickets", tickets(s.getVersionActual()))
																				.add("repositorioDatos", Json.createObjectBuilder()
																										.add("schema", s.getBaseDatos().getSchema())
																										.add("servicio", s.getBaseDatos().getServicio())
																										.add("actualizacion", s.getBaseDatos().getUltimaActualizacion().toString())
																										)
																				.add("jiras", jiras(s.getVersionActual()))
														 )
										);

		return builder.build().toString();
	}

	private List<EntServidor> getServidores(String id){
		return servicioServidor.getServidoresAmbiente(id);
	}

	private JsonArrayBuilder tickets(EntVersion version){
		JsonArrayBuilder arreglo = Json.createArrayBuilder();
		if (version == null){
		 return arreglo;
		}
		servicioVersion.buscaTickets(version).forEach(j -> arreglo.add(j.getTicketSysAid().getNumero()));
		return arreglo;
	}

	private JsonArrayBuilder jiras(EntVersion version){
		JsonArrayBuilder arreglo = Json.createArrayBuilder();
		if (version == null){
		 return arreglo;
		}
		servicioVersion.buscaJiras(version).forEach(j -> arreglo.add(j.getJira().getNumero()));
		return arreglo;
	}
}