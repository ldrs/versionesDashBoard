package rd.huma.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VERSION_TICKET")
public class EntVersionTicket extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 268122415682429660L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;


	@JoinColumn
	@ManyToOne
	private EntTicketSysAid ticketSysAid;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public EntTicketSysAid getTicketSysAid() {
		return ticketSysAid;
	}

	public void setTicketSysAid(EntTicketSysAid ticketSysAid) {
		this.ticketSysAid = ticketSysAid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ticketSysAid == null) ? 0 : ticketSysAid.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof EntVersionTicket)) {
			return false;
		}
		EntVersionTicket other = (EntVersionTicket) obj;
		if (ticketSysAid == null) {
			if (other.ticketSysAid != null) {
				return false;
			}
		} else if (!ticketSysAid.equals(other.ticketSysAid)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}





}