package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.Base64;
import java.util.List;
import java.util.function.Consumer;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.integracion.svn.util.ServicioUltimaRevisionSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.UltimaRevision;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
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
	private ServicioPersona servicioPersona;
	private ServicioRepositorioDatos servicioRepositorioDatos;

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
		servicioPersona = ServicioPersona.getInstanciaTransaccional();
		servicioRepositorioDatos = ServicioRepositorioDatos.getInstanciaTransaccional();
	}

	public EntJobDespliegueVersion getJob() {
		return job;
	}

	private void deployVersion(){
		String urlBaseEjecucionJob =  getURLDeployEjecucionJob();
		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(urlBaseEjecucionJob+"buildWithParameters");
		invocadorJenkins.adicionarParametro("URL_CALLBACK_DASHBOARD", configuracionGeneral.getRutaDashBoard()+"api/versionConfirma/"+ job.getId());

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

	private String getURLScriptFile(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaDashBoard()).append("api/versionArchivo/script/").toString();
	}

	private String getURLReporteFile(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaDashBoard()).append("api/versionArchivo/reporte/").toString();
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
		List<EntVersionScript> scriptsDespues = servicioVersion.getScriptDespuesEjecucion(version);

		if (!scriptsDespues.isEmpty()){
			deployScriptEjecucion(scriptsDespues, ETipoScript.DESPUES_SUBIDA);
		}
	}


	private void deployReporte() {
		EntJobDespliegueVersion job = new EntJobDespliegueVersion();
		job.setVersion(version);
		job.setTipoDespliegue(ETipoDespliegueJob.REPORTE);
		job.setServidor(servidor);
		job.setFilaDespliegue(this.job.getFilaDespliegue());
		servicioJobDespliegueVersion.nuevoJob(job);

		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptReporteJob()+"buildWithParameters");
		invocadorJenkins.adicionarParametro("REPORTE", job.getId());
		invocadorJenkins.adicionarParametro("URL_SCRIPT_FILE", getURLReporteFile()+job.getId());

		invocadorJenkins.invocar();
	}

	private void deployScriptAntesEjecucion(List<EntVersionScript> scriptAntesEjecucion){
		deployScriptEjecucion(scriptAntesEjecucion, ETipoScript.ANTES_SUBIDA);
	}

	private void deployScriptEjecucion(List<EntVersionScript> scripts, ETipoScript tipoScript){


		EntJobDespliegueVersion jobScript = new EntJobDespliegueVersion();
		jobScript.setServidor(job.getServidor());
		jobScript.setVersion(version);
		jobScript.setFilaDespliegue(this.job.getFilaDespliegue());
		jobScript.setTipoDespliegue(ETipoDespliegueJob.SCRIPT);
		jobScript.setTipoScript(tipoScript);
		servicioJobDespliegueVersion.nuevoJob(jobScript);

		for (EntVersionScript versionScript: scripts){
			UltimaRevision ultimaRevision = new ServicioUltimaRevisionSVN(versionScript.getUrlScript()).revision();
			if (ultimaRevision!=null){
				List<EntRepositorioDatosScriptEjecutados> scriptEjecutados = servicioRepositorioDatos.getScriptEjecutados(job.getServidor().getBaseDatos(), versionScript.getUrlScript());

				if (scriptEjecutados.stream().filter(s -> s.getEstadoScript().puedeEjecutarDeNuevo())
								.filter(s -> s.getRevisionScript()<=ultimaRevision.getNumeroRevision())
								.count()==0){

					EntRepositorioDatosScriptEjecutados repositorioDatosScriptEjecutados = new EntRepositorioDatosScriptEjecutados();
					repositorioDatosScriptEjecutados.setRepositorioDatos(job.getServidor().getBaseDatos());
					repositorioDatosScriptEjecutados.setScript(versionScript);
					repositorioDatosScriptEjecutados.setAutorScript(servicioPersona.buscaOCreaPersona(ultimaRevision.getUsuarioSVN()));
					repositorioDatosScriptEjecutados.setRevisionScript(ultimaRevision.getNumeroRevision());
					repositorioDatosScriptEjecutados.setJob(jobScript);
				}
			}
		}


		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptEjecucionJob()+"buildWithParameters");
		invocadorJenkins.responseHanlder((new ResponseHandler( new SeguimientoJobScript(tipoScript), falloJobDespliegue())));
		invocadorJenkins.adicionarParametro("URL_SCRIPT_FILE", getURLScriptFile() + jobScript.getId());
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
			setValor(job);
			setUrlSeguimiento(getURLDeployEjecucionJob());
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
			setUrlSeguimiento(getURLDeployEjecucionJob());
			EjecutorDespliegueVersionJenkins.LOGGER.info(String.format("Deploy ejecutando en jenkins de la version %s en el servidor %s", job.getVersion().getNumero(),job.getServidor().getNombre()));
			super.ejecutar();
		}

	}


	class SeguimientoJobScript extends SeguimientoJob{

		private ETipoScript tipoScript;

		public SeguimientoJobScript(ETipoScript tipoScript) {
			this.tipoScript = tipoScript;
			setHandlerResult(this::manejaResultado);
			setValor(job);
			setUrlSeguimiento(getURLDeployScriptEjecucionJob());
		}


		void manejaResultado(boolean r){
			if (r){
				if (tipoScript == ETipoScript.ANTES_SUBIDA){
					deployVersion();
				}
			}else{
				falloJobDespliegue().accept(null);
			}
		}

		@Override
		public void ejecutar() {
			setUrlSeguimiento(getURLDeployScriptEjecucionJob());
			EjecutorDespliegueVersionJenkins.LOGGER.info(String.format("Ejecutando los scripts % de la version %s en el servidor %s", tipoScript.name(), job.getVersion().getNumero(),job.getServidor().getNombre()));
			super.ejecutar();
		}

	}
}