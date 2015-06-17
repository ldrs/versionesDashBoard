package rd.huma.dashboard.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HISTORICO_DEPLOYEMENT")
public class EntHistoricoDeployement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4866711081993958810L;

	@Id
	private String id = UUID.randomUUID().toString();
	
	private EntServidor servidor;
	private EntVersion version;
	private LocalDateTime fecha;
	private EEstadoDeployement estado;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public EEstadoDeployement getEstado() {
		return estado;
	}
	public void setEstado(EEstadoDeployement estado) {
		this.estado = estado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
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
		if (!(obj instanceof EntHistoricoDeployement)) {
			return false;
		}
		EntHistoricoDeployement other = (EntHistoricoDeployement) obj;
		if (estado != other.estado) {
			return false;
		}
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