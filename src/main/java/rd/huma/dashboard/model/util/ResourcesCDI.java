package rd.huma.dashboard.model.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ResourcesCDI {


    @PersistenceContext
    @Produces
    private EntityManager em;

    EntityManager getEm() {
		return em;
	}
}
