package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;

public class EjecutorJenkinsSeguimientoDespliegue extends AEjecutor {
	private static final Logger LOGGER = Logger.getLogger(EjecutorJenkinsSeguimientoDespliegue.class.getSimpleName());


	private String url;
	private EntJobDespliegueVersion job;

	public EjecutorJenkinsSeguimientoDespliegue(String url,	EntJobDespliegueVersion job) {
		this.url = url;
		this.job = job;
	}

	@Override
	public void ejecutar() {

	}
}