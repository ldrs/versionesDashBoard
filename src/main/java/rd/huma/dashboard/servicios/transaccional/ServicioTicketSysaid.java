package rd.huma.dashboard.servicios.transaccional;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;

@Stateless
@Servicio
public class ServicioTicketSysaid {

	private static final Logger LOGGER = Logger.getLogger(ServicioTicketSysaid.class.getSimpleName());

	@Inject
	private EntityManager entityManager;


	public EntTicketSysAid encuentraOSalva(String numero) {
		synchronized(numero){
			return entityManager.createNamedQuery("buscar.versionTicket",EntTicketSysAid.class).setParameter("num", numero) .getResultList().stream().findFirst().orElse(nuevoSysAid(numero));
		}
	}

	private EntTicketSysAid nuevoSysAid(String numero) {
		LOGGER.warning("Actualmente ignorando sysid : " + numero);

		return null;

//		Optional<Ticket> opcionalTicket = ServicioIntegracionSYSAID.instancia().getTicket(Long.valueOf(numero));
//		if (opcionalTicket.isPresent()){
//			return persiste(opcionalTicket.get());
//		}
//		return null;
	}

	private EntTicketSysAid persiste(Ticket ticket){
		EntTicketSysAid jira = new EntTicketSysAid();
		jira.setNumero(String.valueOf(ticket.getTicket()));
		jira.setEstado(String.valueOf(ticket.getEstado()));
		entityManager.persist(jira);
		return jira;
	}
}