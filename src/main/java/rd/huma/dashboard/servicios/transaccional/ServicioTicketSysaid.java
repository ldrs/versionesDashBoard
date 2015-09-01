package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntTicketSysAid;

@Stateless
@Servicio
public class ServicioTicketSysaid {

	@Inject
	private EntityManager entityManager;


	public EntTicketSysAid encuentraOSalva(String numero) {
		synchronized(numero){
			Optional<EntTicketSysAid> ticket = buscarPorNumero(numero);
			if (ticket.isPresent()){
				return ticket.get();
			}else{
				ticket = buscarPorNumero(numero);
				if (ticket.isPresent()){
					return ticket.get();
				}

				return nuevoSysAid(numero);
			}
		}
	}

	public Optional<EntTicketSysAid> buscarPorNumero(String numero){
		return entityManager.createNamedQuery("buscar.versionTicket",EntTicketSysAid.class).setParameter("num", numero) .getResultList().stream().findFirst();
	}

	public static ServicioTicketSysaid getInstanciaTransaccional() {
		Servicio servicio = ServicioTicketSysaid.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioTicketSysaid.class, servicio).get();
	}

	private EntTicketSysAid nuevoSysAid(String numero) {
		return persiste(numero);
	}

	private EntTicketSysAid persiste(String numero){
		EntTicketSysAid jira = new EntTicketSysAid();
		jira.setNumero(String.valueOf(numero));
		jira.setEstado("Desconocido");
		entityManager.persist(jira);
		return jira;
	}

	public EntTicketSysAid actualizar(EntTicketSysAid ticketSysAid) {
		return entityManager.merge(ticketSysAid);
	}
}