package rd.huma.dashboard.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VERSION")
public class EntVersion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5201971151423246320L;

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String numero;
	private String dueno;
	private String correoDueno;
	
	private String comentario;
	
	private LocalDateTime fecha;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDueno() {
		return dueno;
	}

	public void setDueno(String dueno) {
		this.dueno = dueno;
	}

	public String getCorreoDueno() {
		return correoDueno;
	}

	public void setCorreoDueno(String correoDueno) {
		this.correoDueno = correoDueno;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result
				+ ((correoDueno == null) ? 0 : correoDueno.hashCode());
		result = prime * result + ((dueno == null) ? 0 : dueno.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (!(obj instanceof EntVersion)) {
			return false;
		}
		EntVersion other = (EntVersion) obj;
		if (comentario == null) {
			if (other.comentario != null) {
				return false;
			}
		} else if (!comentario.equals(other.comentario)) {
			return false;
		}
		if (correoDueno == null) {
			if (other.correoDueno != null) {
				return false;
			}
		} else if (!correoDueno.equals(other.correoDueno)) {
			return false;
		}
		if (dueno == null) {
			if (other.dueno != null) {
				return false;
			}
		} else if (!dueno.equals(other.dueno)) {
			return false;
		}
		if (fecha == null) {
			if (other.fecha != null) {
				return false;
			}
		} else if (!fecha.equals(other.fecha)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		return true;
	}
	
	
	
	
}