package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntAmbienteSVN;
import rd.huma.dashboard.model.transaccional.EntAmbienteSVNModulo;

@Stateless
@Servicio
public class ServicioModuloAmbienteSVN {


	@Inject
	private EntityManager entityManager;

	public static ServicioModuloAmbienteSVN getInstanciaTransaccional() {
		Servicio servicio = ServicioModuloAmbienteSVN.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioModuloAmbienteSVN.class, servicio).get();
	}

	public List<EntAmbienteSVN> getAmbientes() {
		return entityManager.createNamedQuery("ambienteSVN.todos",EntAmbienteSVN.class).getResultList();
	}
	
	public List<EntAmbienteSVNModulo> getModulos(String ruta) {
		return entityManager.createNamedQuery("ambienteSvnModulo.buscarRuta",EntAmbienteSVNModulo.class).setParameter("rut", ruta).getResultList();
	}

	public EntAmbienteSVN getAmbiente(String ruta) {
		 return entityManager.createNamedQuery("ambienteSVN.porRuta",EntAmbienteSVN.class).setParameter("ruta", ruta).getResultList().stream().findFirst().orElse(crearAmbiente(ruta));
	}

	private EntAmbienteSVN crearAmbiente(String ruta) {
		EntAmbienteSVN ambienteSVN = new EntAmbienteSVN();
		ambienteSVN.setRutaSvnAmbiente(ruta);
		entityManager.persist(ambienteSVN);
		return ambienteSVN;
	}
	
	public void buscarCrear(EntAmbienteSVNModulo modulo){
		Artefacto artefacto = modulo.getArtefacto(); 
		
		if (artefacto.getClasificador() == null){
			entityManager.createNamedQuery("ambienteSvnModulo.buscar",EntAmbienteSVNModulo.class)
						.setParameter("amb", modulo.getAmbienteSVN())
						.setParameter("grp",artefacto. getGrupo())
						.setParameter("paq", artefacto.getPaquete())
						.getResultList().stream().findFirst().orElse(crearAmbiente(modulo));
		
		}else{
			entityManager.createNamedQuery("ambienteSvnModulo.buscar",EntAmbienteSVNModulo.class)
			.setParameter("amb", modulo.getAmbienteSVN())
			.setParameter("grp", artefacto.getGrupo())
			.setParameter("paq", artefacto.getPaquete())
			.setParameter("claf", artefacto.getClasificador())
			.getResultList().stream().findFirst().orElse(crearAmbiente(modulo));
		}
	}
	
	private EntAmbienteSVNModulo crearAmbiente(EntAmbienteSVNModulo modulo){
		entityManager.persist(modulo);
		return modulo;
	}
}