package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;

public class EjecutorDespliegueVersionJenkins extends AEjecutor {
	static final Logger LOGGER = Logger.getLogger(EjecutorDespliegueVersionJenkins.class.getSimpleName());
	private JobDeployVersion jobDeployVersion;

	public EjecutorDespliegueVersionJenkins(EntJobDespliegueVersion job) {
		this.jobDeployVersion = new JobDeployVersion(job);
	}

	@Override
	public void ejecutar() {
		LOGGER.info(String.format("Intentando hacer el deploy de la version %s en el servidor %s", jobDeployVersion.getJob().getVersion().getNumero(),jobDeployVersion.getJob().getServidor().getNombre()));
		jobDeployVersion.inicializar();

		jobDeployVersion.ejecutar();
	}

}