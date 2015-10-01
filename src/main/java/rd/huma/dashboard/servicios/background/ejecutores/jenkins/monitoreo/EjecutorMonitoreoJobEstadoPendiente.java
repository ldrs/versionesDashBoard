package rd.huma.dashboard.servicios.background.ejecutores.jenkins.monitoreo;

import rd.huma.dashboard.servicios.background.AEjecutor;

public class EjecutorMonitoreoJobEstadoPendiente extends AEjecutor {

	@Override
	public void ejecutar() {
		new MoniteroEstadosJobsJenkins().ejecutar();
	}




}

