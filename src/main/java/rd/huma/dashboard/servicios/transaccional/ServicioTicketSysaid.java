package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.servicios.integracion.sysaid.ServicioIntegracionSYSAID;

@Stateless
@Servicio
public class ServicioTicketSysaid {

	@Inject
	private EntityManager entityManager;


	public EntTicketSysAid encuentraOSalva(String numero) {
		synchronized(numero){
			return entityManager.createNamedQuery("buscar.versionTicket",EntTicketSysAid.class).setParameter("num", numero) .getResultList().stream().findFirst().orElse(nuevoSysAid(numero));
		}
	}

	private EntTicketSysAid nuevoSysAid(String numero) {
		Optional<Ticket> opcionalTicket = ServicioIntegracionSYSAID.instancia().getTicket(Long.valueOf(numero));
		if (opcionalTicket.isPresent()){
			return persiste(opcionalTicket.get());
		}
		return null;
	}

	private EntTicketSysAid persiste(Ticket ticket){
		EntTicketSysAid jira = new EntTicketSysAid();
		jira.setNumero(String.valueOf(ticket.getTicket()));
		jira.setEstado(String.valueOf(ticket.getEstado()));
		entityManager.persist(jira);
		return jira;
	}
}