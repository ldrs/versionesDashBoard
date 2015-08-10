package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;

@Stateless
@Servicio
public class ServicioJira {

	@Inject
	private EntityManager entityManager;

	@Inject @Servicio
	private ServicioPersona servicioPersona;

	public static ServicioJira getInstanciaTransaccional() {
		Servicio servicio = ServicioJira.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioJira.class, servicio).get();
	}

	public Optional<EntJira> encuentra(String numero){
		return entityManager.createNamedQuery("buscar.jiraNumero",EntJira.class).setParameter("numJira", numero) .getResultList().stream().findFirst();
	}

	public Optional<EntJiraParticipante> encuentraParticipante(String usuario, String numeroJira){
		return entityManager.createNamedQuery("jiraParticipante.buscar",EntJiraParticipante.class).setParameter("usr", usuario).setParameter("numJira", numeroJira).getResultList().stream().findFirst();
	}

	public EntJira salva(EntJira jira){
		return entityManager.merge(jira);
	}

	public EntJira encuentraOSalva(String numero, String estado) {
		EntJira jira = encuentra(numero).orElse(nuevoJira(numero));
		jira.setEstado(estado);
		jira = entityManager.find(EntJira.class, jira.getId());

		entityManager.persist(jira);

		return jira;
	}

	public void salvarParticipante(EntJiraParticipante participante){
		if (encuentraParticipante(participante.getParticipante().getUsuarioSvn(), participante.getJira().getNumero()).isPresent()){
			return;
		}

		 encuentra(participante.getJira().getNumero()).ifPresent( j->  salvarParticipante(j, participante));
	}

	private void salvarParticipante(EntJira jira, EntJiraParticipante participante){
		EntJiraParticipante participanteFull = participante;
		participanteFull.setJira(jira);
		participanteFull.setParticipante(servicioPersona.buscaOCreaPersona(participante.getParticipante().getUsuarioSvn()));
		participanteFull.setTipo(participante.getTipo());
		entityManager.persist(participanteFull);
	}

	private EntJira nuevoJira(String numero) {
		EntJira jira = new EntJira();
		jira.setNumero(numero);
		entityManager.persist(jira);
		return jira;
	}
}