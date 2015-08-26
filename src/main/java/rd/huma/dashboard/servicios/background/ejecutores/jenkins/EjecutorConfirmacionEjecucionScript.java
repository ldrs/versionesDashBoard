package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;

public class EjecutorConfirmacionEjecucionScript extends AEjecutor {

	private EntJobDespliegueVersion jobDeployVersion;


	public EjecutorConfirmacionEjecucionScript(EntJobDespliegueVersion jobDeployVersion) {
		this.jobDeployVersion = jobDeployVersion;
	}

	@Override
	public void ejecutar() {
		new EjecutorJenkinsSeguimientoDespliegue(jobDeployVersion.getURL(), jobDeployVersion, this::resultado).ejecutar();
	}

	private void resultado(Boolean resultado){
		if (resultado && jobDeployVersion.getTipoScript() == ETipoScript.ANTES_SUBIDA){
			ServicioJobDespliegueVersion servicioJob =  ServicioJobDespliegueVersion.getInstanciaTransaccional();
			EntJobDespliegueVersion jobVersion = servicioJob.buscarPorJobRelacionado(jobDeployVersion);


			DeployVersion deployVersion = new DeployVersion(jobVersion);
			deployVersion.inicializar();
			deployVersion.ejecutar();
		}
	}
}