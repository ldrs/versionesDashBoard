package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersionDueno;
import rd.huma.dashboard.model.transaccional.EntHistoricoDespliegue;
import rd.huma.dashboard.model.transaccional.EntHistoricoDespliegueDueno;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoFilaDeployement;

@Servicio
@Stateless
public class ServicioFilaHistorica {


	@Inject
	private EntityManager entityManager;

	@Inject @Servicio
	private ServicioFila servicioFila;

	public List<EntHistoricoDespliegue> getVersiones(String idAmbiente) {
		return entityManager.createNamedQuery("buscarPorAmbiente.historico", EntHistoricoDespliegue.class).setParameter("idAmbiente", idAmbiente).getResultList();
	}

	public List<EntHistoricoDespliegue> getVersionesPorAplicacion(String aplicacionNombre) {
		return entityManager.createNamedQuery("buscarPorAplicacion.historico", EntHistoricoDespliegue.class).setParameter("app", aplicacionNombre).getResultList();
	}


	public void moverAHistorico(EntFilaDespliegueVersion filaVersion, EntJobDespliegueVersion jobDespliegueVersion){


		EntHistoricoDespliegue historico = new EntHistoricoDespliegue();
		historico.setVersion(jobDespliegueVersion.getVersion());
		historico.setFila(filaVersion.getFila());
		historico.setEstado(EEstadoFilaDeployement.DEPLOY);
		historico.setJobDespliegueVersion(jobDespliegueVersion);
		entityManager.persist(historico);

		servicioFila.getDuenosVersion(filaVersion.getVersion()).forEach(dueno -> moverHistoricoDueno(historico,dueno));

		entityManager.remove(filaVersion);
	}

	private void moverHistoricoDueno(EntHistoricoDespliegue historicoDespliegue, EntFilaDespliegueVersionDueno dueno){
		EntHistoricoDespliegueDueno historico = new EntHistoricoDespliegueDueno();
		historico.setHistoricoDespliegue(historicoDespliegue);
		historico.setDueno(dueno.getDueno());
		entityManager.persist(historico);
		entityManager.remove(dueno);
	}

	public void moverAHistorico(EntFilaDespliegue filaDespliegue,	EntVersion version, EntJobDespliegueVersion jobDespliegueVersion) {
		servicioFila.getFilas(filaDespliegue,version).forEach(fila -> moverAHistorico(fila, jobDespliegueVersion));
	}
}