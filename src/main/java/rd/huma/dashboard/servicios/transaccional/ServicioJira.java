package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraEstado;

@Stateless
@Servicio
public class ServicioJira {

	@Inject
	private EntityManager entityManager;

	public static ServicioJira getInstanciaTransaccional() {
		Servicio servicio = ServicioJira.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioJira.class, servicio).get();
	}
	
	public Optional<EntJira> encuentra(String numero){
		return entityManager.createNamedQuery("buscar.jiraNumero",EntJira.class).setParameter("numJira", numero) .getResultList().stream().findFirst();
	}

	public EntJira encuentraOSalva(String numero, String estado) {
		EntJira jira = encuentra(numero).orElse(nuevoJira(numero));
		jira.setEstado(estado);
		jira = entityManager.find(EntJira.class, jira.getId());

		entityManager.persist(jira);
		
		EntJiraEstado estadoJira = new EntJiraEstado();
		estadoJira.setEstado(estado);
		estadoJira.setJira(jira);
		entityManager.persist(estadoJira);
		return jira;
	}

	private EntJira nuevoJira(String numero) {
		EntJira jira = new EntJira();
		jira.setNumero(numero);
		entityManager.persist(jira);
		return jira;
	}
}