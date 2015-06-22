package rd.huma.dashboard.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AMBIENTE")
public class EntAmbiente implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6521232680596057448L;


	@Id
	private String id = UUID.randomUUID().toString();

	private String nombre;

	private EntAplicacion aplicacion;

	private String jobJenkinsDeployements;

	private List<String> duenos;

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

	public List<String> getDuenos() {
		return duenos;
	}

	public void setDuenos(List<String> duenos) {
		this.duenos = duenos;
	}

	public String getId() {
		return id;
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
		int result = 1;
		result = prime * result
				+ ((aplicacion == null) ? 0 : aplicacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((jobJenkinsDeployements == null) ? 0 : jobJenkinsDeployements.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
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
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
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
		return true;
	}
}