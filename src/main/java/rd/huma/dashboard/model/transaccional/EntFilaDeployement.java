package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DEPLOYEMENT")
@NamedQueries({
		@NamedQuery(name="todos.filaDeploment",query = "Select E from EntFilaDeployement E"),
		@NamedQuery(name="buscar.filaDeploment",query = "Select E from EntFilaDeployement E join E.ambiente A where A.id = :amb")
		}
		)

public class EntFilaDeployement extends AEntModelo implements Comparable<EntFilaDeployement> {
	/**
	 *
	 */
	private static final long serialVersionUID = 7034287352452940249L;

	@JoinColumn
	@ManyToOne
	private EntAmbienteAplicacion ambiente;

	private boolean pideAutorizacion;

	private String grupoDuenos;

	private String estadosJiras;

	private String estadosSysAid;

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

	public String getGrupoDuenos() {
		return grupoDuenos;
	}
	public void setGrupoDuenos(String grupoDuenos) {
		this.grupoDuenos = grupoDuenos;
	}

	public String getEstadosSysAid() {
		return estadosSysAid;
	}
	public void setEstadosSysAid(String estadosSysAid) {
		this.estadosSysAid = estadosSysAid;
	}

	public boolean isPermiteSinTicketSysAid() {
		return permiteSinTicketSysAid;
	}
	public void setPermiteSinTicketSysAid(boolean permiteSinTicketSysAid) {
		this.permiteSinTicketSysAid = permiteSinTicketSysAid;
	}
	@Override
	public int compareTo(EntFilaDeployement o) {
		return ambiente.compareTo(o.ambiente);
	}
}