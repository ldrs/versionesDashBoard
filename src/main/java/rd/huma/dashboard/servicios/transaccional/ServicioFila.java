package rd.huma.dashboard.servicios.transaccional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.servicios.FilaBranch;
import rd.huma.dashboard.model.transaccional.EEstadoVersion;
import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;

@Servicio
@Stateless
public class ServicioFila {
	
	Function<Object[], FilaBranch> toFilaBranch = new Function<Object[], FilaBranch>() {
	    public FilaBranch apply(Object[] t) {
	        return new FilaBranch((EntFilaDeployement) t[0], (String)t[1]);
	    }
	};
	
	@Inject
	private EntityManager entityManager;
	
	public List<EntFilaDeployementVersion> getFilas(FilaBranch filaBranch){
		return entityManager.createNamedQuery("buscarPorFilaBranch.fila",EntFilaDeployementVersion.class)
						.setParameter("fil", filaBranch.getFila())
						.setParameter("bra", filaBranch.getBranch())
						.getResultList();
	}
	
	public List<EntFilaDeployementVersion> getFilasPorEstadoVersion(Set<EEstadoVersion> estados){
		return entityManager.createNamedQuery("buscarPorVersionEstado.fila",EntFilaDeployementVersion.class).setParameter("est", estados).getResultList();
	}
	
	public static ServicioFila getInstanciaTransaccional(){
		Servicio servicio = ServicioFila.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioFila.class, servicio).get();
	}

	public void salirFila(EntFilaDeployementVersion filaVersion) {
		entityManager.remove(filaVersion);		
	}

	public List<FilaBranch> getFilasPorBranchDuplicado(Set<EEstadoVersion> estados) {
		List<FilaBranch> filas = new ArrayList<FilaBranch>();
		entityManager.createNamedQuery("buscarPorDuplicacion.fila",Object[].class).setParameter("est", estados).getResultList()
		
		.stream().forEach(o-> filas.add(toFilaBranch.apply(o)));
		return filas;
	}
	
	public void ordenarFila(EntFilaDeployement filaHeader){
		AtomicInteger entero = new AtomicInteger(1);
		entityManager.createNamedQuery("buscarPorFila.fila",EntFilaDeployementVersion.class).setParameter("fil", filaHeader)
		.getResultList().forEach(f -> procesarOrden(f, entero.getAndIncrement()));
	}
	
	private void procesarOrden(EntFilaDeployementVersion fila, int prioridad){
		EntFilaDeployementVersion f = entityManager.getReference(EntFilaDeployementVersion.class, fila.getId());
		f.setPrioridad(prioridad);
		entityManager.persist(f);
	}
}