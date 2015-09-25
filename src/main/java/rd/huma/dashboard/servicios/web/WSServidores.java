package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;
@Path("servidores")
public class WSServidores {

	private @Servicio @Inject ServicioAmbiente servicioAmbiente;
	private @Servicio @Inject ServicioServidor servicioServidor;
	private @Servicio @Inject ServicioVersion servicioVersion;
	private @Servicio @Inject ServicioFila servicioFila;
	private @Servicio @Inject ServicioPersona servicioPersona;
	private @Servicio @Inject ServicioJobDespliegueVersion servicioJobDespliegueVersion;


	@GET
	@Path("ambiente/{ambiente}")
	public String servidores(@PathParam("ambiente") String ambiente){
		JsonArrayBuilder builder = createArrayBuilder();
		getServidores(ambiente).stream().filter(s -> s.getBaseDatos()!=null) .forEach(s -> builder.add(toJson(s))

										);

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

	private JsonObjectBuilder toJson(EntServidor s){
		return createObjectBuilder()
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