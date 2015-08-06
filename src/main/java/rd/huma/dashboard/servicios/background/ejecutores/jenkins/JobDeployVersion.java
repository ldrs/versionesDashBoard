package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
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

		byte[] postData = getPostData();

		try {

			HttpURLConnection con = (HttpURLConnection) new URL(urlBaseEjecucionJob+"buildWithParameters").openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", credenciales);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length", Integer.toString( postData.length ));
			con.setInstanceFollowRedirects( false );
			con.setUseCaches(false);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(postData);
			wr.flush();
			wr.close();

			manejaResponse(urlBaseEjecucionJob,con.getResponseCode());


		} catch (IOException e) {
			e.printStackTrace();
			servicioJobDespliegueVersion.cambiarEstado(job, EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS);
		}
	}


	private void despuesSubida(){
		List<EntVersionScript> scriptDespuesEjecucion = servicioVersion.getScriptDespuesEjecucion(version);
		if (scriptDespuesEjecucion.isEmpty()){
			deployReporte();
		}else{

		}

	}

	private void deployReporte(){
		List<EntVersionReporte> reportesVersion = servicioVersion.getReportesVersion(version);
		if (!reportesVersion.isEmpty()){}
	}



	private void manejaResponse(String urlBaseEjecucionJob, int responseCode){
		if (responseCode==201){
			EjecutorDespliegueVersionJenkins.LOGGER.info(String.format("Deploy ejecutando en jenkins de la version %s en el servidor %s", job.getVersion().getNumero(),job.getServidor().getNombre()));



			servicioJobDespliegueVersion.cambiarEstado(job, EEstadoJobDespliegue.EN_PROCESO_DEPLOY_JENKINS);
			servicioJobDespliegueVersion.seguimientoJenkinsSeguimientoDespliegue(job,urlBaseEjecucionJob);
		}else{
			servicioJobDespliegueVersion.cambiarEstado(job, EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS);
		}
	}

	private byte[] getPostData(){
		StringBuilder sbURL = new StringBuilder(300);
		sbURL.append("SVN_AMBIENTE=").append(version.getRutaSvnAmbiente()).append("&").append("Servidor").append("=").append(servidor.getNombreServidorJenkins())
		.append("&version=").append(version.getNumero())
		;

		servicioVersion.buscaPropiedades(version).forEach(propiedad -> sbURL.append('&').append(propiedad.getPropiedad()).append('=').append(propiedad.getValor()));
		return sbURL.toString().getBytes(StandardCharsets.UTF_8);
	}

	private String getURLDeployEjecucionJob(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/").append(aplicacion.getNombreJobDeployJenkins()).append("/").toString();
	}

	public void ejecutar() {
		List<EntVersionScript> scriptAntesEjecucion = servicioVersion.getScriptAntesEjecucion(job.getVersion());
		if (scriptAntesEjecucion.isEmpty()){
			deployVersion();
		}else{

		}
	}


}
