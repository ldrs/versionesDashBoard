package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createObjectBuilder;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;

@Path("branchConsulta")
public class WSBranchConsulta {


	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@GET
	@Path("consulta/{branch}")
	public String buscaVersionesBranch(@PathParam("branch") String branch){
		JsonArrayBuilder retorno = Json.createArrayBuilder();
		List<EntVersion> versiones =  servicioVersion.buscaPorBranch(branch);
		versiones.forEach( v -> retorno.add(toJson(v)));
		return retorno.build().toString();
	}

	private JsonObjectBuilder toJson(EntVersion version){
		return		createObjectBuilder()
		.add("numero", version.getNumero())
		.add("svnOrigen", version.getSvnOrigen())
		.add("svnRevision", version.getRevisionSVN())
		.add("estado", version.getEstado().name())
		.add("autor", version.getAutor().getNombreNullSafe())
		.add("fecha", UtilFecha.getFechaFormateada(version.getFechaRegistro()))
		.add("cantidadScripts", servicioVersion.contarScriptVersion(version))
		.add("cantidadReports", servicioVersion.contarReporteVersion(version))
		;
	}


}