package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createObjectBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;

@Path("branchConsulta")
public class WSBranchConsulta {


	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@Inject
	private @Servicio ServicioBranch servicioBranch;

	@GET
	@Path("consulta/{branch}")
	public String buscaVersionesBranch(@PathParam("branch") String branch){
		JsonArrayBuilder jsonVersiones = Json.createArrayBuilder();
		List<EntVersion> versiones =  servicioVersion.buscaPorBranch(branch);
		Set<String> tickets = new HashSet<>();
		versiones.forEach( v -> jsonVersiones.add(toJson(branch,v,tickets)));

		JsonArrayBuilder ticketsJson = Json.createArrayBuilder();

		tickets.forEach(ticketsJson::add);

		return Json.createObjectBuilder().add("versiones", jsonVersiones).add("tickets", ticketsJson).build().toString();
	}

	private JsonObjectBuilder toJson(String branch, EntVersion version, Set<String> tickets){
		Optional<EntBranch> posibleBranch = servicioBranch.buscaBranch(branch);

		servicioVersion.buscaNumeroTicketsSegunBranch(branch).forEach(tickets::add);


		 JsonObjectBuilder origen = Json.createObjectBuilder();

		return		createObjectBuilder()
		.add("numero", version.getNumero())
		.add("versionId", version.getId())
		.add("svnOrigen", version.getSvnOrigen())
		.add("svnRevision", version.getRevisionSVN())
		.add("estado", version.getEstado().name())
		.add("autor", version.getAutor().getNombreNullSafe())
		.add("fecha", UtilFecha.getFechaFormateada(version.getFechaRegistro()))
		.add("cantidadScripts", servicioVersion.contarScriptVersion(version))
		.add("cantidadReports", servicioVersion.contarReporteVersion(version))
		.add("origen", posibleBranch.isPresent()? origen.add("branch", posibleBranch.get().getBranch()).add("revision", posibleBranch.get().getRevisionOrigen())  :origen)
		;
	}


}