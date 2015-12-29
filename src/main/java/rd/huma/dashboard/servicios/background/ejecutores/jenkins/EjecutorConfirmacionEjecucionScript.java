package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.version.script.EjecutorProcesaResultadoScripts;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorConfirmacionEjecucionScript extends AEjecutor {

	private EntJobDespliegueVersion job;
	ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	public EjecutorConfirmacionEjecucionScript(EntJobDespliegueVersion job) {
		this.job = job;
	}

	@Override
	public void ejecutar() {
		servicioJobDespliegueVersion = ServicioJobDespliegueVersion.getInstanciaTransaccional();
		servicioJobDespliegueVersion.cambiarEstado(job, EEstadoJobDespliegue.DEPLOY_JENKINS_EXITOSO);
		ejecutarDespuesScript();
	}

	public void ejecutarDespuesScript(){

		if (job.getTipoScript() == ETipoScript.ANTES_SUBIDA){
		}else if (job.getTipoScript() == ETipoScript.ANTES_SUBIDA_REINTENTO){
			List<EntVersionScript> scriptsDespues = ServicioVersion.getInstanciaTransaccional().getScriptDespuesEjecucion(job.getVersion());
			if (!scriptsDespues.isEmpty()){
				new DeployVersionScript(job, false, scriptsDespues).ejecutar();
			}
		}
		Path path = Paths.get("/logs/scripts/"+job.getId());
		File filePath = path.toFile();
		if (Files.exists(path) && filePath.isDirectory()){
			new EjecutorProcesaResultadoScripts(path).ejecutar();
		}
	}
}