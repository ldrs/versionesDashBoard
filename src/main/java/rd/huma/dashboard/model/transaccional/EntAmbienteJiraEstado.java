package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="AMBIENTE_JIRA_ESTADO")
@NamedQueries({
	@NamedQuery(name="ambientePorEstado.ambienteJiraEstado", query = "SELECT E.ambiente FROM EntAmbienteJiraEstado E where E.estado = :est ")
})
public class EntAmbienteJiraEstado extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 997522669829509772L;

	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;


	@JoinColumn
	@ManyToOne
	private EntJiraEstado estado;


	public EntAmbiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}

	public EntJiraEstado getEstado() {
		return estado;
	}

	public void setEstado(EntJiraEstado estado) {
		this.estado = estado;
	}

}