package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AMBIENTE_APLICACION")
public class EntAmbienteAplicacion extends AEntModelo implements Comparable<EntAmbienteAplicacion>{

	/**
	 *
	 */
	private static final long serialVersionUID = 6521232680596057448L;


	private String jobJenkinsDeployements;

	private boolean habilitado;

	@JoinColumn
	@ManyToOne
	private EntAplicacion aplicacion;


	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public EntAmbiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getJobJenkinsDeployements() {
		return jobJenkinsDeployements;
	}

	public void setJobJenkinsDeployements(String jobJenkinsDeployements) {
		this.jobJenkinsDeployements = jobJenkinsDeployements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ambiente == null) ? 0 : ambiente.hashCode());
		result = prime * result
				+ ((aplicacion == null) ? 0 : aplicacion.hashCode());
		result = prime * result + (habilitado ? 1231 : 1237);
		result = prime
				* result
				+ ((jobJenkinsDeployements == null) ? 0
						: jobJenkinsDeployements.hashCode());
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
		if (!(obj instanceof EntAmbienteAplicacion)) {
			return false;
		}
		EntAmbienteAplicacion other = (EntAmbienteAplicacion) obj;
		if (ambiente == null) {
			if (other.ambiente != null) {
				return false;
			}
		} else if (!ambiente.equals(other.ambiente)) {
			return false;
		}
		if (aplicacion == null) {
			if (other.aplicacion != null) {
				return false;
			}
		} else if (!aplicacion.equals(other.aplicacion)) {
			return false;
		}
		if (habilitado != other.habilitado) {
			return false;
		}
		if (jobJenkinsDeployements == null) {
			if (other.jobJenkinsDeployements != null) {
				return false;
			}
		} else if (!jobJenkinsDeployements.equals(other.jobJenkinsDeployements)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(EntAmbienteAplicacion o) {
		return Integer.compare(aplicacion.getOrden(), o.getAplicacion().getOrden())+
				ambiente.compareTo(o.ambiente)
				;
	}
}