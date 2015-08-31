package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.version.script.EjecutorProcesaResultadoScripts;
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

		ServicioJobDespliegueVersion.getInstanciaTransaccional().cambiarEstado(jobDeployVersion, resultado?EEstadoJobDespliegue.DEPLOY_JENKINS_EXITOSO: EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS);
		Path path = Paths.get("/logs/scripts/"+jobDeployVersion.getId());
		File filePath = path.toFile();
		if (Files.exists(path) && filePath.isDirectory()){
			new EjecutorProcesaResultadoScripts(path).ejecutar();
		}
	}
}