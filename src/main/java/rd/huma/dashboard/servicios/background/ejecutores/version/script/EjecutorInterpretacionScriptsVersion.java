package rd.huma.dashboard.servicios.background.ejecutores.version.script;

import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorInterpretacionScriptsVersion extends AEjecutor {

	private EntVersion version;
	private ServicioVersion servicioVersion;

	public EjecutorInterpretacionScriptsVersion(EntVersion version){
		this.version = version;
	}

	@Override
	public void ejecutar() {
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();

		servicioVersion.buscaScript(version).forEach(this::intepreta);
	}

	private void intepreta(EntVersionScript versionScript){

	}

}