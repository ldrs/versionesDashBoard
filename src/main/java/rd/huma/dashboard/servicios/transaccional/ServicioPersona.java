package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntPersona;

@Stateless
@Servicio
public class ServicioPersona {

	@Inject
	private EntityManager entityManager;

	public List<EntPersona> buscarPersonas(){
		return entityManager.createNamedQuery("buscaPersonas",EntPersona.class).getResultList();
	}

	public EntPersona buscaOCreaPersona(String usuarioSVN){
		return entityManager.createNamedQuery("buscaPersonaSVN", EntPersona.class)
			.setParameter("usrSVN", usuarioSVN).getResultList()
			.stream().findFirst()
			.orElse(creaPersona(usuarioSVN));
	}

	public Optional<EntPersona> buscaPorCorreo(String correo){
		return entityManager.createNamedQuery("buscaPersonaCorreo", EntPersona.class)
			.setParameter("mail", correo).getResultList()
			.stream().findFirst();
	}

	private EntPersona creaPersona(String usuarioSVN) {
		String correo =	usuarioSVN.replace('_', '.');

		EntPersona persona = new EntPersona();
		persona.setUsuarioSvn(usuarioSVN);
		persona.setCorreo(correo+"@sigef.gov.do");

		entityManager.persist(persona);

		return persona;
	}

	public EntPersona actualiza(EntPersona persona) {
		return entityManager.merge(persona);
	}
}