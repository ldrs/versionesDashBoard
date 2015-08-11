package rd.huma.dashboard.model.transaccional;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.EEstadoFilaDeployement;

@Entity
@Table(name="HISTORICO_DESPLIEGUE")
@NamedQueries({
	@NamedQuery(name ="buscarPorAmbiente.historico", query = "SELECT E from EntHistoricoDespliegue E join E.fila F join F.ambiente A where A.id = :idAmbiente ")
}

		)
public class EntHistoricoDespliegue extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = -4866711081993958810L;


	@ManyToOne
	@JoinColumn
	private EntJobDespliegueVersion jobDespliegueVersion;

	@ManyToOne
	@JoinColumn
	private EntVersion version;


	@ManyToOne
	@JoinColumn
	private EntFilaDespliegue fila;

	private LocalDateTime fechaRegistro = LocalDateTime.now();

	@Enumerated
	private EEstadoFilaDeployement estado;

	public EntVersion getVersion() {
		return version;
	}
	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public EEstadoFilaDeployement getEstado() {
		return estado;
	}
	public void setEstado(EEstadoFilaDeployement estado) {
		this.estado = estado;
	}

	public EntJobDespliegueVersion getJobDespliegueVersion() {
		return jobDespliegueVersion;
	}
	public void setJobDespliegueVersion(EntJobDespliegueVersion jobDespliegueVersion) {
		this.jobDespliegueVersion = jobDespliegueVersion;
	}
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public EntFilaDespliegue getFila() {
		return fila;
	}
	public void setFila(EntFilaDespliegue fila) {
		this.fila = fila;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result + ((fila == null) ? 0 : fila.hashCode());
		result = prime
				* result
				+ ((jobDespliegueVersion == null) ? 0 : jobDespliegueVersion
						.hashCode());
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
		if (!(obj instanceof EntHistoricoDespliegue)) {
			return false;
		}
		EntHistoricoDespliegue other = (EntHistoricoDespliegue) obj;
		if (estado != other.estado) {
			return false;
		}
		if (fechaRegistro == null) {
			if (other.fechaRegistro != null) {
				return false;
			}
		} else if (!fechaRegistro.equals(other.fechaRegistro)) {
			return false;
		}
		if (fila == null) {
			if (other.fila != null) {
				return false;
			}
		} else if (!fila.equals(other.fila)) {
			return false;
		}
		if (jobDespliegueVersion == null) {
			if (other.jobDespliegueVersion != null) {
				return false;
			}
		} else if (!jobDespliegueVersion.equals(other.jobDespliegueVersion)) {
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