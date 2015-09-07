package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntPersona;
import static rd.huma.dashboard.util.UtilString.*;
@Stateless
@Servicio
public class ServicioPersona {

	@Inject
	private EntityManager entityManager;


	public static ServicioPersona getInstanciaTransaccional(){
		Servicio servicio = ServicioPersona.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioPersona.class, servicio).get();
	}

	public List<EntPersona> buscarPersonas(){
		return entityManager.createNamedQuery("buscaPersonas",EntPersona.class).getResultList();
	}

	public EntPersona busca(String id){
		return entityManager.find(EntPersona.class, id);
	}

	public EntPersona buscaOCreaPersona(String usuarioSVN){
		return entityManager.createNamedQuery("buscaPersonaSVN", EntPersona.class)
			.setParameter("usrSVN", miniscula(usuarioSVN)).getResultList()
			.stream().findFirst()
			.orElse(creaPersona(usuarioSVN));
	}

	public Optional<EntPersona> buscaPorCorreo(String correo){
		return entityManager.createNamedQuery("buscaPersonaCorreo", EntPersona.class)
			.setParameter("mail", correo).getResultList()
			.stream().findFirst();
	}

	private EntPersona creaPersona(String usuarioSVN) {
		String correo =	usuarioSVN.replace('_', '.')+"@sigef.gov.do";
		synchronized(usuarioSVN){
			Optional<EntPersona> optionalPersona = buscaPorCorreo(correo);
			if (optionalPersona.isPresent()){
				return optionalPersona.get();
			}
		}

		EntPersona persona = new EntPersona();
		persona.setUsuarioSvn(usuarioSVN);
		persona.setCorreo(correo);


		entityManager.persist(persona);

		return persona;
	}

	public EntPersona actualiza(EntPersona persona) {
		return entityManager.merge(persona);
	}

	public EntPersona crearPersona(String nombre, String correo, String usuarioSVN) {
		EntPersona persona = new EntPersona();
		persona.setCorreo(correo);
		persona.setNombre(nombre);
		persona.setUsuarioSvn(usuarioSVN);

		entityManager.persist(persona);

		return persona;
	}
}