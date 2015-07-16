package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntGrupoPersona;
import rd.huma.dashboard.model.transaccional.EntGrupoPersonaDetalle;
import rd.huma.dashboard.model.transaccional.EntPersona;

@Servicio
@Stateless
public class ServicioGrupo {

	@Inject
	private EntityManager entityManager;

	public EntGrupoPersona nuevoGrupo(String grupo){
		EntGrupoPersona grupoPersona = new EntGrupoPersona();
		grupoPersona.setGrupo(grupo);
		entityManager.persist(grupoPersona);
		return grupoPersona;
	}

	public EntGrupoPersona cambiaGrupo(EntGrupoPersona grupoPersona, String grupo){
		grupoPersona.setGrupo(grupo);
		entityManager.merge(grupoPersona);
		return grupoPersona;
	}

	public List<EntGrupoPersona> grupos(){
		return entityManager.createNamedQuery("todos.grupoPersona",EntGrupoPersona.class).getResultList();
	}


	public EntGrupoPersonaDetalle agregarDetalle(String idGrupo,  String idPersona){
		EntGrupoPersona grupoPersona = entityManager.find(EntGrupoPersona.class, idGrupo);
		EntPersona persona = entityManager.find(EntPersona.class, idPersona);
		EntGrupoPersonaDetalle detalle = new EntGrupoPersonaDetalle();
		detalle.setGrupoPersona(grupoPersona);
		detalle.setPersona(persona);
		entityManager.persist(detalle);
		return detalle;
	}

	public void borrarDetalle(String idGrupo, String idPersona){

		EntGrupoPersona grupoPersona = entityManager.find(EntGrupoPersona.class, idGrupo);
		EntPersona persona = entityManager.find(EntPersona.class, idPersona);
		entityManager.createNamedQuery("busca.detalleGrupo",EntGrupoPersonaDetalle.class)
					 .setParameter("grupo", grupoPersona)
					 .setParameter("persona", persona)
					 .getResultList().stream().findFirst().ifPresent(entityManager::remove);

	}

	public List<EntGrupoPersonaDetalle> buscarDetallePorGrupo(String idGrupo){
		return entityManager.createNamedQuery("buscaPorGrupo.detalleGrupo",EntGrupoPersonaDetalle.class).setParameter("idGrupo", idGrupo).getResultList();
	}

}