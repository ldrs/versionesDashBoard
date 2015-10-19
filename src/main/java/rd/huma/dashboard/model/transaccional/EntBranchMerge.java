package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="BRANCH_MERGE")
@NamedQueries({
	@NamedQuery(name="buscaPorOrigen.branchMerge",query="select E from EntBranchMerge E join E.origen B where B.branch = :branch"),
	@NamedQuery(name="buscaPorDestino.branchMerge",query="select E from EntBranchMerge E where E.destino = :branch"),
	@NamedQuery(name="buscaPorOrigenObj.branchMerge",query="select E from EntBranchMerge E where E.origen = :branch"),
	@NamedQuery(name="buscaOridenYDestino.branchMerge",query="select E from EntBranchMerge E join E.destino B join E.origen O where B.branch = :branch and O.branch = :origen")
})
public class EntBranchMerge extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -6884368899974970446L;


	@ManyToOne
	@JoinColumn
	private EntBranch origen;

	@ManyToOne
	@JoinColumn
	private EntBranch destino;

	public EntBranch getBranchOrigen() {
		return origen;
	}

	public void setBranchOrigen(EntBranch branchOrigen) {
		this.origen = branchOrigen;
	}

	public EntBranch getBranchDestino() {
		return destino;
	}

	public void setBranchDestino(EntBranch branchDestino) {
		this.destino = branchDestino;
	}
}