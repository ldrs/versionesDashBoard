package rd.huma.dashboard.servicios.transaccional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.EntTicketSysAid;

@Stateless
public class ServicioTicketSysaid {

	@Inject
	private EntityManager entityManager;


	public EntTicketSysAid encuentraOSalva(String numero) {
		EntTicketSysAid jira = entityManager.createNamedQuery("buscar.versionTicket",EntTicketSysAid.class).setParameter("num", numero) .getResultList().stream().findFirst().orElse(nuevoJira(numero));
		jira = entityManager.find(EntTicketSysAid.class, jira.getId());

		entityManager.persist(jira);
		return jira;
	}

	private EntTicketSysAid nuevoJira(String numero) {
		EntTicketSysAid jira = new EntTicketSysAid();
		jira.setNumero(numero);
		entityManager.persist(jira);
		return jira;
	}
}