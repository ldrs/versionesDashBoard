package rd.huma.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VERSION_JIRA")
public class EntVersionTicket extends AEntModelo {

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
		if (!(obj instanceof EntVersionTicket)) {
			return false;
		}
		EntVersionTicket other = (EntVersionTicket) obj;
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