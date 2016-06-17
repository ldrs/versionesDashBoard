package rd.huma.dashboard.servicios.transaccional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAmbienteDueno;
import rd.huma.dashboard.model.transaccional.EntAmbienteParticipanteResponsable;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntGrupoPersonaDetalle;
import rd.huma.dashboard.model.transaccional.EntPersona;

@Stateless
@Servicio
public class ServicioAmbiente {

	@Inject
	private EntityManager entityManager;

	private @Inject @Servicio ServicioGrupo servicioGrupo;

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

	public Set<EntPersona> getResponsables(EntAmbiente entAmbiente) {
		Set<EntPersona> personas = new HashSet<>();
		List<EntAmbienteParticipanteResponsable> responsables = entityManager.createNamedQuery("buscarPorAmbiente.ambParcipanteResponsable",EntAmbienteParticipanteResponsable.class).setParameter("amb", entAmbiente).getResultList();
		for (EntAmbienteParticipanteResponsable responsable : responsables) {
			servicioGrupo.buscarDetallePorGrupo(responsable.getResponsables().getId()).stream().map(EntGrupoPersonaDetalle::getPersona).forEach(p -> personas.add(p));
		}
		return personas;
	}

	public List<EntAmbienteAplicacion> getAmbientes(){
		return entityManager.createQuery(" FROM EntAmbienteAplicacion amb ORDER BY amb.ambiente.orden ASC", EntAmbienteAplicacion.class).getResultList();
	}

	public EntAmbienteAplicacion getAmbienteAplicacion(String id) {
		return entityManager.createNamedQuery("buscarUnico.ambienteAplicacion",EntAmbienteAplicacion.class).setParameter("app", id).getSingleResult();
	}

}