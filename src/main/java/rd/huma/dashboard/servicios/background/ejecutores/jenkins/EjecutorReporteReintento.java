package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;

public class EjecutorReporteReintento extends AEjecutor {

	private EntVersion version;

	public EjecutorReporteReintento(EntVersion version){
		this.version = version;
	}

	@Override
	public void ejecutar() {
		ServicioJobDespliegueVersion servicioJobDeploy = ServicioJobDespliegueVersion.getInstanciaTransaccional();
		servicioJobDeploy.buscarJobPorIdVersion(version.getId()).stream().sorted((a,b) -> b.getFechaRegistro().compareTo(a.getFechaRegistro()))
		.findFirst().ifPresent(this::ejecutarInterno);
	}

	private void ejecutarInterno(EntJobDespliegueVersion jobVersion){
		new DeployReporte(jobVersion).ejecutar();
	}
}