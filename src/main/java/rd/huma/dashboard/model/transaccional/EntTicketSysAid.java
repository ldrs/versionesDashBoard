package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_SYSAID")
@NamedQueries({@NamedQuery(name="buscar.versionTicket", query="SELECT E from EntTicketSysAid E where E.numero = :num")})
public class EntTicketSysAid extends AEntModelo implements Comparable<EntTicketSysAid> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1463431661829014017L;

	private String numero;

	private String estado;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
		if (o.numero == null && numero == null){
			return 0;
		}
		if (o.numero != null && numero == null){
			return 1;
		}
		if (o.numero == null && numero != null){
			return -1;
		}

		return  o.numero.compareTo(numero);
	}
}