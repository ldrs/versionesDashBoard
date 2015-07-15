package rd.huma.dashboard.model.transaccional;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;

@Entity
@Table(name="VERSION" ,uniqueConstraints  = {@UniqueConstraint(columnNames={"numero","svnOrigen"}) }  )
@NamedQueries	({@NamedQuery(name="buscar.versionTodas",query="SELECT E from EntVersion E"),
				  @NamedQuery(name="buscarPorEstado.version",query="SELECT E from EntVersion E where E.estado in :est"),
				  
				 }
				)
public class EntVersion extends AEntModelo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5201971151423246320L;


	private String numero;
	private String autor;
	private String branchOrigen;
	private String revisionSVN;

	private String svnOrigen;

	private String comentario;

	private String rutaSvnAmbiente;

	private LocalDateTime momentoCreacion = LocalDateTime.now();

	@Enumerated
	private EEstadoVersion estado = EEstadoVersion.ESPERANDO_DATOS_INTEGRACION;

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


	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public String getSvnOrigen() {
		return svnOrigen;
	}

	public void setSvnOrigen(String svnOrigen) {
		this.svnOrigen = svnOrigen;
	}

	public LocalDateTime getMomentoCreacion() {
		return momentoCreacion;
	}

	public EEstadoVersion getEstado() {
		return estado;
	}

	public void setEstado(EEstadoVersion estado) {
		this.estado = estado;
	}



	public String getRutaSvnAmbiente() {
		return rutaSvnAmbiente;
	}

	public void setRutaSvnAmbiente(String rutaSvnAmbiente) {
		this.rutaSvnAmbiente = rutaSvnAmbiente;
	}

	public void setMomentoCreacion(LocalDateTime momentoCreacion) {
		this.momentoCreacion = momentoCreacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result
				+ ((branchOrigen == null) ? 0 : branchOrigen.hashCode());
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result
				+ ((momentoCreacion == null) ? 0 : momentoCreacion.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((revisionSVN == null) ? 0 : revisionSVN.hashCode());
		result = prime * result
				+ ((svnOrigen == null) ? 0 : svnOrigen.hashCode());
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
		if (svnOrigen == null) {
			if (other.svnOrigen != null) {
				return false;
			}
		} else if (!svnOrigen.equals(other.svnOrigen)) {
			return false;
		}
		return true;
	}
}