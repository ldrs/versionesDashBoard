package rd.huma.dashboard.servicios.integracion.svn.util;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntJira;

public class SVNOrigenBranch {

	private String origenBranch;
	private long revision;
	private List<EntJira> jiras;

	void setJiraEncontrados(List<EntJira> jiras) {
		this.jiras = jiras;
	}

	public List<EntJira> getJiras() {
		return jiras;
	}

	void setOrigenBranch(String origenBranch) {
		this.origenBranch = origenBranch;
	}

	void setRevision(long revision) {
		this.revision = revision;
	}

	public long getRevision() {
		return revision;
	}

	public String getOrigenBranch() {
		return origenBranch;
	}
}