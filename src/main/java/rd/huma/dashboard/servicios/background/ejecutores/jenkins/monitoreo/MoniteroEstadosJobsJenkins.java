package rd.huma.dashboard.servicios.background.ejecutores.jenkins.monitoreo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorConfirmacionEjecucionScript;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorConfirmacionVersion;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorJenkinsSeguimientoDespliegue;
import rd.huma.dashboard.servicios.integracion.jenkins.ServicioJenkins;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;

class MoniteroEstadosJobsJenkins {

	private List<EntJobDespliegueVersion> jobs;
	private ServicioJobDespliegueVersion servicioJobs;
	private Map<String, ServicioJenkins> cacheJenkinsPorApp = new HashMap<>();

	public MoniteroEstadosJobsJenkins() {
		this.servicioJobs = ServicioJobDespliegueVersion.getInstanciaTransaccional();
		this.jobs = servicioJobs.buscaJobEstadosPendienteEjecucion();
	}

	public void ejecutar(){
		Instant hace5Minutos = Instant.now().minus(5, ChronoUnit.MINUTES);
		this.jobs.stream().filter(p -> p.getFechaRegistro().isAfter(hace5Minutos)) .forEach(this::buscarEstadoDespliegue);
	}

	private void buscarEstadoDespliegue(EntJobDespliegueVersion job){
		new EjecutorJenkinsSeguimientoDespliegue(getUrlPorJob(job), job, new ResultadoMonitoreo(job));
	}

	private String getUrlPorJob(EntJobDespliegueVersion job){
		return job.getTipoDespliegue().getUrlDeploy(getServicioJenkins(job.getVersion().getSvnOrigen()));
	}

	private ServicioJenkins getServicioJenkins(String app){
		return cacheJenkinsPorApp.computeIfAbsent(app, k ->   ServicioJenkins.para(ServicioAplicacion.getCacheAplicacion(app).get()));
	}
}

class ResultadoMonitoreo implements Consumer<Boolean>{
	private EntJobDespliegueVersion job;

	public ResultadoMonitoreo(EntJobDespliegueVersion job) {
		this.job = job;
	}

	@Override
	public void accept(Boolean resultado) {
		if (job.getTipoDespliegue() == ETipoDespliegueJob.SCRIPT && job.getTipoScript() == ETipoScript.ANTES_SUBIDA){
			new EjecutorConfirmacionEjecucionScript(job).ejecutarDespuesScript();
		}

		if (job.getTipoDespliegue() == ETipoDespliegueJob.SCRIPT){
			new EjecutorConfirmacionVersion(job).resultado(resultado);
		}

	}
}
