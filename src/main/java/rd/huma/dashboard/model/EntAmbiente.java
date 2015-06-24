package rd.huma.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AMBIENTE")
public class EntAmbiente extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 6521232680596057448L;


	private String nombre;


	private String jobJenkinsDeployements;

	@JoinColumn
	@ManyToOne
	private EntAplicacion aplicacion;

	private int orden;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
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
				+ ((aplicacion == null) ? 0 : aplicacion.hashCode());
		result = prime
				* result
				+ ((jobJenkinsDeployements == null) ? 0
						: jobJenkinsDeployements.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + orden;
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
		if (!(obj instanceof EntAmbiente)) {
			return false;
		}
		EntAmbiente other = (EntAmbiente) obj;
		if (aplicacion == null) {
			if (other.aplicacion != null) {
				return false;
			}
		} else if (!aplicacion.equals(other.aplicacion)) {
			return false;
		}
		if (jobJenkinsDeployements == null) {
			if (other.jobJenkinsDeployements != null) {
				return false;
			}
		} else if (!jobJenkinsDeployements.equals(other.jobJenkinsDeployements)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (orden != other.orden) {
			return false;
		}
		return true;
	}



}