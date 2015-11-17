package rd.huma.dashboard.model.transaccional.dato;

public class BranchUltimaRevision {

	private long ultimaRevision;
	private String branch;

	public BranchUltimaRevision(long ultimaRevision, String branch) {
		this.ultimaRevision = ultimaRevision;
		this.branch = branch;
	}

	public long getUltimaRevision() {
		return ultimaRevision;
	}

	public String getBranch() {
		return branch;
	}
}