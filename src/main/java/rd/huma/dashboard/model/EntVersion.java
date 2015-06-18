package rd.huma.dashboard.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
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
	private List<String> duenos;
	private List<String> jiraNumbers;
	private List<String> ticketNumbers;
	private String branchOrigen;

	private String comentario;

	private Instant momentoCreacion = Instant.now();

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<String> getDuenos() {
		return duenos;
	}

	public void setDuenos(List<String> duenos) {
		this.duenos = duenos;
	}

	public List<String> getJiraNumbers() {
		return jiraNumbers;
	}

	public void setJiraNumbers(List<String> jiraNumbers) {
		this.jiraNumbers = jiraNumbers;
	}

	public List<String> getTicketNumbers() {
		return ticketNumbers;
	}

	public void setTicketNumbers(List<String> ticketNumbers) {
		this.ticketNumbers = ticketNumbers;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result
				+ ((branchOrigen == null) ? 0 : branchOrigen.hashCode());
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + ((duenos == null) ? 0 : duenos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((jiraNumbers == null) ? 0 : jiraNumbers.hashCode());
		result = prime * result
				+ ((momentoCreacion == null) ? 0 : momentoCreacion.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((ticketNumbers == null) ? 0 : ticketNumbers.hashCode());
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
		if (duenos == null) {
			if (other.duenos != null) {
				return false;
			}
		} else if (!duenos.equals(other.duenos)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (jiraNumbers == null) {
			if (other.jiraNumbers != null) {
				return false;
			}
		} else if (!jiraNumbers.equals(other.jiraNumbers)) {
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
		if (ticketNumbers == null) {
			if (other.ticketNumbers != null) {
				return false;
			}
		} else if (!ticketNumbers.equals(other.ticketNumbers)) {
			return false;
		}
		return true;
	}


}