package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BRANCH_REVISION")
public class EntBranchRevision extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 3244865824170116082L;


	@ManyToOne
	@JoinColumn
	private EntBranch branch;

	private long revision;

	public long getRevision() {
		return revision;
	}

	public void setRevision(long revision) {
		this.revision = revision;
	}
}