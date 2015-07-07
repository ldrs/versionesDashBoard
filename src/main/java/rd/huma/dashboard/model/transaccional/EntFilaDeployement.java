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

	public EntAmbiente getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}
	@Override
	public int compareTo(EntFilaDeployement o) {
		return ambiente.getNombre().compareTo(o.ambiente.getNombre());
	}
}