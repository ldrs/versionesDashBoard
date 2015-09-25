package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorScriptDespues extends AEjecutor {

	private EntVersion version;

	public EjecutorScriptDespues(EntVersion version){
		this.version = version;
	}

	@Override
	public void ejecutar() {
		ServicioJobDespliegueVersion servicioJobDeploy = ServicioJobDespliegueVersion.getInstanciaTransaccional();
		servicioJobDeploy.buscarJobPorIdVersion(version.getId()).stream().sorted((a,b) -> b.getFechaRegistro().compareTo(a.getFechaRegistro()))
		.findFirst().ifPresent(this::ejecutarInterno);
	}

	private void ejecutarInterno(EntJobDespliegueVersion jobVersion){
		ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();

		List<EntVersionScript> scriptAntesEjecucion = servicioVersion.getScriptDespuesEjecucion(version);
		if (!scriptAntesEjecucion.isEmpty()){
			DeployVersionScript deploy = new DeployVersionScript(jobVersion, true, scriptAntesEjecucion);
			deploy.setEjecutarSiempre(true);
			deploy.ejecutar();
		}
	}
}