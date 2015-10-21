package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.servicios.integracion.jenkins.InvocadorJenkins;

public class DeployVersion extends ADeployVersion{

	private static final Logger LOGGER = Logger.getLogger(DeployVersion.class.getSimpleName());

	public DeployVersion(EntJobDespliegueVersion job) {
		super(job);
	}

	@Override
	public void ejecutar() {
		super.ejecutar();
		deployVersion();
	}

	void deployVersion(){
		LOGGER.info(String.format("Intentando hacer el deploy para el servidor %s para la version %s",job.getServidor().getNombre(), job.getVersion().getNumero()));


		String urlBaseEjecucionJob =  getURLDeployEjecucionJob();
		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(urlBaseEjecucionJob+"buildWithParameters");
		invocadorJenkins.adicionarParametro("URL_CALLBACK_DASHBOARD", getConfiguracionGeneral().getRutaDashBoard()+"api/versionConfirma/"+ job.getId());

		getServicioVersion().buscaPropiedades(getVersion()).forEach(propiedad -> invocadorJenkins.adicionarParametro(propiedad.getPropiedad(), propiedad.getValor()));

		invocadorJenkins.adicionarParametro("JOB_ID", getJob().getId());
		invocadorJenkins.invocar();

	}

}
