package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import rd.huma.dashboard.model.transaccional.EntHistoricoUndeploy;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionCambioObjectoSql;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedad;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.model.transaccional.dominio.ObjectoCambio;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;

@Path("versionConsulta")
public class WSVersionesConsulta {

	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@Inject
	private @Servicio ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	@Inject
	private @Servicio ServicioRepositorioDatos servicioRepositorioDatos;

	@Inject
	private @Servicio ServicioServidor servicioServidor;

	@GET
	@Produces("text/plain")
	public String consulta(){
		JsonArrayBuilder builder = createArrayBuilder();

		servicioVersion.buscaUltimaVersiones().stream().forEach(v ->  consultaInt(builder, v));
		return builder.build().toString();
	}


	@GET
	@Produces("text/plain")
	@Path("consulta/{id}")
	public String consultaPorId(@PathParam("id") String id){
		EntVersion version = servicioVersion.buscaPorId(id);
		if (version == null){
			return "{}";
		}
		return toJson(version).build().toString();
	}


	@GET
	@Produces("text/plain")
	@Path("servidores/{id}")
	public String consultaServidores(@PathParam("id") String id){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioServidor.getServidoresPorVersion(id).forEach(s -> builder.add(toJson(s)));
		return builder.build().toString();
	}

	@GET
	@Produces("text/plain")
	@Path("cambiosModelos/{id}")
	public String consultaCambioModelos(@PathParam("id") String id){
		JsonArrayBuilder builder = createArrayBuilder();
		for (Entry<String, Map<ObjectoCambioAgrupado, ObjectoCambioAgrupado>> entry : getAgrupadosCambiosSQL(id).entrySet()){
			JsonObjectBuilder objecto = createObjectBuilder().add("objecto", entry.getKey());
			JsonArrayBuilder cambios = createArrayBuilder();
			int cantidadCambio = 0;
			for (Entry<ObjectoCambioAgrupado, ObjectoCambioAgrupado> entryInt : entry.getValue().entrySet()){
				 cantidadCambio+=entryInt.getKey().getCantidadVeces();
				 cambios.add(createObjectBuilder().add("tipo", entryInt.getKey().getObjectoCambio().getCambioTabla().name())
									 .add("cantidad", entryInt.getKey().getCantidadVeces()));
			}
			builder.add(objecto.add("cambios",cambios).add("totalCantidad", cantidadCambio));
		}

		return builder.build().toString();
	}

	@GET
	@Produces("text/plain")
	@Path("jobs/{id}")
	public String consultaJobs(@PathParam("id") String id){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioJobDespliegueVersion.buscarJobPorIdVersion(id).forEach( j -> builder.add(toJson(j)));
		return builder.build().toString();
	}

	@GET
	@Produces("text/plain")
	@Path("undeploys/{id}")
	public String consultaUndeploys(@PathParam("id") String id){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioServidor.getHistoricoUndeploy(id).forEach(h -> builder.add(toJson(h)));
		return builder.build().toString();
	}

	private JsonObjectBuilder toJson(EntHistoricoUndeploy h) {
		return createObjectBuilder()
									.add("fechaRegistro", h.getFechaRegistro() == null ?"" :  UtilFecha.getFechaFormateada (h.getFechaRegistro()))
									.add("persona", createObjectBuilder().add("id", h.getAutor().getId())
																		 .add("nombre", h.getAutor().getNombreNullSafe())
																		 )
									.add("servidor", createObjectBuilder().add("id", h.getServidor().getId())
																		 .add("nombre", h.getServidor().getNombre())
																		 .add("ruta", h.getServidor().getRutaEntrada())
																		)
									;
	}


	private JsonObjectBuilder toJson(EntJobDespliegueVersion j) {
		return createObjectBuilder().add("id", j.getId())
									.add("tipo", j.getTipoDespliegue() == null ? ETipoDespliegueJob.VERSION.name(): j.getTipoDespliegue().name())
									.add("fechaRegistro", j.getFechaRegistro() == null ?"" :  UtilFecha.getFechaFormateada (j.getFechaRegistro()))
									.add("estado", j.getEstado().name())
									.add("numero", j.getJobNumber() == null ? "-1": j.getJobNumber())
									.add("fila", j.getFilaDespliegue().getId())
									.add("observacion",  observacionPorTipo(j.getTipoDespliegue() ,j.getId()))

				;
	}

	private String observacionPorTipo(ETipoDespliegueJob tipo, String idJob){
		if (tipo == null){
			return "";
		}else if (tipo == ETipoDespliegueJob.SCRIPT){
			StringBuilder resultado = new StringBuilder(150);

			for (EntRepositorioDatosScriptEjecutados ejecucion : servicioRepositorioDatos.getScriptEjecutadosPorJob(idJob)){
				resultado.append("Para el Script <a title=\"Dar click para ver el resultado del script\" download=\"").append(ejecucion.getScript().getNombre()).append(".out.txt").append("\" href=\"http://dashboard.version.sigef.gov.do/dashboard/api/resultadoScript/").append(idJob).append("\">")
				.append(ejecucion.getScript().getNombre()).append("</a>")
				.append(" en su ejecucion el resultado fue (<span class=\"\">").append(ejecucion.getEstadoScript().name()).append(") autor (").append(ejecucion.getAutorScript().getNombreNullSafe() ) .append(") ")
				;

			}
			return resultado.toString();

		}else{
			return "";
		}
	}


	private Map<String, Map<ObjectoCambioAgrupado,ObjectoCambioAgrupado>> getAgrupadosCambiosSQL(String idVersion){
		List<EntVersionCambioObjectoSql> cambios = servicioVersion.buscaCambioModelos(idVersion);
		Map<String, Map<ObjectoCambioAgrupado,ObjectoCambioAgrupado>> cambiosAgrupados = new HashMap<>();
		for (EntVersionCambioObjectoSql entVersionCambioObjectoSql : cambios) {
			Map<ObjectoCambioAgrupado, ObjectoCambioAgrupado> cambio = cambiosAgrupados.get(entVersionCambioObjectoSql.getObjecto());
			 if (cambio == null){
				 cambio = new HashMap<>();
				 cambiosAgrupados.put(entVersionCambioObjectoSql.getObjecto(), cambio);
			 }
			 ObjectoCambioAgrupado agrupado = ObjectoCambioAgrupado.from(entVersionCambioObjectoSql);
			 ObjectoCambioAgrupado encontrado = cambio.get(agrupado);
			 if (encontrado == null){
				 cambio.put(agrupado, agrupado);
			 }else{
				 encontrado.adicionarVeces(agrupado.getCantidadVeces());
			 }
		}
		return cambiosAgrupados;
	}

	private void consultaInt(JsonArrayBuilder builder, EntVersion version){
		builder.add(toJson(version)) ;
	}

	private JsonObjectBuilder toJson(EntServidor servidor){
		return createObjectBuilder().add("id", servidor.getId())
									.add("url", servidor.getRutaEntrada())
									.add("nombre", servidor.getNombre())
									.add("estado", servidor.getEstadoServidor().name())
									.add("basedatos", servidor.getBaseDatos().toString())
				;
	}

	private JsonObjectBuilder toJson(EntVersion version){
		return		createObjectBuilder()
		.add("numero", version.getNumero())
		.add("svnOrigen", version.getSvnOrigen())
		.add("svnRevision", version.getRevisionSVN())
		.add("comentario", version.getComentario())
		.add("estado", version.getEstado().name())
		.add("autor", version.getAutor().getNombreNullSafe())
		.add("branch", version.getBranchOrigen())
		.add("fecha", UtilFecha.getFechaFormateada(version.getFechaRegistro()))
		.add("jiras", consultaJiras(version))
		.add("tickets", consultaTickets(version))
		.add("propiedades", consultaPropiedades(version))
		.add("participantes", consultaParticipantes(version))
		.add("scripts", consultaScript(version))
		.add("reportes", consultaReportes(version))
		;
	}

	private JsonArrayBuilder consultaReportes(EntVersion version) {
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaReportesVersion(version).stream().forEach(j -> agrega(builder, j));
		return builder;
	}




	private JsonArrayBuilder consultaJiras(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaJiras(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private JsonArrayBuilder consultaTickets(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaTickets(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private JsonArrayBuilder consultaPropiedades(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaPropiedades(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private JsonArrayBuilder consultaScript(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaScript(version).stream().forEach(j -> agrega(builder, j));
		return builder;
	}



	private JsonArrayBuilder agrega(JsonArrayBuilder builder, EntVersionScript script) {
		return builder.add(
		createObjectBuilder().add("id", script.getId())
							 .add("nombre", script.getNombre() == null? "": script.getNombre())
							 .add("url", script.getUrlScript())
							 .add("tipo", script.getTipoScript().name())
							 .add("jiras",  buscaJirasScript(script))
		);
	}

	private JsonArrayBuilder agrega(JsonArrayBuilder builder, EntVersionReporte reporte) {
		return builder.add(
				createObjectBuilder().add("id", reporte.getId())
									 .add("nombre", reporte.getNombre() == null ? "": reporte.getNombre())
									 .add("url", reporte.getReporte())
									 .add("jiras",  buscaJirasReporte(reporte))
				);
	}

	private JsonArrayBuilder buscaJirasScript(EntVersionScript versionReporte){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaJiraScript(versionReporte).forEach(j -> builder.add(j.getJira().getNumero()));
		return builder;
	}


	private JsonArrayBuilder buscaJirasReporte(EntVersionReporte versionReporte){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaJiraReporte(versionReporte).forEach(j -> builder.add(j.getJira().getNumero()));
		return builder;
	}





	private JsonArrayBuilder consultaParticipantes(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaParticipantes(version).stream().forEach(j -> agrega(builder, j));
		return builder;
	}

	private void agrega(JsonArrayBuilder builder, EntVersionJira jira){
		builder.add(jira.getJira().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionTicket jira){
		builder.add(jira.getTicketSysAid().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionPropiedad jira){
		builder.add(createObjectBuilder().add("nombre", jira.getPropiedad())
										 .add("valor",  jira.getValor())
				   );
	}

	private void agrega(JsonArrayBuilder builder, EntVersionParticipante jira){
		EntPersona participante = jira.getParticipante();
		builder.add( createObjectBuilder().add("id", participante.getId())
							 .add("nombre", participante.getNombreNullSafe())
							 .add("usuarioSVN", participante.getUsuarioSvn())
							 .add("correo", participante.getCorreo())
							 );
	}
}

class ObjectoCambioAgrupado{
	private ObjectoCambio objectoCambio;
	private int cantidadVeces;

	public ObjectoCambioAgrupado(ObjectoCambio objectoCambio) {
		this.objectoCambio = objectoCambio;
	}

	public ObjectoCambioAgrupado adicionarVeces(int cantidadAdicionada){
		cantidadVeces+=cantidadAdicionada;
		return this;
	}

	public int getCantidadVeces() {
		return cantidadVeces;
	}

	public ObjectoCambio getObjectoCambio() {
		return objectoCambio;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((objectoCambio == null) ? 0 : ( objectoCambio.getCambioTabla() .hashCode()) + objectoCambio.getNombre().hashCode() );
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ObjectoCambioAgrupado)) {
			return false;
		}
		ObjectoCambioAgrupado other = (ObjectoCambioAgrupado) obj;
		if (objectoCambio == null) {
			if (other.objectoCambio != null) {
				return false;
			}
		}
		 if (!objectoCambio.getCambioTabla().equals(other.objectoCambio.getCambioTabla())) {
				return false;
		 }

		 if (!objectoCambio.getNombre().equals(other.objectoCambio.getNombre())) {
				return false;
		 }

		return true;
	}

	public static ObjectoCambioAgrupado from(EntVersionCambioObjectoSql origen){
		return new ObjectoCambioAgrupado	(new ObjectoCambio(origen.getTipo(), origen.getObjecto(), Collections.emptyList()))
				.adicionarVeces(origen.getCantidad());
	}


}