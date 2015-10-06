package rd.huma.dashboard.servicios.integracion.svn.util;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import rd.huma.dashboard.model.transaccional.EntJira;

public class SVNOrigenBranch {

	private String origenBranch;
	private long revision;
	private List<EntJira> jiras;
	private Set<String> mergeBranches;

	void setJiraEncontrados(List<EntJira> jiras) {
		this.jiras = jiras;
	}

	void setMergeInformacion(Set<String> mergeBranches){
		this.mergeBranches = mergeBranches;
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

	public Set<String> getMergeBranches() {
		if (mergeBranches == null){
			return Collections.emptySet();
		}
		return mergeBranches;
	}
}