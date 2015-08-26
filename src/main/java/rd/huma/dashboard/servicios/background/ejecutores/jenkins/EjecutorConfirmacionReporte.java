package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;

public class EjecutorConfirmacionReporte extends AEjecutor {

	private EntJobDespliegueVersion jobDeployVersion;

	public EjecutorConfirmacionReporte(EntJobDespliegueVersion jobDespliegue) {
		this.jobDeployVersion = jobDespliegue;
	}


	@Override
	public void ejecutar() {
		new EjecutorJenkinsSeguimientoDespliegue(jobDeployVersion.getURL(), jobDeployVersion, this::resultado).ejecutar();
	}

	public void resultado(Boolean resultado){

	}

}
