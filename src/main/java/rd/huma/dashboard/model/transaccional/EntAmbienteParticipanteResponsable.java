package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="AMBIENTE_PARTICIPANTE_RESP")
@NamedQueries({
	@NamedQuery(name="buscarPorAmbiente.ambParcipanteResponsable",query="SELECT E from EntAmbienteParticipanteResponsable E WHERE E.ambiente = :amb")
})
public class EntAmbienteParticipanteResponsable extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1206082479792124667L;


	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;


	@JoinColumn
	@ManyToOne
	private EntGrupoPersona responsables;


	public EntAmbiente getAmbiente() {
		return ambiente;
	}


	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}


	public EntGrupoPersona getResponsables() {
		return responsables;
	}


	public void setResponsables(EntGrupoPersona responsables) {
		this.responsables = responsables;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ambiente == null) ? 0 : ambiente.hashCode());
		result = prime * result
				+ ((responsables == null) ? 0 : responsables.hashCode());
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
		if (!(obj instanceof EntAmbienteParticipanteResponsable)) {
			return false;
		}
		EntAmbienteParticipanteResponsable other = (EntAmbienteParticipanteResponsable) obj;
		if (ambiente == null) {
			if (other.ambiente != null) {
				return false;
			}
		} else if (!ambiente.equals(other.ambiente)) {
			return false;
		}
		if (responsables == null) {
			if (other.responsables != null) {
				return false;
			}
		} else if (!responsables.equals(other.responsables)) {
			return false;
		}
		return true;
	}
}