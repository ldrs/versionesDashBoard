package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VERSION_JIRA")
@NamedQueries({
				@NamedQuery(name="buscarPorVersion.versionJira", query="select E from EntVersionJira E where E.version = :ver"),
				@NamedQuery(name="buscarPorJira.versionJira", query="select E from EntVersionJira E join E.version V where E.jira = :jira and V.estado in :est order by V.fechaRegistro desc")

			})
public class EntVersionJira extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 268122415682429660L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;


	@JoinColumn
	@ManyToOne
	private EntJira jira;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public EntJira getJira() {
		return jira;
	}

	public void setJira(EntJira jira) {
		this.jira = jira;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jira == null) ? 0 : jira.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		if (!(obj instanceof EntVersionJira)) {
			return false;
		}
		EntVersionJira other = (EntVersionJira) obj;
		if (jira == null) {
			if (other.jira != null) {
				return false;
			}
		} else if (!jira.equals(other.jira)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}



}