package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_REPORTE")
@NamedQueries({
		@NamedQuery(name="contar.versionReportes", query="select count(e) from EntVersionReporte E where E.version = :ver"),
		@NamedQuery(name="buscar.versionReportes", query="select E from EntVersionReporte E where E.version = :ver")
		}
		)
public class EntVersionReporte extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -2514107819885937403L;


	@JoinColumn
	@ManyToOne
	private EntVersion version;


	@JoinColumn
	@ManyToOne
	private EntJira jira;

	private String reporte;

	@JoinColumn
	@ManyToOne
	private EntPersona autor;

	private long numeroRevision;

	private String nombre;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
		if (reporte!=null){
			this.nombre = reporte.substring(reporte.lastIndexOf('/'));
		}
	}

	public EntJira getJira() {
		return jira;
	}

	public void setJira(EntJira jira) {
		this.jira = jira;
	}

	public EntPersona getAutor() {
		return autor;
	}

	public void setAutor(EntPersona autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((reporte == null) ? 0 : reporte.hashCode());
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
		if (!(obj instanceof EntVersionReporte)) {
			return false;
		}
		EntVersionReporte other = (EntVersionReporte) obj;
		if (reporte == null) {
			if (other.reporte != null) {
				return false;
			}
		} else if (!reporte.equals(other.reporte)) {
			return false;
		}
		return true;
	}

	public void setRevision(long numeroRevision) {
		this.numeroRevision = numeroRevision;
	}

	public long getNumeroRevision() {
		return numeroRevision;
	}

	public String getNombre() {
		return nombre;
	}
}