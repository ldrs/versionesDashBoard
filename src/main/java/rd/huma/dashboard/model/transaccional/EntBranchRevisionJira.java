package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BRANCH_REVISION_JIRA")
@NamedQueries({
	@NamedQuery(name="buscarRevision.branchRevJira",query="SELECT E FROM EntBranchRevisionJira E where E.revision = :rev and E.jira = :jira")
})
public class EntBranchRevisionJira extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 7415696257332416603L;

	@JoinColumn
	@ManyToOne
	private EntBranchRevision revision;


	@JoinColumn
	@ManyToOne
	private EntJira jira;

	public EntBranchRevision getRevision() {
		return revision;
	}

	public void setRevision(EntBranchRevision revision) {
		this.revision = revision;
	}

	public EntJira getJira() {
		return jira;
	}

	public void setJira(EntJira jira) {
		this.jira = jira;
	}
}