package rd.huma.dashboard.servicios.transaccional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.EntJira;

@Stateless
public class ServicioJira {

	@Inject
	private EntityManager entityManager;

	public static ServicioJira getInstanciaTransaccional() {
		return CDI.current().select(ServicioJira.class, ServicioJira.class.getAnnotations()).get();
	}

	public EntJira encuentraOSalva(String numero, String estado) {
		EntJira jira = entityManager.createNamedQuery("buscar.jiraNumero",EntJira.class).setParameter("numJira", numero) .getResultList().stream().findFirst().orElse(nuevoJira(numero));
		jira.setEstado(estado);
		jira = entityManager.find(EntJira.class, jira.getId());

		entityManager.persist(jira);
		return jira;
	}

	private EntJira nuevoJira(String numero) {
		EntJira jira = new EntJira();
		jira.setNumero(numero);
		entityManager.persist(jira);
		return jira;
	}
}