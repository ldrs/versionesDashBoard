package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.integracion.jenkins.InvocadorJenkins;
import rd.huma.dashboard.servicios.integracion.jenkins.ServicioJenkins;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

abstract class ADeployVersion {

	protected EntJobDespliegueVersion job;

	private ServicioVersion servicioVersion;
	private EntConfiguracionGeneral configuracionGeneral;
	private EntServidor servidor;
	private EntVersion version;
	private EntAmbienteAplicacion ambienteAplicacion;
	private EntAplicacion aplicacion;

	protected ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	private ServicioJenkins servicioJenkins;


	public ADeployVersion(EntJobDespliegueVersion job) {
		this.job = job;
	}

	private void inicializar(){
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();

		this.configuracionGeneral = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();

		this.servidor = job.getServidor();
		this.version =  job.getVersion();
		this.ambienteAplicacion = servidor.getAmbiente();
		this.aplicacion = ambienteAplicacion.getAplicacion();
		this.servicioJenkins = ServicioJenkins.para(aplicacion);
		this.servicioJobDespliegueVersion = ServicioJobDespliegueVersion.getInstanciaTransaccional();

	}

	public EntJobDespliegueVersion getJob() {
		return job;
	}

	String getURLDeployEjecucionJob(){
		return servicioJenkins.getURLDeployVersion();
	}

	String getURLDeployScriptEjecucionJob(){
		return servicioJenkins.getURLDeployJobScript();
	}

	String getURLDeployScriptReporteJob(){
		return servicioJenkins.getURLDeployJobReporte();
	}

	String getURLScriptFile(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaDashBoard()).append("api/versionArchivo/script/").toString();
	}

	String getURLReporteFile(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaDashBoard()).append("api/versionArchivo/reporte/").toString();
	}

	public void ejecutar(){
		inicializar();
	}

	public EntVersion getVersion() {
		return version;
	}

	public ServicioVersion getServicioVersion() {
		return servicioVersion;
	}

	public EntConfiguracionGeneral getConfiguracionGeneral() {
		return configuracionGeneral;
	}



	InvocadorJenkins nuevoInvocador(){
		return  new InvocadorJenkins(configuracionGeneral)
		.adicionarParametro("SVN_AMBIENTE", version.getRutaSvnAmbiente())
		.adicionarParametro("Servidor", servidor.getNombreServidorJenkins())
		.adicionarParametro("version", version.getNumero())
		.setFiltroEncontrar(parametro ->  "version".equals(parametro.getName()) && "".equals(getVersion().getNumero()));
	}
}