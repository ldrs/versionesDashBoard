package rd.huma.dashboard.servicios.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.version.script.EjecutorProcesaResultadoScripts;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;

@Path("log")
public class WSLogScript {

	@Servicio @Inject ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	@Inject
	private MonitorEjecutor monitorEjecutor;

	@GET
	@Path("script/{id}")
	public String buscaLogScript(@PathParam("id") String id){
		EntJobDespliegueVersion job = servicioJobDespliegueVersion.getJob(id);
		if (job == null){
			//LOGGER.warning(String.format("El id %s del script no fue encontrado",idJob));
			return "{ejecuto:false}";
		}

		java.nio.file.Path path = Paths.get("/logs/scripts/"+job.getId());
		File filePath = path.toFile();
		if (Files.exists(path) && filePath.isDirectory()){
			monitorEjecutor.ejecutarAsync(new EjecutorProcesaResultadoScripts(path));
			return "{ejecuto:true}";
		}
		return "{ejecuto:false}";
	}
}
