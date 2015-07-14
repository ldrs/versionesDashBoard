package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAmbienteSVN;

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

	public EntAmbienteSVN getAmbiente(String ruta) {
		 return entityManager.createNamedQuery("ambienteSVN.porRuta",EntAmbienteSVN.class).setParameter("ruta", ruta).getResultList().stream().findFirst().orElse(crearAmbiente(ruta));
	}

	private EntAmbienteSVN crearAmbiente(String ruta) {
		EntAmbienteSVN ambienteSVN = new EntAmbienteSVN();
		ambienteSVN.setRutaSvnAmbiente(ruta);
		entityManager.persist(ambienteSVN);
		return ambienteSVN;
	}
}