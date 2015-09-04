package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.function.Consumer;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
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

	@Servicio @Inject
	private  ServicioVersion servicioVersion;

	public List<EntJobDespliegueVersion> buscarJobPorIdVersion(String id){
		return entityManager.createNamedQuery("buscaPorId.jobDespliegue",EntJobDespliegueVersion.class).setParameter("id", id).getResultList();
	}

	public EntJobDespliegueVersion nuevoDeploy(EntServidor servidor, EntFilaDespliegueVersion versionFila){
		servicioServidor.cambiaVersionServidor(servidor,versionFila.getVersion());

		EntJobDespliegueVersion job = new EntJobDespliegueVersion();
		job.setServidor(servidor);
		job.setVersion(versionFila.getVersion());
		job.setFilaDespliegue(versionFila.getFila());
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
		if (estado.getEstadoVersionRelativo()!=null){
			servicioVersion.actualizarEstado(estado.getEstadoVersionRelativo(), job.getVersion());
		}

	}

	public void seguimientoJenkinsSeguimientoDespliegue(EntJobDespliegueVersion job, String url, Consumer<Boolean> handlerResult) {
		monitorEjecutor.ejecutarAsync(new EjecutorJenkinsSeguimientoDespliegue(url, job, handlerResult));
	}

	public void nuevoJob(EntJobDespliegueVersion jobScript) {
		entityManager.persist(jobScript);
	}

	public EntJobDespliegueVersion getJob(String id){
		return entityManager.find(EntJobDespliegueVersion.class, id);
	}

	public EntJobDespliegueVersion buscarPorJobRelacionado(EntJobDespliegueVersion jobDeployVersion) {
		return entityManager.createNamedQuery("buscaPorVersionTipo.jobDespliegue",EntJobDespliegueVersion.class)
		.setParameter("ver", jobDeployVersion.getVersion())
		.setParameter("tipo", ETipoDespliegueJob.VERSION)
		.setParameter("est", EEstadoJobDespliegue.ESPERANDO_DEPLOY).getResultList().stream().findFirst().orElse(null);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> buscaVersionesAgrupadasPorTipo(ETipoDespliegueJob version) {
		return  entityManager.createNamedQuery("metricaAgrupadaYear.jobDespliegue").setParameter("tipo", version).getResultList();
	}

	public List<EntJobDespliegueVersion> buscaPorBranch(String branch, ETipoDespliegueJob  tipo){
		return entityManager.createNamedQuery("buscaPorBranch.jobDespliegue",EntJobDespliegueVersion.class).setParameter("branch", branch)
				   .setParameter("tipo", tipo)
				   .getResultList();
	}
	public List<EntJobDespliegueVersion> buscaPorBranch(String branch){
		return buscaPorBranch(branch, ETipoDespliegueJob.VERSION);
	}
}