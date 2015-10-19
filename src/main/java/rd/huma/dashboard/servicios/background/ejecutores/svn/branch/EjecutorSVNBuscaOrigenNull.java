package rd.huma.dashboard.servicios.background.ejecutores.svn.branch;

import rd.huma.dashboard.servicios.background.AEjecutor;

public class EjecutorSVNBuscaOrigenNull extends AEjecutor {

	@Override
	public void ejecutar() {
		new ProcesadorOrigenNull().procesar();

	}
}