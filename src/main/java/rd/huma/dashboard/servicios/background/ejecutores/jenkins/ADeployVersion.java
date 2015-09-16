package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.Base64;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
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
	private String credenciales;

	protected ServicioJobDespliegueVersion servicioJobDespliegueVersion;


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
		this.servicioJobDespliegueVersion = ServicioJobDespliegueVersion.getInstanciaTransaccional();
		this.credenciales = "Basic " + Base64.getEncoder().encodeToString( (configuracionGeneral.getUsrJenkins() + ":" + configuracionGeneral.getPwdJenkins()).getBytes() );

	}

	public EntJobDespliegueVersion getJob() {
		return job;
	}

	String getURLDeployEjecucionJob(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/").append(aplicacion.getNombreJobDeployJenkins()).append("/").toString();
	}

	String getURLDeployScriptEjecucionJob(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/").append(aplicacion.getNombreJobSQLJenkins()).append("/").toString();
	}

	String getURLDeployScriptReporteJob(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/").append(aplicacion.getNombreJobOracleReportJenkins()).append("/").toString();
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
		return  new InvocadorJenkins(credenciales)
		.adicionarParametro("SVN_AMBIENTE", version.getRutaSvnAmbiente())
		.adicionarParametro("Servidor", servidor.getNombreServidorJenkins())
		.adicionarParametro("version", version.getNumero());

	}
}