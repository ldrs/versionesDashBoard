package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.EEstadoDeployement;
import rd.huma.dashboard.model.EEstadoVersion;
import rd.huma.dashboard.model.EntFilaDeployementVersion;

@Servicio
@Stateless
public class ServicioFila {
	
	@Inject
	private EntityManager entityManager;
	
	public List<EntFilaDeployementVersion> getFilasPorEstadoVersion(Set<EEstadoVersion> estados){
		return entityManager.createNamedQuery("buscarPorVersionEstado.fila",EntFilaDeployementVersion.class).setParameter("est", estados).getResultList();
	}
	
	public static ServicioFila getInstanciaTransaccional(){
		Servicio servicio = ServicioFila.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioFila.class, servicio).get();
	}

	public void salirFila(EntFilaDeployementVersion filaVersion,EEstadoDeployement estado) {
		entityManager.remove(filaVersion);		
	}
}