package rd.huma.dashboard.servicios.transaccional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class ServicioJira {

	@Inject
	private EntityManager entityManager;
	
	
}