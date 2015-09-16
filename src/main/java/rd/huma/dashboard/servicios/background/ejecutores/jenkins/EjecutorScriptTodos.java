package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorScriptTodos extends AEjecutor {


	private EntVersion version;

	public EjecutorScriptTodos(EntVersion version){
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

		List<EntVersionScript> scriptAntesEjecucion = servicioVersion.getScriptAntesEjecucion(version);
		if (scriptAntesEjecucion.isEmpty()){
			List<EntVersionScript> scriptsDespues = servicioVersion.getScriptDespuesEjecucion(version);
			if (!scriptsDespues.isEmpty()){
				new DeployVersionScript(jobVersion, false, scriptsDespues).ejecutar();
			}
		}else{
			 DeployVersionScript deploy = new DeployVersionScript(jobVersion, true, scriptAntesEjecucion);
			 deploy.setTipoScriptCustom(ETipoScript.ANTES_SUBIDA_REINTENTO);
			 deploy.ejecutar();
		}
	}
}