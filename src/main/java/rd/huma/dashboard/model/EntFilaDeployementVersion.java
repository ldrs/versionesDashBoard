package rd.huma.dashboard.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DEPLOYEMENT_VERSION")
@NamedQueries({
	@NamedQuery(name = "buscarPorVersionEstado.fila",query ="Select F from EntFilaDeployementVersion F join F.version V where V.estado in :est" )
})
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
	private int prioridad;

	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public EntVersion getVersion() {
		return version;
	}
	public void setVersion(EntVersion version) {
		this.version = version;
	}
	public EntFilaDeployement getFila() {
		return fila;
	}
	public void setFila(EntFilaDeployement fila) {
		this.fila = fila;
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