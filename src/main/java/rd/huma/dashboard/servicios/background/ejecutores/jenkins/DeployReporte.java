package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.servicios.integracion.jenkins.InvocadorJenkins;

public class DeployReporte extends ADeployVersion{

	public DeployReporte(EntJobDespliegueVersion job) {
		super(job);
	}

	@Override
	public void ejecutar() {
		super.ejecutar();
		deployReporte();
	}

	void deployReporte() {
		if (getServicioVersion().buscaReportesVersion(getJob().getVersion()).isEmpty()){
			return;
		}

		EntJobDespliegueVersion job = new EntJobDespliegueVersion();
		job.setVersion(getJob().getVersion());
		job.setTipoDespliegue(ETipoDespliegueJob.REPORTE);
		job.setServidor(getJob().getServidor());
		job.setFilaDespliegue( getJob().getFilaDespliegue());
		servicioJobDespliegueVersion.nuevoJob(job);

		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptReporteJob()+"buildWithParameters");
		invocadorJenkins.adicionarParametro("REPORTE", job.getId());
		invocadorJenkins.adicionarParametro("URL_SCRIPT_FILE", getURLReporteFile()+job.getId());
		invocadorJenkins.adicionarParametro("JOB_ID", job.getId());

		invocadorJenkins.invocar();
	}

}
