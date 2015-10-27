package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_SYSAID_ESTADO_AREA")
public class EntTicketEstadoArea extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 267284855399220258L;


	@JoinColumn
	@ManyToOne
	private EntTicketSysAidEstado estado;

	@JoinColumn
	@ManyToOne
	private EntArea area;


	public EntTicketSysAidEstado getEstado() {
		return estado;
	}

	public void setEstado(EntTicketSysAidEstado estado) {
		this.estado = estado;
	}

	public EntArea getArea() {
		return area;
	}

	public void setArea(EntArea area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
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
		if (!(obj instanceof EntTicketEstadoArea)) {
			return false;
		}
		EntTicketEstadoArea other = (EntTicketEstadoArea) obj;
		if (area == null) {
			if (other.area != null) {
				return false;
			}
		} else if (!area.equals(other.area)) {
			return false;
		}
		if (estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!estado.equals(other.estado)) {
			return false;
		}
		return true;
	}
}