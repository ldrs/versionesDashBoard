package rd.huma.dashboard.servicios.background.ejecutores.svn.branch;

import rd.huma.dashboard.servicios.background.AEjecutor;

public class EjecutorSVNBuscaMergeBranches extends AEjecutor {

	@Override
	public void ejecutar() {
		new ProcesadorMergeBranches().procesar();
	}
}