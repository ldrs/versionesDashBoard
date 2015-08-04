package rd.huma.dashboard.model.transaccional;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REPOSITORIO_DATOS_SCRIPT_EJE")
public class EntRepositorioDatosScriptEjecutados extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 5531411340878896672L;

	@JoinColumn
	@ManyToOne
	private EntRepositorioDatos repositorioDatos;


	@JoinColumn
	@ManyToOne
	private EntJira jira;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	private LocalDate fechaEjecucion;

	private boolean ocacionoError;

	private String urlScript;

	public EntRepositorioDatos getRepositorioDatos() {
		return repositorioDatos;
	}

	public void setRepositorioDatos(EntRepositorioDatos repositorioDatos) {
		this.repositorioDatos = repositorioDatos;
	}

	public EntJira getJira() {
		return jira;
	}

	public void setJira(EntJira jira) {
		this.jira = jira;
	}

	public LocalDate getFechaEjecucion() {
		return fechaEjecucion;
	}



	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public void setFechaEjecucion(LocalDate fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	public boolean isOcacionoError() {
		return ocacionoError;
	}

	public void setOcacionoError(boolean ocacionoError) {
		this.ocacionoError = ocacionoError;
	}

	public String getUrlScript() {
		return urlScript;
	}

	public void setUrlScript(String urlScript) {
		this.urlScript = urlScript;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jira == null) ? 0 : jira.hashCode());
		result = prime
				* result
				+ ((repositorioDatos == null) ? 0 : repositorioDatos.hashCode());
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
		if (!(obj instanceof EntRepositorioDatosScriptEjecutados)) {
			return false;
		}
		EntRepositorioDatosScriptEjecutados other = (EntRepositorioDatosScriptEjecutados) obj;
		if (jira == null) {
			if (other.jira != null) {
				return false;
			}
		} else if (!jira.equals(other.jira)) {
			return false;
		}
		if (repositorioDatos == null) {
			if (other.repositorioDatos != null) {
				return false;
			}
		} else if (!repositorioDatos.equals(other.repositorioDatos)) {
			return false;
		}
		return true;
	}
}