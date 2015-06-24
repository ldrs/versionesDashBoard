package rd.huma.dashboard.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DEPLOYEMENT_VERSION")
public class EntFilaDeployementVersion extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 2449992088857363547L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;


	@JoinColumn
	@ManyToOne
	private EntFilaDeployement fila;


	private LocalDateTime fecha;

	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fila == null) ? 0 : fila.hashCode());
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
		if (!(obj instanceof EntFilaDeployementVersion)) {
			return false;
		}
		EntFilaDeployementVersion other = (EntFilaDeployementVersion) obj;
		if (fila == null) {
			if (other.fila != null) {
				return false;
			}
		} else if (!fila.equals(other.fila)) {
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