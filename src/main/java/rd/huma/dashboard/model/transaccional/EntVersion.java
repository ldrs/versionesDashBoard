package rd.huma.dashboard.model.transaccional;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;

@Entity
@Table(name="VERSION" ,uniqueConstraints  = {@UniqueConstraint(columnNames={"numero","svnOrigen"}) }  )
@NamedQueries	({
				  @NamedQuery(name="buscar.versionTodas",query="SELECT E from EntVersion E order by E.fechaRegistro desc"),
				  @NamedQuery(name="buscarPorEstado.version",query="SELECT E from EntVersion E where E.estado in :est"),
				  @NamedQuery(name="buscarPorNumero.version",query="SELECT E from EntVersion E where E.numero in :num"),
				  @NamedQuery(name="buscarPorNumeroOrigen.version", query = "SELECT E from EntVersion E where E.numero = :num and svnOrigen = :sOri"),
				  @NamedQuery(name="buscarPorBranch.version", query = "SELECT E from EntVersion E where E.branchOrigen = :branch order by revisionSVN desc"),
				  @NamedQuery(name="buscarPorBranchDuplicado.version", query = "SELECT E.branchOrigen from EntVersion E where E.estado in :est group by E.branchOrigen having Count(E)>2")
				  })
public class EntVersion extends AEntModelo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5201971151423246320L;


	private String numero;
	@JoinColumn
	@ManyToOne
	private EntPersona autor;
	private String branchOrigen;
	private String revisionSVN;

	private String svnOrigen;

	private String comentario;

	private String rutaSvnAmbiente;

	private int minutosCompiladoVersion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	@Enumerated(EnumType.STRING)
	private EEstadoVersion estado = EEstadoVersion.ESPERANDO_DATOS_INTEGRACION;


	private String inicioJob;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public EntPersona getAutor() {
		return autor;
	}

	public void setAutor(EntPersona autor) {
		this.autor = autor;
	}


	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		if (comentario.length()>150){
			this.comentario = comentario.substring(0,150);
		}else{
			this.comentario = comentario;
		}
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

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
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

	public void setInicioJob(String inicioJob) {
		this.inicioJob= inicioJob;
	}

	public String getInicioJob() {
		return inicioJob;
	}

	public int getMinutosCompiladoVersion() {
		return minutosCompiladoVersion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((branchOrigen == null) ? 0 : branchOrigen.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (branchOrigen == null) {
			if (other.branchOrigen != null) {
				return false;
			}
		} else if (!branchOrigen.equals(other.branchOrigen)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
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