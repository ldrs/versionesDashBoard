package rd.huma.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_SYSAID")
public class EntTicketSysAid extends AEntModelo implements Comparable<EntTicketSysAid> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1463431661829014017L;


	private String numero;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (!(obj instanceof EntTicketSysAid)) {
			return false;
		}
		EntTicketSysAid other = (EntTicketSysAid) obj;
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		return true;
	}


	@Override
	public int compareTo(EntTicketSysAid o) {
		return o.numero.compareTo(numero);
	}
}