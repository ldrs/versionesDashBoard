package rd.huma.dashboard.servicios.web;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorConfirmacionEjecucionScript;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorConfirmacionReporte;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorConfirmacionVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;

@Path("jobDespliegue")
public class WSJobDespliegue {
	private static final Logger LOGGER = Logger.getLogger(WSJobDespliegue.class.getSimpleName());


	@Servicio @Inject ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	@Inject
	private MonitorEjecutor monitorEjecutor;


	@GET
	@Path("script")
	public String confirmaScript(@QueryParam("id") String idJob, @QueryParam("urlJenkins") String urlJenkins, @QueryParam("inicioJob") String inicioJob){
		EntJobDespliegueVersion job = servicioJobDespliegueVersion.getJob(idJob);
		if (job == null){
			LOGGER.warning(String.format("El id %s del script no fue encontrado",idJob));
			return "{}";
		}
		job.setURL(urlJenkins);
		job.setInicioJob(inicioJob);
		monitorEjecutor.ejecutarAsync(new EjecutorConfirmacionEjecucionScript(job));
		return "{}";
	}

	@GET
	@Path("version")
	public String confirmaVersion(@QueryParam("id") String idJob, @QueryParam("urlJenkins") String urlJenkins, @QueryParam("inicioJob") String inicioJob){
		EntJobDespliegueVersion job = servicioJobDespliegueVersion.getJob(idJob);
		if (job == null){
			LOGGER.warning(String.format("El id %s de la version no fue encontrado",idJob));
			return "{}";
		}
		job.setURL(urlJenkins);
		job.setInicioJob(inicioJob);
		monitorEjecutor.ejecutarAsync(new EjecutorConfirmacionVersion(job));
		return "{}";
	}

	@GET
	@Path("reporte")
	public String confirmaReporte(@QueryParam("id") String idJob, @QueryParam("urlJenkins") String urlJenkins, @QueryParam("inicioJob") String inicioJob){
		EntJobDespliegueVersion job = servicioJobDespliegueVersion.getJob(idJob);
		if (job == null){
			LOGGER.warning(String.format("El id %s del reporte no fue encontrado",idJob));
			return "{}";
		}
		job.setURL(urlJenkins);
		job.setInicioJob(inicioJob);
		monitorEjecutor.ejecutarAsync(new EjecutorConfirmacionReporte(job));
		return "{}";
	}


}