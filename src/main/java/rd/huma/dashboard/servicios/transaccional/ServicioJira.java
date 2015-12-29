package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraEstado;
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;
import rd.huma.dashboard.servicios.integracion.jira.CacheJiraEstado;

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


	public EntJira encuentraOSalva(String numero, EntJiraEstado entJiraEstado) {
		Optional<EntJira> posible =  encuentra(numero);
		if (posible.isPresent()){
			return posible.get();
		}

		synchronized(numero){
			posible =  encuentra(numero);
			if (posible.isPresent()){
				return posible.get();
			}

			EntJira jira = new EntJira();
			jira.setNumero(numero);
			if (entJiraEstado != null){
				jira.setJiraEstado(encuentraOSalvaJiraEstado(entJiraEstado.getCodigo(),entJiraEstado.getDescripcion()));
			}

			entityManager.persist(jira);
			return jira;
		}
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

	public List<EntJiraEstado> buscaEstado(String codigo){
		return entityManager.createNamedQuery("buscar.jiraEstado",EntJiraEstado.class).setParameter("cod", codigo).getResultList();
	}


	public EntJiraEstado encuentraOSalvaJiraEstado(String codigo, String descripcion){
		List<EntJiraEstado> resultado = buscaEstado(codigo);
		if (resultado.isEmpty()){

			synchronized(codigo){
				resultado = buscaEstado(codigo);
				if (resultado.isEmpty()){
					EntJiraEstado jiraEstado = new EntJiraEstado();
					jiraEstado.setCodigo(codigo);
					jiraEstado.setDescripcion(descripcion);
					entityManager.persist(jiraEstado);
					CacheJiraEstado.asignarJiraEstado(jiraEstado);
					return jiraEstado;
				}
			}
		}
		return resultado.stream().findFirst().get();
	}

	public List<EntJira> buscarTodos() {
		return entityManager.createNamedQuery("todos.jira",EntJira.class).getResultList();
	}

	public Optional<EntAmbiente> getAmbientePorEstado(EntJiraEstado estadoJiraServidor) {
		return entityManager.createNamedQuery("ambientePorEstado.ambienteJiraEstado",EntAmbiente.class).setParameter("est", estadoJiraServidor).getResultList().stream().findFirst();
	}
}