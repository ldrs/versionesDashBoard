package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.function.Consumer;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.model.transaccional.dominio.ETipoUndeploy;
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
		servicioServidor.cambiaVersionServidor(servidor,versionFila.getVersion(),null,null);

		EntJobDespliegueVersion job = nuevoDeploy(servidor, versionFila.getFila(), versionFila.getVersion());

		servicioFilaHistorica.moverAHistorico(versionFila,job);
		return job;
	}

	public EntJobDespliegueVersion nuevoDeploy(EntServidor servidor, EntFilaDespliegue fila, EntVersion version){

		EntJobDespliegueVersion job = new EntJobDespliegueVersion();
		job.setServidor(servidor);
		job.setVersion(version);
		job.setFilaDespliegue(fila);
		entityManager.persist(job);

		monitorEjecutor.ejecutarAsync(new EjecutorDespliegueVersionJenkins(job));
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
		if (job.getTipoDespliegue() == ETipoDespliegueJob.VERSION && estado == EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS){
			servicioServidor.cambiaVersionServidor(job.getServidor(), null,null,ETipoUndeploy.AUTOMOMATICO_POR_FALLIDO_JENKINS);
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