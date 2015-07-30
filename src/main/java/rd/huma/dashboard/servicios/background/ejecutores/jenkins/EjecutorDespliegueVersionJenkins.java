package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.net.URI;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.json.Json;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedad;
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

		EntConfiguracionGeneral configuracionGeneral = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();

		EntServidor servidor = job.getServidor();
		EntVersion version =  job.getVersion();
		EntAmbienteAplicacion ambienteAplicacion = servidor.getAmbiente();
		EntAplicacion aplicacion = ambienteAplicacion.getAplicacion();
		String urlJobJenkins = aplicacion.getJobJenkinsDeployements();



		Builder request = ClientBuilder.newClient().target(urlJobJenkins).request();

		String usernameAndPassword = configuracionGeneral.getUsrJenkins() + ":" + configuracionGeneral.getPwdJenkins();

	    request.header("Authorization", "Basic " + Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() ));

	    Response response = request.buildPost(parametrosVersion(version, aplicacion, servidor)).invoke();

		String respuesta = response.readEntity(String.class);
		if (response.getStatus() == Status.CREATED.getStatusCode()){
			URI pooling =  response.getLocation();
		}else{

		}

		//request.async().post( parametrosVersion(version, aplicacion, servidor) , new RespuestaJob(job));
	}

	private Entity<Form> parametrosVersion(EntVersion version, EntAplicacion aplicacion, EntServidor servidor){
		ServicioVersion servicio = ServicioVersion.getInstanciaTransaccional();
		List<EntVersionPropiedad> propiedades = servicio.buscaPropiedades(version);

		Form form = new Form();
		propiedades.forEach(propiedad -> form.param(propiedad.getPropiedad(), propiedad.getValor()));

		form.param("AMBIENTE", version.getRutaSvnAmbiente());
		form.param("Servidor", servidor.getNombreServidorJenkins());

		form.param("json", Json.createObjectBuilder().add("parameter", Json.createArrayBuilder().
				add(Json.createObjectBuilder().add("name", "AMBIENTE").add("value", version.getRutaSvnAmbiente())) ).build().toString());

		return Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE);
	}



}

class RespuestaJob implements InvocationCallback<Response>{

	private EntJobDespliegueVersion job;

	public RespuestaJob(EntJobDespliegueVersion job) {
		this.job = job;
	}

	@Override
	public void completed(Response response) {
		String respuesta = response.readEntity(String.class);
		EjecutorDespliegueVersionJenkins.LOGGER.info(String.format("El job deploy de la version %s en el servidor %s ha sido exitoso", job.getVersion().getNumero(),job.getServidor().getNombre()));

	}

	@Override
	public void failed(Throwable throwable) {
		EjecutorDespliegueVersionJenkins.LOGGER.info(String.format("El job deploy de la version %s en el servidor %s fallo con el error %s", job.getVersion().getNumero(),job.getServidor().getNombre(),throwable.getMessage()));


		ServicioJobDespliegueVersion.getInstanciaTransaccional().cambiarEstado(job, throwable.getMessage(), EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS);

		// TODO Auto-generated method stub

	}

}