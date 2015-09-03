package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_SCRIPT_JIRA")
@NamedQueries({
	@NamedQuery(name="buscaPorReporte.versionScriptJira",query="SELECT J from EntVersionScriptJira J where J.script = :src")
})
public class EntVersionScriptJira extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -70168492223697202L;


	@JoinColumn
	@ManyToOne
	private EntJira jira;

	@JoinColumn
	@ManyToOne
	private EntVersionScript script;

	public EntJira getJira() {
		return jira;
	}

	public void setJira(EntJira jira) {
		this.jira = jira;
	}

	public EntVersionScript getScript() {
		return script;
	}

	public void setScript(EntVersionScript script) {
		this.script = script;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jira == null) ? 0 : jira.hashCode());
		result = prime * result + ((script == null) ? 0 : script.hashCode());
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
		if (!(obj instanceof EntVersionScriptJira)) {
			return false;
		}
		EntVersionScriptJira other = (EntVersionScriptJira) obj;
		if (jira == null) {
			if (other.jira != null) {
				return false;
			}
		} else if (!jira.equals(other.jira)) {
			return false;
		}
		if (script == null) {
			if (other.script != null) {
				return false;
			}
		} else if (!script.equals(other.script)) {
			return false;
		}
		return true;
	}
}