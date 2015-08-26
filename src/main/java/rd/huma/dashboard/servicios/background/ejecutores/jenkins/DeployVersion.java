package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;

public class DeployVersion extends ADeployVersion{

	public DeployVersion(EntJobDespliegueVersion job) {
		super(job);
	}

	@Override
	public void ejecutar() {
		deployVersion();
	}

	void deployVersion(){
		String urlBaseEjecucionJob =  getURLDeployEjecucionJob();
		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(urlBaseEjecucionJob+"buildWithParameters");
		invocadorJenkins.adicionarParametro("URL_CALLBACK_DASHBOARD", getConfiguracionGeneral().getRutaDashBoard()+"api/versionConfirma/"+ job.getId());

		getServicioVersion().buscaPropiedades(getVersion()).forEach(propiedad -> invocadorJenkins.adicionarParametro(propiedad.getPropiedad(), propiedad.getValor()));

		invocadorJenkins.adicionarParametro("JOB_ID", getJob().getId());
		invocadorJenkins.invocar();

	}

}
