package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

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

	private EntPersona creaPersona(String usuarioSVN) {
		String correo =	usuarioSVN.replace('_', '.');

		EntPersona persona = new EntPersona();
		persona.setUsuarioSvn(usuarioSVN);
		persona.setCorreo(correo+"@sigef.gov.do");
		persona.setNombre(usuarioSVN.replace('_', ' '));

		entityManager.persist(persona);

		return persona;
	}
}