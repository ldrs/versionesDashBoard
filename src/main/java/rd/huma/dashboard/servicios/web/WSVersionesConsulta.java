package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.model.EntVersionJira;
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
						.add("autor", version.getAutor())
						.add("branchOrigen", version.getBranchOrigen())
						.add("fecha", version.getMomentoCreacion().toString())
						.add("jiras", consultaJiras(version))

					);
	}

	private JsonArrayBuilder consultaJiras(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaJiras(version).stream().forEach(j -> {agregaJira(builder, j);});
		return builder;
	}

	private void agregaJira(JsonArrayBuilder builder, EntVersionJira jira){
		builder.add(jira.getJira().getNumero());
	}
}