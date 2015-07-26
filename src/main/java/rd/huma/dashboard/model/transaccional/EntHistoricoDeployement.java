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
@Table(name="HISTORICO_DEPLOYEMENT")
@NamedQueries({
	@NamedQuery(name ="buscarPorAmbiente.historico", query = "SELECT E from EntHistoricoDeployement E join E.fila F join F.ambiente A where A.id = :idAmbiente ")
}
		
		)
public class EntHistoricoDeployement extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = -4866711081993958810L;


	@ManyToOne
	@JoinColumn
	private EntServidor servidor;

	@ManyToOne
	@JoinColumn
	private EntVersion version;
	

	@ManyToOne
	@JoinColumn
	private EntFilaDeployement fila;

	private LocalDateTime fecha;

	@Enumerated
	private EEstadoFilaDeployement estado;

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
	public EEstadoFilaDeployement getEstado() {
		return estado;
	}
	public void setEstado(EEstadoFilaDeployement estado) {
		this.estado = estado;
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
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
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
		if (!super.equals(obj)) {
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