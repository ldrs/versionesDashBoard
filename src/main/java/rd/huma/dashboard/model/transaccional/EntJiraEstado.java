package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_JIRA_ESTADO")
@NamedQueries({
	@NamedQuery(name = "buscar.jiraEstado", query = "SELECT E from EntJiraEstado E where E.codigo = :cod")
})
public class EntJiraEstado extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -222937856728702918L;

	private String codigo;
	private String descripcion;


	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}