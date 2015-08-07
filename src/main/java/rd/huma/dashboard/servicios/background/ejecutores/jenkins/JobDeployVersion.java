package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.function.Consumer;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
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

	private InvocadorJenkins nuevoInvocador(){
		InvocadorJenkins invocadorJenkins = new InvocadorJenkins(credenciales);
		invocadorJenkins.adicionarParametro("SVN_AMBIENTE", version.getRutaSvnAmbiente())
		.adicionarParametro("Servidor", servidor.getNombreServidorJenkins())
		.adicionarParametro("version", version.getNumero());

		return invocadorJenkins;
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

	public void ejecutar() {
		List<EntVersionScript> scriptAntesEjecucion = servicioVersion.getScriptAntesEjecucion(job.getVersion());
		if (scriptAntesEjecucion.isEmpty()){
			deployVersion();
		}else{
			deployScriptAntesEjecucion(scriptAntesEjecucion);
		}
	}

	private void desployDespuesVersion(){
		List<EntVersionReporte> reportesVersion = servicioVersion.getReportesVersion(version);
		if (!reportesVersion.isEmpty()){}
	}


	private void deployScriptAntesEjecucion(List<EntVersionScript> scriptAntesEjecucion){
		EntJobDespliegueVersion jobScript = new EntJobDespliegueVersion();
		jobScript.setServidor(job.getServidor());
		jobScript.setVersion(version);
		jobScript.setTipoDespliegue(ETipoDespliegueJob.SCRIPT);
		servicioJobDespliegueVersion.nuevoJob(jobScript);

		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptEjecucionJob()+"buildWithParameters");
//TODO gerararArchivo
		invocadorJenkins.invocar();

	}

	private File generarFileFromUrls(List<String> urls){

	}

	class SeguimientoJobVersion extends SeguimientoJob{

		{
			setHandlerResult(r -> r? desployDespuesVersion() : falloJobDespliegue().accept(null));
		}

		@Override
		public void ejecutar() {
			EjecutorDespliegueVersionJenkins.LOGGER.info(String.format("Deploy ejecutando en jenkins de la version %s en el servidor %s", job.getVersion().getNumero(),job.getServidor().getNombre()));
			super.ejecutar();
		}

	}


}


