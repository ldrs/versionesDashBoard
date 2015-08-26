package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;

public class DeployReporte extends ADeployVersion{

	public DeployReporte(EntJobDespliegueVersion job) {
		super(job);
	}

	@Override
	public void ejecutar() {
		deployReporte();
	}

	void deployReporte() {
		if (getServicioVersion().buscaReportesVersion(getJob().getVersion()).isEmpty()){
			return;
		}

		EntJobDespliegueVersion job = new EntJobDespliegueVersion();
		job.setVersion(job.getVersion());
		job.setTipoDespliegue(ETipoDespliegueJob.REPORTE);
		job.setServidor(getJob().getServidor());
		job.setFilaDespliegue( this.job.getFilaDespliegue());
		servicioJobDespliegueVersion.nuevoJob(job);

		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptReporteJob()+"buildWithParameters");
		invocadorJenkins.adicionarParametro("REPORTE", job.getId());
		invocadorJenkins.adicionarParametro("URL_SCRIPT_FILE", getURLReporteFile()+job.getId());
		invocadorJenkins.adicionarParametro("JOB_ID", job.getId());

		invocadorJenkins.invocar();
	}

}
