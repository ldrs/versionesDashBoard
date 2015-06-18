package rd.huma.dashboard.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DEPLOYEMENT")
public class EntFilaDeployement implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 7034287352452940249L;

	@Id
	private String id = UUID.randomUUID().toString();

	private EntServidor servidor;
	private EntVersion version;
	private LocalDateTime fecha;
	private EntAmbiente ambiente;


	public EntServidor getServidor() {
		return servidor;
	}
	public void setServidor(EntServidor servidor) {
		this.servidor = servidor;
	}
	public EntVersion getVersion() {
		return version;
	}
	public void setVersion(EntVersion version) {
		this.version = version;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getId() {
		return id;
	}

	public EntAmbiente getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((servidor == null) ? 0 : servidor.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EntFilaDeployement)) {
			return false;
		}
		EntFilaDeployement other = (EntFilaDeployement) obj;
		if (fecha == null) {
			if (other.fecha != null) {
				return false;
			}
		} else if (!fecha.equals(other.fecha)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (servidor == null) {
			if (other.servidor != null) {
				return false;
			}
		} else if (!servidor.equals(other.servidor)) {
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