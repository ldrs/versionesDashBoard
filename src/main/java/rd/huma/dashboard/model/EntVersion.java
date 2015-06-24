package rd.huma.dashboard.model;

import java.io.Serializable;
import java.time.Instant;
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
	private String autor;
	private String branchOrigen;
	private String revisionSVN;

	private String comentario;

	private Instant momentoCreacion = Instant.now();

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getId() {
		return id;
	}

	public String getBranchOrigen() {
		return branchOrigen;
	}

	public void setBranchOrigen(String branchOrigen) {
		this.branchOrigen = branchOrigen;
	}

	public String getRevisionSVN() {
		return revisionSVN;
	}

	public void setRevisionSVN(String revisionSVN) {
		this.revisionSVN = revisionSVN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result
				+ ((branchOrigen == null) ? 0 : branchOrigen.hashCode());
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((momentoCreacion == null) ? 0 : momentoCreacion.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((revisionSVN == null) ? 0 : revisionSVN.hashCode());
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
		if (autor == null) {
			if (other.autor != null) {
				return false;
			}
		} else if (!autor.equals(other.autor)) {
			return false;
		}
		if (branchOrigen == null) {
			if (other.branchOrigen != null) {
				return false;
			}
		} else if (!branchOrigen.equals(other.branchOrigen)) {
			return false;
		}
		if (comentario == null) {
			if (other.comentario != null) {
				return false;
			}
		} else if (!comentario.equals(other.comentario)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (momentoCreacion == null) {
			if (other.momentoCreacion != null) {
				return false;
			}
		} else if (!momentoCreacion.equals(other.momentoCreacion)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		if (revisionSVN == null) {
			if (other.revisionSVN != null) {
				return false;
			}
		} else if (!revisionSVN.equals(other.revisionSVN)) {
			return false;
		}
		return true;
	}
}