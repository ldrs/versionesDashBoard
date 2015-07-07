package rd.huma.dashboard.model.fila;

import rd.huma.dashboard.model.transaccional.EntFilaDeployement;

public class FilaBranch {
	
	private final EntFilaDeployement fila;
	private final String branch;
	
	public FilaBranch(EntFilaDeployement fila, String branch) {
		this.fila = fila;
		this.branch = branch;
	}

	public EntFilaDeployement getFila() {
		return fila;
	}

	public String getBranch() {
		return branch;
	}
}