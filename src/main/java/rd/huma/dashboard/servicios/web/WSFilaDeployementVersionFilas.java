package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionDuenos;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedades;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.servicios.web.simulacion.SimulaFila;
import rd.huma.dashboard.servicios.web.simulacion.SimulaVersion;

@Path("filaDeploymentVersion")
public class WSFilaDeployementVersionFilas {
	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@GET
	@Path("{idAmbiente}")
	public String filas(@PathParam("idAmbiente") String idAmbiente ){
		JsonArrayBuilder builder = createArrayBuilder();
		getFilaDeploymentVersion(idAmbiente).stream().forEach(f -> builder.add(createObjectBuilder()
																				.add("id", f.getId())
																				.add("prioridad", f.getPrioridad())
																				.add("numero", f.getVersion().getNumero())
																				.add("autor", f.getVersion().getAutor())
																				.add("duenos", consultaDuenos(f.getVersion()))
																				.add("branchOrigen", f.getVersion().getBranchOrigen())
																				.add("fechaVersion", f.getVersion().getMomentoCreacion().toString())
																				.add("controles", "block")
																				.add("tickets", consultaTickets(f.getVersion()))
																				.add("propiedades", consultaPropiedades(f.getVersion()))
																				.add("jiras", consultaJiras(f.getVersion())))
													);
		return builder.build().toString();
	}
	


	private List<EntFilaDeployementVersion> getFilaDeploymentVersion(String id){
		return SimulaFila.filas(id);
	}
	
	private JsonArrayBuilder consultaJiras(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		SimulaVersion.getJiras().getOrDefault(version, Collections.emptyList()).stream().forEach(j -> {agrega(builder, j);});
		//servicioVersion.buscaJiras(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private JsonArrayBuilder consultaTickets(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		SimulaVersion.getTickets().getOrDefault(version, Collections.emptyList()).stream().forEach(j -> {agrega(builder, j);});
		//servicioVersion.buscaTickets(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private JsonArrayBuilder consultaDuenos(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		SimulaVersion.getDuenos().getOrDefault(version, Collections.emptyList())  .stream().forEach(j -> {agrega(builder, j);});
		//servicioVersion.buscaDuenos(version).stream().forEach(j -> {agrega(builder, j);});
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

	private void agrega(JsonArrayBuilder builder, EntVersionPropiedades jira){
		builder.add(createObjectBuilder().add(jira.getPropiedad(), jira.getValor()));
	}

	private void agrega(JsonArrayBuilder builder, EntVersionDuenos jira){
		builder.add(jira.getDueno().getUsuarioSvn());
	}

	
}
