package rd.huma.dashboard.model.transaccional;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="JIRA_ESTADO")
public class EntJiraEstado extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 2222206705107397270L;

	@JoinColumn
	@ManyToOne
	private EntJira jira;

	private String estado;


	private LocalDateTime momentoCreacion = LocalDateTime.now();


	public EntJira getJira() {
		return jira;
	}


	public void setJira(EntJira jira) {
		this.jira = jira;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDateTime getMomentoCreacion() {
		return momentoCreacion;
	}


}
