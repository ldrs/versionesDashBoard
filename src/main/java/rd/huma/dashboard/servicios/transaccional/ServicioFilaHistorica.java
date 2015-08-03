package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.model.transaccional.EntHistoricoDespliegue;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoFilaDeployement;

@Servicio
@Stateless
public class ServicioFilaHistorica {


	@Inject
	private EntityManager entityManager;

	public List<EntHistoricoDespliegue> getVersiones(String idAmbiente) {
		return entityManager.createNamedQuery("buscarPorAmbiente.historico", EntHistoricoDespliegue.class).setParameter("idAmbiente", idAmbiente).getResultList();
	}

	public void moverAHistorico(EntFilaDeployementVersion filaVersion, EntJobDespliegueVersion jobDespliegueVersion){
		EntHistoricoDespliegue historico = new EntHistoricoDespliegue();
		historico.setVersion(jobDespliegueVersion.getVersion());
		historico.setFila(filaVersion.getFila());
		historico.setEstado(EEstadoFilaDeployement.DEPLOY);
		historico.setJobDespliegueVersion(jobDespliegueVersion);
		entityManager.persist(historico);

		entityManager.remove(filaVersion);
	}
}