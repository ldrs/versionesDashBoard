package rd.huma.dashboard.servicios.web;

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

	@Servicio @Inject ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	@Inject
	private MonitorEjecutor monitorEjecutor;


	@GET
	@Path("script")
	public String confirmaScript(@QueryParam("id") String idJob, @QueryParam("urlJenkins") String urlJenkins, @QueryParam("inicioJob") String inicioJob){
		EntJobDespliegueVersion job = servicioJobDespliegueVersion.getJob(idJob);
		if (job == null){
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
			return "{}";
		}
		job.setURL(urlJenkins);
		job.setInicioJob(inicioJob);
		monitorEjecutor.ejecutarAsync(new EjecutorConfirmacionReporte(job));
		return "{}";
	}


}