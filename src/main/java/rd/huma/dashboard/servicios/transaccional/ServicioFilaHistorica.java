package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntHistoricoDeployement;

@Servicio
@Stateless
public class ServicioFilaHistorica {
	

	@Inject
	private EntityManager entityManager;

	public List<EntHistoricoDeployement> getVersiones(String idAmbiente) {
		return entityManager.createNamedQuery("buscarPorAmbiente.historico", EntHistoricoDeployement.class).setParameter("idAmbiente", idAmbiente).getResultList();
	}
}