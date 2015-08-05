package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

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



	public EjecutorJenkinsSeguimientoDespliegue(String url,EntJobDespliegueVersion job) {
		this(url,"lastBuild/api/json",job);
	}

	public EjecutorJenkinsSeguimientoDespliegue(String url, String sufijo,EntJobDespliegueVersion job) {
		this.urlBase = url;
		this.url = url+sufijo;
		this.job = job;
		if (job.getURL()!=null){
			this.url = job.getURL()+"/api/json";
		}
	}

	@Override
	public void ejecutar() {
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

								servicio.seguimientoJenkinsSeguimientoDespliegue(job, jenkinsJob.getUrl());

								return;
							}

							EEstadoJobDespliegue estado = "FAILURE".equals(jenkinsJob.getResult())?EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS:EEstadoJobDespliegue.DEPLOY_JENKINS_EXITOSO;
							job.setJobNumber(jenkinsJob.getNumber());
							job.setURL(jenkinsJob.getUrl());
							servicio.cambiarEstado(job, estado);
						}else{
							int numeroAnterior = Integer.valueOf(jenkinsJob.getNumber())-1;
							servicio.seguimientoJenkinsSeguimientoDespliegue(job, urlBase+numeroAnterior+"/api/json");
						}
						break;
					}
				}
			}
		}

	}
}