package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;
@Path("/servidores/{ambiente}")
public class WSServidores {

	private @Servicio @Inject ServicioServidor servicioServidor;
	private @Servicio @Inject ServicioVersion servicioVersion;
	private @Servicio @Inject ServicioFila servicioFila;


	@GET
	public String servidores(@PathParam("ambiente") String ambiente){
		JsonArrayBuilder builder = createArrayBuilder();
		getServidores(ambiente).stream().filter(s -> s.getBaseDatos()!=null) .forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getNombre())
																				.add("css", "none")
																				.add("ruta", s.getRutaEntrada())
																				.add("id",s.getId())
																				.add("version",  version(s) )
																				.add("estado", s.getEstadoServidor().name())
																				.add("tickets", tickets(s.getVersionActual()))
																				.add("repositorioDatos", Json.createObjectBuilder()
																										.add("id", s.getBaseDatos().getId())
																										.add("schema", s.getBaseDatos().getSchema())
																										.add("puerto", s.getBaseDatos().getPuerto())
																										.add("host", s.getBaseDatos().getHost())
																										.add("servicio", s.getBaseDatos().getServicio())
																										.add("actualizacion",  UtilFecha.getFechaFormateada(s.getBaseDatos().getUltimaActualizacion()))
																										)
																				.add("jiras", jiras(s.getVersionActual()))
														 )
										);

		return builder.build().toString();
	}

	private JsonObjectBuilder version(EntServidor servidor){
		JsonObjectBuilder rt = Json.createObjectBuilder();
		EntVersion version = servidor.getVersionActual();
		if (version == null){
			rt.add("numero", "Sin Versión");
		}else{
			JsonArrayBuilder duenos = createArrayBuilder();
			servicioFila.getDuenosVersion(version).forEach(d -> duenos.add(d.getId()));

			rt.add("numero", version.getNumero());
			rt.add("id", version.getId());
			rt.add("duenos", duenos );
		}
		return rt;
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