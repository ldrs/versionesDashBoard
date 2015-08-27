package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedad;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;

@Path("filaDeploymentVersion")
public class WSFilaDeployementVersionFilas {
	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@Inject
	private @Servicio ServicioFila servicioFila;

	@GET
	@Path("{idAmbiente}")
	public String filas(@PathParam("idAmbiente") String idAmbiente ){
		JsonArrayBuilder builder = createArrayBuilder();
		getFilaDeploymentVersion(idAmbiente).stream().forEach(f -> builder.add(createObjectBuilder()
																				.add("id", f.getId())
																				.add("prioridad", f.getPrioridad())
																				.add("numero", f.getVersion().getNumero())
																				.add("versionId", f.getVersion().getId())
																				.add("autor", getAutor(f.getVersion()))
																				.add("duenos", consultaDuenos(f.getVersion()))
																				.add("branchOrigen", f.getVersion().getBranchOrigen())
																				.add("fechaVersion", UtilFecha.getFechaFormateada(f.getVersion().getFechaRegistro()))
																				.add("fechaFila", UtilFecha.getFechaFormateada(f.getFechaRegistro()))
																				.add("controles", "block")
																				.add("tickets", consultaTickets(f.getVersion()))
																				.add("propiedades", consultaPropiedades(f.getVersion()))
																				.add("cantidadScripts", servicioVersion.contarScriptVersion(f.getVersion()))
																				.add("cantidadReports", servicioVersion.contarReporteVersion(f.getVersion()))
																				.add("jiras", consultaJiras(f.getVersion()))
																				)
															 );
		return builder.build().toString();
	}

	private JsonObjectBuilder getAutor(EntVersion version){
		EntPersona autor = version.getAutor();
		return toJsonObjectBuilder(autor);
	}

	private JsonObjectBuilder toJsonObjectBuilder(EntPersona persona){
		return createObjectBuilder().add("id",persona.getId()).add("nombre", persona.getNombreNullSafe());
	}

	private List<EntFilaDespliegueVersion> getFilaDeploymentVersion(String id){
		return  servicioFila.getFilasPorAmbienteAplicacion(id);
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

	private JsonArrayBuilder consultaDuenos(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaParticipantes(version).stream().forEach(j -> builder.add(toJsonObjectBuilder(j.getParticipante())));
		return builder;
	}



	private JsonArrayBuilder consultaPropiedades(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();

		servicioVersion.buscaPropiedades(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private void agrega(JsonArrayBuilder builder, EntVersionJira jira){
		builder.add(jira.getJira().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionTicket jira){
		builder.add(jira.getTicketSysAid().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionPropiedad jira){
		if (jira.getValor()==null){
			return;
		}
		builder.add(createObjectBuilder().add(jira.getPropiedad(), jira.getValor()));
	}
}