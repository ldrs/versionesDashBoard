package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_SYSAID_PERSONA")
public class EntTicketSysAidParticipante extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -6225141488159536701L;

	@JoinColumn
	@ManyToOne
	private EntTicketSysAid ticket;


	@JoinColumn
	@ManyToOne
	private EntPersona participante;


	public EntTicketSysAid getTicket() {
		return ticket;
	}


	public void setTicket(EntTicketSysAid ticket) {
		this.ticket = ticket;
	}


	public EntPersona getParticipante() {
		return participante;
	}


	public void setParticipante(EntPersona participante) {
		this.participante = participante;
	}


}