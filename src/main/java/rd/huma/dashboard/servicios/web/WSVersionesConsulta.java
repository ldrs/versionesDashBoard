package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedad;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

@Path("/versionConsulta")
public class WSVersionesConsulta {

	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@GET
	@Produces("text/plain")
	public String consulta(){
		JsonArrayBuilder builder = createArrayBuilder();

		servicioVersion.buscaUltimaVersiones().stream().forEach(v ->  consultaInt(builder, v));
		return builder.build().toString();
	}

	private void consultaInt(JsonArrayBuilder builder, EntVersion version){
		builder.add(createObjectBuilder()
						.add("version", version.getNumero())
						.add("estado", version.getEstado().name())
						.add("autor", version.getAutor().getNombreNullSafe())
						.add("branchOrigen", version.getBranchOrigen())
						.add("fecha", version.getFechaRegistro().toString())
						.add("jiras", consultaJiras(version))
						.add("tickets", consultaTickets(version))
						.add("propiedades", consultaPropiedades(version))
						.add("duenos", consultaDuenos(version))
					);
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


	private JsonArrayBuilder consultaDuenos(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaParticipantes(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private void agrega(JsonArrayBuilder builder, EntVersionJira jira){
		builder.add(jira.getJira().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionTicket jira){
		builder.add(jira.getTicketSysAid().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionPropiedad jira){
		builder.add(createObjectBuilder().add(jira.getPropiedad(), jira.getValor()));
	}

	private void agrega(JsonArrayBuilder builder, EntVersionParticipante jira){
		builder.add(jira.getParticipante().getUsuarioSvn());
	}
}