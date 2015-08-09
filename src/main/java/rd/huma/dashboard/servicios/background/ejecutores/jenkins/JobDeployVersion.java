package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.Base64;
import java.util.List;
import java.util.function.Consumer;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

class JobDeployVersion {

	private EntJobDespliegueVersion job;

	private ServicioVersion servicioVersion;
	private EntConfiguracionGeneral configuracionGeneral;
	private EntServidor servidor;
	private EntVersion version;
	private EntAmbienteAplicacion ambienteAplicacion;
	private EntAplicacion aplicacion;
	private String credenciales;

	private ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	public JobDeployVersion(EntJobDespliegueVersion job) {
		this.job = job;
	}

	void inicializar(){
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



	private void deployVersion(){
		String urlBaseEjecucionJob =  getURLDeployEjecucionJob();
		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(urlBaseEjecucionJob+"buildWithParameters");

		servicioVersion.buscaPropiedades(version).forEach(propiedad -> invocadorJenkins.adicionarParametro(propiedad.getPropiedad(), propiedad.getValor()));

		invocadorJenkins.responseHanlder(new ResponseHandler( new SeguimientoJobVersion(), falloJobDespliegue()));
		invocadorJenkins.invocar();

	}

	private Consumer<Void> falloJobDespliegue(){
		return e -> servicioJobDespliegueVersion.cambiarEstado(job, EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS);
	}




	private String getURLDeployEjecucionJob(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/").append(aplicacion.getNombreJobDeployJenkins()).append("/").toString();
	}

	private String getURLDeployScriptEjecucionJob(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/").append(aplicacion.getNombreJobSQLJenkins()).append("/").toString();
	}
	
	private String getURLDeployScriptReporteJob(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/").append(aplicacion.getNombreJobOracleReportJenkins()).append("/").toString();
	}

	public void ejecutar() {
		List<EntVersionScript> scriptAntesEjecucion = servicioVersion.getScriptAntesEjecucion(job.getVersion());
		if (scriptAntesEjecucion.isEmpty()){
			deployVersion();
		}else{
			deployScriptAntesEjecucion(scriptAntesEjecucion);
		}
	}

	private void desployDespuesVersion(){
		if (!servicioVersion.getReportesVersion(version).isEmpty()){
			deployReporte();
		}
		
		if (!servicioVersion.getScriptAntesEjecucion(version).isEmpty()){
			deployScriptDespuesEjecucion();
		}
		
	}


	private void deployReporte() {
		EntJobDespliegueVersion job = new EntJobDespliegueVersion();
		job.setVersion(version);
		job.setTipoDespliegue(ETipoDespliegueJob.REPORTE);
		job.setServidor(servidor);
		servicioJobDespliegueVersion.nuevoJob(job);
		
		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptReporteJob()+"buildWithParameters");
		invocadorJenkins.adicionarParametro("REPORTE", job.getId());
		invocadorJenkins.invocar();
	}

	private void deployScriptAntesEjecucion(List<EntVersionScript> scriptAntesEjecucion){


		EntJobDespliegueVersion jobScript = new EntJobDespliegueVersion();
		jobScript.setServidor(job.getServidor());
		jobScript.setVersion(version);
		jobScript.setTipoDespliegue(ETipoDespliegueJob.SCRIPT);
		jobScript.setTipoScript(ETipoScript.ANTES_SUBIDA);
		servicioJobDespliegueVersion.nuevoJob(jobScript);

		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptEjecucionJob()+"buildWithParameters");
		invocadorJenkins.adicionarParametro("SQL", jobScript.getId());
		invocadorJenkins.invocar();
	}
	
	private void deployScriptDespuesEjecucion(){


		EntJobDespliegueVersion jobScript = new EntJobDespliegueVersion();
		jobScript.setServidor(job.getServidor());
		jobScript.setVersion(version);
		jobScript.setTipoDespliegue(ETipoDespliegueJob.SCRIPT);
		jobScript.setTipoScript(ETipoScript.DESPUES_SUBIDA);
		servicioJobDespliegueVersion.nuevoJob(jobScript);

		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptEjecucionJob()+"buildWithParameters");
		invocadorJenkins.adicionarParametro("SQL", jobScript.getId());
		invocadorJenkins.invocar();
	}


	private InvocadorJenkins nuevoInvocador(){
		return  new InvocadorJenkins(credenciales)
		.adicionarParametro("SVN_AMBIENTE", version.getRutaSvnAmbiente())
		.adicionarParametro("Servidor", servidor.getNombreServidorJenkins())
		.adicionarParametro("version", version.getNumero());

	}


	class SeguimientoJobVersion extends SeguimientoJob{

		{
			setHandlerResult(this::manejaResultado);
		}

		void manejaResultado(boolean r){
			if (r){
				desployDespuesVersion();
			}else{
				falloJobDespliegue().accept(null);
			}
		}


		@Override
		public void ejecutar() {
			EjecutorDespliegueVersionJenkins.LOGGER.info(String.format("Deploy ejecutando en jenkins de la version %s en el servidor %s", job.getVersion().getNumero(),job.getServidor().getNombre()));
			super.ejecutar();
		}

	}


}


