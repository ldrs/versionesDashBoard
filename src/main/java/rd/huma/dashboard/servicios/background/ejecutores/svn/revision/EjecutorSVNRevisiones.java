package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import rd.huma.dashboard.servicios.background.AEjecutor;

public class EjecutorSVNRevisiones extends AEjecutor {


	@Override
	public void ejecutar() {
		new ProcesarSVNReviciones().ejecutar();
	}
}