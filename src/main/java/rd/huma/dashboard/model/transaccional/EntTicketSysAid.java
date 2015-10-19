package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TICKET_SYSAID")
@NamedQueries({
				@NamedQuery(name="buscar.versionTicket", query="SELECT E from EntTicketSysAid E where E.numero = :num"),
				@NamedQuery(name="buscarAmbienteSegunEstados.versionTicket", query="SELECT A from EntTicketSysAid T, EntTicketSysAidEstado E join E.ambiente A where T.estado = E.codigo and T in :tickets order by A.orden"),
				@NamedQuery(name="buscarTodos.versionTicket", query="SELECT E from EntTicketSysAid E"),
				@NamedQuery(name="buscarTodosMenosEstado.versionTicket", query="SELECT E from EntTicketSysAid E where estado!= :est")
			})
public class EntTicketSysAid extends AEntModelo implements Comparable<EntTicketSysAid> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1463431661829014017L;

	private long numero;

	private int estado = -1 ;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());


	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaUltimaModificacion = Timestamp.from(Instant.now());

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
	}

	public Instant asignarFechaModificacion(){
		Instant instante = Instant.now();
		fechaUltimaModificacion = Timestamp.from(instante);
		return instante;
	}

	public Instant getUltimaModificacion(){
		return fechaUltimaModificacion.toInstant();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Long.valueOf(numero).hashCode();
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
		if (numero!=other.numero){
			return false;
		}
		return true;
	}


	@Override
	public int compareTo(EntTicketSysAid o) {
		return Long.compare(numero, o.numero);
	}
}