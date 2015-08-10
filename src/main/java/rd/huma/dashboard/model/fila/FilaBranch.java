package rd.huma.dashboard.model.fila;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;

public class FilaBranch {
	
	private final EntFilaDespliegue fila;
	private final String branch;
	
	public FilaBranch(EntFilaDespliegue fila, String branch) {
		this.fila = fila;
		this.branch = branch;
	}

	public EntFilaDespliegue getFila() {
		return fila;
	}

	public String getBranch() {
		return branch;
	}
}