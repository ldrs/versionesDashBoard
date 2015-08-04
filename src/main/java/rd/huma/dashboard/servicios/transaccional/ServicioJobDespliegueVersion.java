package rd.huma.dashboard.servicios.transaccional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorDespliegueVersionJenkins;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorJenkinsSeguimientoDespliegue;

@Stateless
@Servicio
public class ServicioJobDespliegueVersion {


	@Inject
	private EntityManager entityManager;

	@Inject
	private MonitorEjecutor monitorEjecutor;

	@Inject @Servicio
	private ServicioServidor servicioServidor;

	@Servicio @Inject
	private  ServicioFilaHistorica servicioFilaHistorica;

	public EntJobDespliegueVersion nuevoDeploy(EntServidor servidor, EntFilaDeployementVersion versionFila){
		servicioServidor.cambiaVersionServidor(servidor,versionFila.getVersion());

		EntJobDespliegueVersion job = new EntJobDespliegueVersion();
		job.setServidor(servidor);
		job.setVersion(versionFila.getVersion());
		entityManager.persist(job);

		monitorEjecutor.ejecutarAsync(new EjecutorDespliegueVersionJenkins(job));
		servicioFilaHistorica.moverAHistorico(versionFila,job);
		return job;
	}

	public static ServicioJobDespliegueVersion getInstanciaTransaccional() {
		Servicio servicio = ServicioJobDespliegueVersion.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioJobDespliegueVersion.class, servicio).get();
	}

	public void cambiarEstado(EntJobDespliegueVersion job, EEstadoJobDespliegue estado) {
		EntJobDespliegueVersion jobActualizado = entityManager.find(EntJobDespliegueVersion.class, job.getId());
		jobActualizado.setEstado(estado);
		entityManager.merge(jobActualizado);
		if (estado == EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS){
			servicioServidor.cambiaVersionServidor(job.getServidor(), null);
		}
	}

	public void seguimientoJenkinsSeguimientoDespliegue(EntJobDespliegueVersion job, String url) {
		monitorEjecutor.ejecutarAsync(new EjecutorJenkinsSeguimientoDespliegue(url, job));
	}}