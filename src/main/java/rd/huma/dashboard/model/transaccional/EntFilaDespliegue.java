package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DESPLIEGE")
@NamedQueries({
		@NamedQuery(name="todos.filaDeploment",query = "Select E from EntFilaDespliegue E"),
		@NamedQuery(name="buscar.filaDeploment",query = "Select E from EntFilaDespliegue E join E.ambiente A where A.id = :amb")
		}
		)

public class EntFilaDespliegue extends AEntModelo implements Comparable<EntFilaDespliegue> {
	/**
	 *
	 */
	private static final long serialVersionUID = 7034287352452940249L;

	@JoinColumn
	@ManyToOne
	private EntAmbienteAplicacion ambiente;

	private boolean pideAutorizacion;

	private String estadosJiras;

	private boolean permiteSinTicketSysAid;

	public EntAmbienteAplicacion getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(EntAmbienteAplicacion ambiente) {
		this.ambiente = ambiente;
	}

	public boolean isPideAutorizacion() {
		return pideAutorizacion;
	}
	public void setPideAutorizacion(boolean pideAutorizacion) {
		this.pideAutorizacion = pideAutorizacion;
	}

	public String getEstadosJiras() {
		return estadosJiras;
	}
	public void setEstadosJiras(String estadosJiras) {
		this.estadosJiras = estadosJiras;
	}

	public boolean isPermiteSinTicketSysAid() {
		return permiteSinTicketSysAid;
	}
	public void setPermiteSinTicketSysAid(boolean permiteSinTicketSysAid) {
		this.permiteSinTicketSysAid = permiteSinTicketSysAid;
	}

	@Override
	public int compareTo(EntFilaDespliegue o) {
		return ambiente.compareTo(o.ambiente);
	}
}