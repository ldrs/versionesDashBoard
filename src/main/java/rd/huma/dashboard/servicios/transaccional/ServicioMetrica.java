package rd.huma.dashboard.servicios.transaccional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
@Servicio
public class ServicioMetrica {


	@Inject
	private EntityManager entityManager;
	
	public void getMetricaAplicacion(String idAplicacion){
		
		
		entityManager.createQuery("select count(j),(MONTH(J.fechaRegistro)) from EntJobDespliegueVersion J join J.version V join V.aplicacion A where A.id='"+idAplicacion + "' group by (MONTH(J.fechaRegistro))");
	}
	
}