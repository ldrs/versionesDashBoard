package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;

public class EjecutorConfirmacionReporte extends AEjecutor {

	private EntJobDespliegueVersion jobDeployReporte;

	public EjecutorConfirmacionReporte(EntJobDespliegueVersion jobDespliegue) {
		this.jobDeployReporte = jobDespliegue;
	}

	@Override
	public void ejecutar() {
		ServicioJobDespliegueVersion.getInstanciaTransaccional().cambiarEstado(jobDeployReporte, EEstadoJobDespliegue.DEPLOY_JENKINS_EXITOSO);
	}
}