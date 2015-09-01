package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAmbienteDueno;
import rd.huma.dashboard.model.transaccional.EntAplicacion;

@Stateless
@Servicio
public class ServicioAmbiente {

	@Inject
	private EntityManager entityManager;

	public static ServicioAmbiente getInstanciaTransaccional(){
		Servicio servicio = ServicioAmbiente.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioAmbiente.class, servicio).get();
	}

	public EntAmbiente nuevoAmbiente(String nombre, int orden){
		EntAmbiente ambiente = new EntAmbiente();
		ambiente.setNombre(nombre);
		ambiente.setOrden(orden);
		entityManager.persist(ambiente);
		return ambiente;
	}

	public EntAmbienteAplicacion nuevoAmbienteAplicacion(EntAmbiente ambiente, EntAplicacion aplicacion){
		EntAmbienteAplicacion ambienteAplicacion = new EntAmbienteAplicacion();
		ambienteAplicacion.setAmbiente(ambiente);
		ambienteAplicacion.setAplicacion(aplicacion);
		entityManager.persist(ambienteAplicacion);
		return ambienteAplicacion;
	}

	public Optional<EntAmbiente> getAmbiente(String id) {
		 return Optional.ofNullable(entityManager.find(EntAmbiente.class, id));
	}

	public List<EntAmbienteAplicacion> getAmbientesAplicacion(String id) {
		return entityManager.createNamedQuery("buscar.ambienteAplicacion",EntAmbienteAplicacion.class).setParameter("app", id).getResultList();
	}

	public List<EntAmbienteDueno> getDuenos(EntAmbiente entAmbiente) {
		return entityManager.createNamedQuery("buscarPorAmbiente",EntAmbienteDueno.class).setParameter("amb", entAmbiente).getResultList();
	}

}