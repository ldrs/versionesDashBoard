package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DEPLOYEMENT")
public class EntFilaDeployement extends AEntModelo implements Comparable<EntFilaDeployement> {
	/**
	 *
	 */
	private static final long serialVersionUID = 7034287352452940249L;

	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;

	private boolean duenosJiras;

	private boolean duenosTickets;

	private boolean pideAutorizacion;

	public EntAmbiente getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}

	public boolean isDuenosJiras() {
		return duenosJiras;
	}
	public void setDuenosJiras(boolean duenosJiras) {
		this.duenosJiras = duenosJiras;
	}
	public boolean isDuenosTickets() {
		return duenosTickets;
	}
	public void setDuenosTickets(boolean duenosTickets) {
		this.duenosTickets = duenosTickets;
	}
	public boolean isPideAutorizacion() {
		return pideAutorizacion;
	}
	public void setPideAutorizacion(boolean pideAutorizacion) {
		this.pideAutorizacion = pideAutorizacion;
	}
	@Override
	public int compareTo(EntFilaDeployement o) {
		return ambiente.getNombre().compareTo(o.ambiente.getNombre());
	}
}