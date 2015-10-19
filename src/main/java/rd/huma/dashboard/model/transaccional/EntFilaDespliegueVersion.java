package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="FILA_DESPLIEGE_VERSION")
@NamedQueries({
	@NamedQuery(name = "buscarPorVersionEstado.fila",query ="Select F from EntFilaDespliegueVersion F join F.version V where V.estado in :est" ),
	@NamedQuery(name = "buscarPorDuplicacion.fila", query = "Select F.fila, V.branchOrigen from EntFilaDespliegueVersion F join F.version V  where V.estado in :est group by F.fila, V.branchOrigen having count(V)>1"),
	@NamedQuery(name = "buscarPorFilaBranch.fila", query = "Select F from EntFilaDespliegueVersion F join F.version V where F.fila = :fil and V.branchOrigen = :bra order by V.fechaRegistro"),
	@NamedQuery(name = "buscarPorFilaVersion.fila", query = "Select F from EntFilaDespliegueVersion F join F.version V where F = :fil and V.id = :ver order by V.fechaRegistro"),
	@NamedQuery(name = "buscarPorFilaTicket.fila", query = "Select F from EntFilaDespliegueVersion F, EntVersionTicket VT  where F.version = VT.version and VT.ticketSysAid = :ticket"),
	@NamedQuery(name = "buscarPorVersion.fila", query = "Select F from EntFilaDespliegueVersion F join F.version V where V.id = :ver order by V.fechaRegistro"),
	@NamedQuery(name = "buscarPorFila.fila", query = "Select F from EntFilaDespliegueVersion F where F.fila = :fil  order by prioridad"),
	@NamedQuery(name = "buscarPorAmbiente.fila", query = "Select E from EntFilaDespliegueVersion E Join E.fila F Join F.ambiente A where A.id = :amb order by E.prioridad"),
	@NamedQuery(name = "buscarPorFilaMenorPrioridad.fila", query = "Select E from EntFilaDespliegueVersion E where E.fila = :fil and E.prioridad < :prd order by E.prioridad"),
	@NamedQuery(name = "buscarPorFilaMayorPrioridad.fila", query = "Select E from EntFilaDespliegueVersion E where E.fila = :fil and E.prioridad > :prd order by E.prioridad desc"),
	@NamedQuery(name = "maxVersion.fila",query = "Select Max(F.prioridad) +1 from EntFilaDespliegueVersion F where F.fila = :fil")
})
public class EntFilaDespliegueVersion extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 2449992088857363547L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	@JoinColumn
	@ManyToOne
	private EntFilaDespliegue fila;


	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	private int prioridad;

	private boolean procesandoDeploy;


	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
	}

	public EntVersion getVersion() {
		return version;
	}
	public void setVersion(EntVersion version) {
		this.version = version;
	}
	public EntFilaDespliegue getFila() {
		return fila;
	}
	public void setFila(EntFilaDespliegue fila) {
		this.fila = fila;
	}

	public boolean isProcesandoDeploy() {
		return procesandoDeploy;
	}
	public void setProcesandoDeploy(boolean procesandoDeploy) {
		this.procesandoDeploy = procesandoDeploy;
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
		if (!(obj instanceof EntFilaDespliegueVersion)) {
			return false;
		}
		EntFilaDespliegueVersion other = (EntFilaDespliegueVersion) obj;
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