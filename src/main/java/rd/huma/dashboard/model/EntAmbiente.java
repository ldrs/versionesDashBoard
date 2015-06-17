package rd.huma.dashboard.model;

import java.io.Serializable;
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
	
	private String jobJenkins;

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

	public String getJobJenkins() {
		return jobJenkins;
	}

	public void setJobJenkins(String jobJenkins) {
		this.jobJenkins = jobJenkins;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aplicacion == null) ? 0 : aplicacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((jobJenkins == null) ? 0 : jobJenkins.hashCode());
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
		if (jobJenkins == null) {
			if (other.jobJenkins != null) {
				return false;
			}
		} else if (!jobJenkins.equals(other.jobJenkins)) {
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