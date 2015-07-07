package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AMBIENTE_DUENOS")
public class EntAmbienteDueno  extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = -7503105943853372323L;

	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;

	@JoinColumn
	@ManyToOne
	private EntPersona persona;

	public EntAmbiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}

	public EntPersona getPersona() {
		return persona;
	}

	public void setPersona(EntPersona persona) {
		this.persona = persona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ambiente == null) ? 0 : ambiente.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
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
		if (!(obj instanceof EntAmbienteDueno)) {
			return false;
		}
		EntAmbienteDueno other = (EntAmbienteDueno) obj;
		if (ambiente == null) {
			if (other.ambiente != null) {
				return false;
			}
		} else if (!ambiente.equals(other.ambiente)) {
			return false;
		}
		if (persona == null) {
			if (other.persona != null) {
				return false;
			}
		} else if (!persona.equals(other.persona)) {
			return false;
		}
		return true;
	}



}