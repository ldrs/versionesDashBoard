package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorDespliegueVersionJenkins extends AEjecutor {
	static final Logger LOGGER = Logger.getLogger(EjecutorDespliegueVersionJenkins.class.getSimpleName());

	private EntJobDespliegueVersion job;

	public EjecutorDespliegueVersionJenkins(EntJobDespliegueVersion jobDeployVersion) {
		this.job = jobDeployVersion;
	}

	@Override
	public void ejecutar() {
		LOGGER.info(String.format("Intentando hacer el deploy de la version %s en el servidor %s", job.getVersion().getNumero(),job.getServidor().getNombre()));
		ServicioVersion servicio = ServicioVersion.getInstanciaTransaccional();

		EntConfiguracionGeneral configuracionGeneral = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();

		EntServidor servidor = job.getServidor();
		EntVersion version =  job.getVersion();
		EntAmbienteAplicacion ambienteAplicacion = servidor.getAmbiente();
		EntAplicacion aplicacion = ambienteAplicacion.getAplicacion();
		String urlBaseEjecucionJob = new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/").append(aplicacion.getNombreJobJenkins()).append("/").toString();

		String usernameAndPassword = configuracionGeneral.getUsrJenkins() + ":" + configuracionGeneral.getPwdJenkins();
		StringBuilder sbURL = new StringBuilder(300);
		sbURL.append("SVN_AMBIENTE=").append(version.getRutaSvnAmbiente()).append("&").append("Servidor").append("=").append(servidor.getNombreServidorJenkins())
		.append("&version=").append(version.getNumero())
		;

		servicio.buscaPropiedades(version).forEach(propiedad -> sbURL.append('&').append(propiedad.getPropiedad()).append('=').append(propiedad.getValor()));
		byte[] postData = sbURL.toString().getBytes(StandardCharsets.UTF_8);
		ServicioJobDespliegueVersion servicioJobDespliegueVersion = ServicioJobDespliegueVersion.getInstanciaTransaccional();

		try {

			HttpURLConnection con = (HttpURLConnection) new URL(urlBaseEjecucionJob+"buildWithParameters").openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() ));
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length", Integer.toString( postData.length ));
			con.setInstanceFollowRedirects( false );
			con.setUseCaches(false);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(postData);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();

			if (responseCode==201){
				LOGGER.info(String.format("Deploy ejecutando en jenkins de la version %s en el servidor %s", job.getVersion().getNumero(),job.getServidor().getNombre()));



				servicioJobDespliegueVersion.cambiarEstado(job, null, EEstadoJobDespliegue.EN_PROCESO_DEPLOY_JENKINS);
				servicioJobDespliegueVersion.seguimientoJenkinsSeguimientoDespliegue(job,urlBaseEjecucionJob);
			}else{
				servicioJobDespliegueVersion.cambiarEstado(job, "El codigo de response para el deploy no fue el esperad(201) y fue " + responseCode, EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS);
			}
		} catch (IOException e) {
			e.printStackTrace();
			servicioJobDespliegueVersion.cambiarEstado(job, null, EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS);
		}
	}

}