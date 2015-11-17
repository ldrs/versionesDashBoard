package rd.huma.dashboard.servicios.web;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.servicios.utilitarios.ServicioGeneracionZipFileFromUrls;

@Path("versionArchivo")
public class WSVersionArchivo {

	private static final Logger LOGGER = Logger.getLogger(WSVersionArchivo.class.getName());

	@Inject @Servicio
	private  ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	@Inject @Servicio
	private  ServicioVersion servicioVersion;


	@Inject @Servicio
	private ServicioRepositorioDatos servicioRepositorioDatos;

	@GET
	@Produces("application/zip")
	@Path("script/{id}")
	public Response retornaScript(@PathParam("id") String idJob){
		EntJobDespliegueVersion jobDespliegueVersion = servicioJobDespliegueVersion.getJob(idJob);
		if (jobDespliegueVersion == null){
			return Response.serverError().entity("No existe este job id").build();
		}

		LOGGER.info(String.format("Creando el archivo zip para la version %s",jobDespliegueVersion.getVersion().getNumero()));

		List<EntRepositorioDatosScriptEjecutados> scripts = servicioRepositorioDatos.getScriptEjecutadosPorJob(idJob);


		ResponseBuilder response;
		ServicioGeneracionZipFileFromUrls servicio =  new ServicioGeneracionZipFileFromUrls(jobDespliegueVersion.getVersion().getNumero(), scripts.stream().map(EntRepositorioDatosScriptEjecutados::getScript)
																															.map(EntVersionScript::getUrlScript)
																																							.collect(Collectors.toList()));
		File fileSQL = servicio.setQuery(true).generar();
		response = Response.ok((Object) fileSQL);
		response.header("Content-Disposition","attachment; filename=\"script.zip\"");


		return response.build();
	}

	@GET
	@Produces("application/zip")
	@Path("reporte/{id}")
	public Response retornaReporte(@PathParam("id") String scriptId){
		EntJobDespliegueVersion jobDespliegueVersion = servicioJobDespliegueVersion.getJob(scriptId);

		if (jobDespliegueVersion == null){
			return Response.serverError().entity("No existe este job id").build();
		}


		List<EntVersionReporte> reportes = servicioVersion.buscaReportesVersion(jobDespliegueVersion.getVersion());
		ResponseBuilder response;
		ServicioGeneracionZipFileFromUrls servicio =  new ServicioGeneracionZipFileFromUrls(jobDespliegueVersion.getVersion().getNumero(), reportes.stream().map(EntVersionReporte::getReporte).collect(Collectors.toList()));
			File fileSQL = servicio.generar();
			response = Response.ok((Object) fileSQL);
			response.header("Content-Disposition",	"attachment; filename=\"reporte.zip\"");


		return response.build();
	}

}
