package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.List;
import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorDespliegueVersionJenkins extends AEjecutor {
	static final Logger LOGGER = Logger.getLogger(EjecutorDespliegueVersionJenkins.class.getSimpleName());

	private EntJobDespliegueVersion job;

	public EjecutorDespliegueVersionJenkins(EntJobDespliegueVersion job) {
		this.job = job;
	}

	@Override
	public void ejecutar() {
		LOGGER.info(String.format("Iniciando el proceso de despliegues para la version %s en el servidor %s", job.getVersion().getNumero(),job.getServidor().getNombre()));
		ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();

		List<EntVersionScript> scriptAntesEjecucion = servicioVersion.getScriptAntesEjecucion(job.getVersion());
		ADeployVersion deploy;
		if (scriptAntesEjecucion.isEmpty()){
			deploy = new DeployVersion(job);
		}else{
			deploy = new DeployVersionScript(job, true, scriptAntesEjecucion);
		}
		deploy.inicializar();
		deploy.ejecutar();
	}
}