package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="BRANCH_REVISION_MERGE")
@NamedQueries({
	@NamedQuery(name = "buscarOrigenDestino.revisionMerge",query = "SELECT E FROM EntBranchRevisionMerge E where E.revisionOrigen = :ori and E.revisionDestino = :des")
})
public class EntBranchRevisionMerge extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 105597145105014467L;


	@JoinColumn
	@ManyToOne
	private EntBranchRevision revisionOrigen;


	@JoinColumn
	@ManyToOne
	private EntBranchRevision revisionDestino;


	public EntBranchRevision getRevisionDestino() {
		return revisionDestino;
	}

	public void setRevisionDestino(EntBranchRevision revisionDestino) {
		this.revisionDestino = revisionDestino;
	}

	public EntBranchRevision getRevisionOrigen() {
		return revisionOrigen;
	}

	public void setRevisionOrigen(EntBranchRevision revisionOrigen) {
		this.revisionOrigen = revisionOrigen;
	}
}