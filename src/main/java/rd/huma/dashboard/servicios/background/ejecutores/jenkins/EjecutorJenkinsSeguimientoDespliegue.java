package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.function.Consumer;

import javax.ws.rs.client.ClientBuilder;

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

	public EjecutorJenkinsSeguimientoDespliegue(String url,EntJobDespliegueVersion job,  Consumer<Boolean> handlerResult) {
		this(url,"lastBuild/api/json",job,handlerResult);
	}

	public EjecutorJenkinsSeguimientoDespliegue(String url, String sufijo,EntJobDespliegueVersion job,  Consumer<Boolean> handlerResult) {
		this.urlBase = url;
		this.url = url+sufijo;
		this.job = job;
		if (job.getURL()!=null){
			this.url = job.getURL()+"/api/json";
		}
		this.handlerResult = handlerResult;
	}

	@Override
	public void ejecutar() {
		if (job.getURL()!=null){
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
		}

		ServicioJobDespliegueVersion servicio = ServicioJobDespliegueVersion.getInstanciaTransaccional();

		JenkinsJobStatus jenkinsJob = ClientBuilder.newClient().target(url).request().get(JenkinsJobStatus.class);
		Actions[] acciones = jenkinsJob.getActions();
		if (acciones!=null && acciones.length>0){
			Parameters[] parametros = acciones[0].getParameters();
			if (parametros!=null && parametros.length>0){
				for (Parameters parameters : parametros) {
					if ( "version".equals(parameters.getName())){
						if (job.getVersion().getNumero().equals(parameters.getValue())){
							if (jenkinsJob.getResult() == null){
								job.setURL(jenkinsJob.getUrl());

								servicio.seguimientoJenkinsSeguimientoDespliegue(job, jenkinsJob.getUrl(),handlerResult);

								return;
							}
							boolean fallo = "FAILURE".equals(jenkinsJob.getResult());
							EEstadoJobDespliegue estado = fallo?EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS:EEstadoJobDespliegue.DEPLOY_JENKINS_EXITOSO;
							job.setJobNumber(jenkinsJob.getNumber());
							job.setURL(jenkinsJob.getUrl());
							servicio.cambiarEstado(job, estado);
							handlerResult.accept(fallo);
						}else{
							int numeroAnterior = Integer.valueOf(jenkinsJob.getNumber())-1;
							servicio.seguimientoJenkinsSeguimientoDespliegue(job, urlBase+numeroAnterior+"/api/json",handlerResult);
						}
						break;
					}
				}
			}
		}

	}
}