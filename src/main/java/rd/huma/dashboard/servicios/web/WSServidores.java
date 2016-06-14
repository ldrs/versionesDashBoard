package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import rd.huma.dashboard.antipatron.dto.Servidor;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoServidor;
import rd.huma.dashboard.model.transaccional.dominio.ETipoUndeploy;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorReporteReintento;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorScriptAntes;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorScriptDespues;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorScriptTodos;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioAmbiente;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.servicios.utilitarios.UtilJSON;
import rd.huma.dashboard.util.UtilFecha;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
@Path("servidores")
public class WSServidores {

	private @Servicio @Inject ServicioAmbiente servicioAmbiente;
	private @Servicio @Inject ServicioServidor servicioServidor;
	private @Servicio @Inject ServicioVersion servicioVersion;
	private @Servicio @Inject ServicioFila servicioFila;
	private @Servicio @Inject ServicioPersona servicioPersona;
	private @Servicio @Inject ServicioJobDespliegueVersion servicioJobDespliegueVersion;
	private @Servicio @Inject ServicioRepositorioDatos servicioRepositorio;

	private  @Context Request request;


	@GET
	@Path("ambiente/{ambiente}")
	public String servidores(@PathParam("ambiente") String ambiente){
		JsonArrayBuilder builder = createArrayBuilder();
		getServidores(ambiente).stream().filter(s -> s.getBaseDatos()!=null) .forEach(s -> builder.add(toJson(s)));
		return builder.build().toString();
	}

	@GET
	@Path("undeploy/{servidor}/{autor}")
	public String undeploy(@PathParam("servidor") String idServidor , @PathParam("autor") String autor  ){
		EntServidor servidor = servicioServidor.getServidorPorId(idServidor);
		if (servidor == null){
			return "{}";
		}


		servicioServidor.cambiaVersionServidor(servidor, null,servicioPersona.busca(autor),ETipoUndeploy.MANUAL);

		return toJson(servidor).build().toString();
	}

	@GET
	@Path("cambiarServidor/{servidorOrigen}/{servidorDestino}/{autor}")
	public String cambiaServidor(@PathParam("servidorOrigen") String idServidorOrigen,@PathParam("servidorDestino") String idServidorDestino  , @PathParam("autor") String autor  ){
		EntServidor servidorOrigen = servicioServidor.getServidorPorId(idServidorOrigen);
		EntServidor servidorDestino = servicioServidor.getServidorPorId(idServidorDestino);
		if (servidorOrigen == null || servidorDestino == null){
			return "{}";
		}
		EntFilaDespliegue fila = servicioFila.getFilaPorAmbienteAplicacion(servidorOrigen.getAmbiente().getId());
		EntVersion version = servidorOrigen.getVersionActual();


		servicioServidor.cambiaVersionServidor(servidorOrigen, null,servicioPersona.busca(autor),ETipoUndeploy.MANUAL);

		servicioServidor.cambiaVersionServidor(servidorDestino, version,servicioPersona.busca(autor),null);

		servicioJobDespliegueVersion.nuevoDeploy(servidorDestino, fila, version);

		return toJson(servidorDestino).build().toString();
	}

	@GET
	@Path("copiaReportes/{idServidor}/{idUsuario}")
	public String copiaReportes(@PathParam("idServidor") String idServidor, @PathParam("idUsuario") String idUsuario){
		EntServidor servidor = servicioServidor.getServidorPorId(idServidor);
		if (servidor == null){
			return "{ejecuto:false}";
		}
		servicioVersion.ejecutarJob(new EjecutorReporteReintento(servidor.getVersionActual()));
		return "{ejecuto:true}";
	}


	@GET
	@Path("ejecutarTodosScripts/{idServidor}/{idUsuario}")
	public String ejecutarTodosScripts(@PathParam("idServidor") String idServidor, @PathParam("idUsuario") String idUsuario){
		EntServidor servidor = servicioServidor.getServidorPorId(idServidor);
		if (servidor == null){
			return "{ejecuto:true}";
		}

		servicioVersion.ejecutarJob(new EjecutorScriptTodos(servidor.getVersionActual()));
		return "{ejecuto:true}";
	}

	@GET
	@Path("ejecutarAntesScripts/{idServidor}/{idUsuario}")
	public String ejecutarAntesScripts(@PathParam("idServidor") String idServidor, @PathParam("idUsuario") String idUsuario){
		EntServidor servidor = servicioServidor.getServidorPorId(idServidor);
		if (servidor == null){
			return "{ejecuto:false}";
		}

		servicioVersion.ejecutarJob(new EjecutorScriptAntes(servidor.getVersionActual()));
		return "{ejecuto:true}";
	}


	@GET
	@Path("ejecutarDespuesScripts/{idServidor}/{idUsuario}")
	public String ejecutarScriptDespues(@PathParam("idServidor") String idServidor, @PathParam("idUsuario") String idUsuario){
		EntServidor servidor = servicioServidor.getServidorPorId(idServidor);
		if (servidor == null){
			return "{ejecuto:false}";
		}

		servicioVersion.ejecutarJob(new EjecutorScriptDespues(servidor.getVersionActual()));
		return "{ejecuto:true}";
	}

	@GET
	@Path("cambiaDisponibilidad/{idServidor}/{idUsuario}/{disponible}")
	public String cambiaDisponibilidad(@PathParam("idServidor") String idServidor ,@PathParam("idUsuario") String idUsuario, @PathParam("disponible") boolean disponible  ){
		EntServidor servidor = servicioServidor.getServidorPorId(idServidor);
		if (servidor == null){
			return "{}";
		}
		servidor.setEstadoServidor (disponible? servidor.getVersionActual() == null ? EEstadoServidor.LIBRE : EEstadoServidor.OCUPADO:  EEstadoServidor.NO_DISPONIBLE_MANUAL);
		servidor = servicioServidor.actualizarServidor(servidor);
		return toJson(servidor).build().toString();
	}


	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonArray getServidores(){
		return servicioServidor.getServidores().stream().map(s-> toJson(s)).collect(UtilJSON.toJsonArray()).build();
	}

	@GET
	@Path("/{idServidor}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getServidor(@PathParam("idServidor") String idServidor){
		String json = null;
		ObjectWriter ow = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writer().withDefaultPrettyPrinter();
		try{
			json = ow.writeValueAsString(servicioServidor.getServidor(idServidor));
		}catch(Exception e){
			json = Json.createObjectBuilder().build().toString();
		}
		return json;
	}


	@PUT
	@Path("/{idServidor}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void actualizarServidor(@PathParam("idServidor") String idServidor, final Servidor servidor){
		if(servidor != null){
			EntServidor server = servicioServidor.getServidorPorId(idServidor);
			server.setNombre(servidor.getNombre());
			server.setRutaEntrada(servidor.getRutaEntrada());
			server.setNombreServidorJenkins(servidor.getNombreServidorJenkins());
			server.setAmbiente(servicioAmbiente.getAmbienteAplicacion(servidor.getAmbiente()));
			server.setBaseDatos(servicioRepositorio.getRepositorioDato(servidor.getBaseDatos()));

			servicioServidor.actualizarServidor(server);
		}
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void  crearServidor(Servidor servidor){
		servicioServidor.nuevoServidor( servicioAmbiente.getAmbienteAplicacion(servidor.getAmbiente()),
										servicioRepositorio.getRepositorioDato(servidor.getBaseDatos()),
										servidor.getNombre(), servidor.getRutaEntrada(), servidor.getNombreServidorJenkins() );
	}




	private  JsonObjectBuilder toJson(EntServidor s){
		return createObjectBuilder()
		.add("nombre", s.getNombre())
		.add("nombreServidorJenkins", s.getNombreServidorJenkins())
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
		.add("ambienteDuenos",ambienteDuenos(s))
		.add("ambienteResponsables",ambienteResponsables(s,s.getVersionActual()))
		.add("jiras", jiras(s.getVersionActual()));
	}

	private JsonArrayBuilder ambienteResponsables(EntServidor s,EntVersion version) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		if (version == null){
			return builder;
		}
		Set<EntPersona> responsables = servicioAmbiente.getResponsables(s.getAmbiente().getAmbiente());
		servicioVersion.buscaParticipantes(version).stream().filter(p ->  responsables.contains(p.getParticipante())).forEach( p-> builder.add(p.getParticipante().getId()));
		return builder;
	}

	private JsonArrayBuilder ambienteDuenos(EntServidor s){
		JsonArrayBuilder builder = Json.createArrayBuilder();
		servicioAmbiente.getDuenos(s.getAmbiente().getAmbiente()).stream().forEach(e -> builder.add(e.getPersona().getId()));
		return builder;
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
			rt.add("branch", version.getBranchOrigen());
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