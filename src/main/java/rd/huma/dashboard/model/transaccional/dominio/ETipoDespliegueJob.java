package rd.huma.dashboard.model.transaccional.dominio;

import rd.huma.dashboard.servicios.integracion.jenkins.ServicioJenkins;

public enum ETipoDespliegueJob {

	VERSION{
		@Override
		public String getUrlDeploy(ServicioJenkins servicioJenkins) {
			return servicioJenkins.getURLDeployVersion();
		}
	},
	SCRIPT{
		@Override
		public String getUrlDeploy(ServicioJenkins servicioJenkins) {
			return servicioJenkins.getURLDeployJobScript();
		}
	},
	REPORTE{
		@Override
		public String getUrlDeploy(ServicioJenkins servicioJenkins) {
			return servicioJenkins.getURLDeployJobReporte();
		}
	};

	public abstract String getUrlDeploy(ServicioJenkins servicioJenkins);
}