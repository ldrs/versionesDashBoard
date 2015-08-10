package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.Arrays;
import java.util.function.Consumer;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import rd.huma.dashboard.model.jenkins.Actions;
import rd.huma.dashboard.model.jenkins.JenkinsJobStatus;
import rd.huma.dashboard.model.jenkins.Parameters;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;

public class EjecutorJenkinsSeguimientoDespliegue extends AEjecutor {

	private String url;
	private String urlBase;
	private EntJobDespliegueVersion job;
	private Consumer<Boolean> handlerResult;
	private ServicioJobDespliegueVersion servicio;
	private JenkinsJobStatus jenkinsJob;

	public EjecutorJenkinsSeguimientoDespliegue(String url,EntJobDespliegueVersion job,  Consumer<Boolean> handlerResult) {
		this(url,"lastBuild/api/json",job,handlerResult);
	}

	public EjecutorJenkinsSeguimientoDespliegue(String url, String sufijo,EntJobDespliegueVersion job,  Consumer<Boolean> handlerResult) {
		this.urlBase = url;
		this.url = url+sufijo;
		this.job = job;
		if (job == null){
			throw new IllegalArgumentException("No puede ser nulo el job");
		}
		if (job.getURL()!=null){
			this.url = job.getURL()+"/api/json";
		}
		this.handlerResult = handlerResult;
	}

	@Override
	public void ejecutar() {
		esperaSiEstaEnSeguimiento();

		Response respuesta  = ClientBuilder.newClient().target(url).request().buildGet().invoke();
		if (respuesta.getStatus()!=200){
			return;//TODO hacer log
		}
		servicio = ServicioJobDespliegueVersion.getInstanciaTransaccional();

		jenkinsJob = respuesta.readEntity(JenkinsJobStatus.class);
		Actions[] acciones = jenkinsJob.getActions();
		if (isEjecucionJobRetornoErroneo(acciones)){
			return;
		}
		Arrays.stream(acciones[0].getParameters()).filter(p -> "version".equals(p.getName())).findFirst().ifPresent(this::encontradoParametro);
	}

	private void esperaSiEstaEnSeguimiento(){
		if (job.getURL()!=null){
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
		}
	}

	private boolean isEjecucionJobRetornoErroneo(Actions[] acciones){
		return ( acciones==null || acciones.length==0 || acciones[0].getParameters() == null || acciones[0].getParameters().length==0);
	}

	private void encontradoParametro(Parameters parameter){
		if (job.getVersion().getNumero().equals(parameter.getValue())){
			encontradaVersion(jenkinsJob, servicio);
		}else{
			seguirBuscandoVersion(jenkinsJob,servicio);
		}
	}

	private void seguirBuscandoVersion(JenkinsJobStatus jenkinsJob,ServicioJobDespliegueVersion servicio){
		int numeroAnterior = Integer.valueOf(jenkinsJob.getNumber())-1;
		servicio.seguimientoJenkinsSeguimientoDespliegue(job, urlBase+numeroAnterior+"/api/json",handlerResult);
	}

	private void encontradaVersion(JenkinsJobStatus jenkinsJob,ServicioJobDespliegueVersion servicio ){
		if (sinResultado(jenkinsJob)){
			job.setURL(jenkinsJob.getUrl());
			servicio.seguimientoJenkinsSeguimientoDespliegue(job, jenkinsJob.getUrl(),handlerResult);
		}else{
			manejaResultado(jenkinsJob,servicio);
		}
	}

	private boolean sinResultado(JenkinsJobStatus jenkinsJob){
		return jenkinsJob.getResult() == null;
	}

	private void manejaResultado(JenkinsJobStatus jenkinsJob,ServicioJobDespliegueVersion servicio){
		boolean fallo = "FAILURE".equals(jenkinsJob.getResult());
		EEstadoJobDespliegue estado = fallo?EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS:EEstadoJobDespliegue.DEPLOY_JENKINS_EXITOSO;
		job.setJobNumber(jenkinsJob.getNumber());
		job.setURL(jenkinsJob.getUrl());
		servicio.cambiarEstado(job, estado);
		handlerResult.accept(!fallo);
	}
}