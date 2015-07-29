package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

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

	private EntJobDespliegueVersion job;

	public EjecutorDespliegueVersionJenkins(EntJobDespliegueVersion jobDeployVersion) {
		this.job = jobDeployVersion;
	}

	@Override
	public void ejecutar() {
		EntConfiguracionGeneral configuracionGeneral = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();

		EntServidor servidor = job.getServidor();
		EntVersion version =  job.getVersion();
		EntAmbienteAplicacion ambienteAplicacion = servidor.getAmbiente();
		EntAplicacion aplicacion = ambienteAplicacion.getAplicacion();
		String urlJobJenkins = aplicacion.getJobJenkinsDeployements();

		Builder request = ClientBuilder.newClient().target(urlJobJenkins).request();
		request.header("http-user", configuracionGeneral.getUsrJenkins());
		request.header("http-password", configuracionGeneral.getPwdJenkins());
		request.async().post( parametrosVersion(version, aplicacion, servidor) , new RespuestaJob(job));
	}

	private Entity<Form> parametrosVersion(EntVersion version, EntAplicacion aplicacion, EntServidor servidor){
		ServicioVersion servicio = ServicioVersion.getInstanciaTransaccional();
		List<EntVersionPropiedad> propiedades = servicio.buscaPropiedades(version);

		Form form = new Form();
		propiedades.forEach(propiedad -> form.param(propiedad.getPropiedad(), propiedad.getValor()));
		Set<String> setPropiedades = propiedades.stream().map(EntVersionPropiedad::getPropiedad).collect(Collectors.toSet());
		if (!setPropiedades.contains("AMBIENTE")){
			form.param("AMBIENTE", aplicacion.getAmbienteParaHacerDeployPorDefecto());
		}

		form.param("Servidor", servidor.getNombreServidorJenkins());

		return Entity.form(form);
	}



}

class RespuestaJob implements InvocationCallback<Response>{

	private EntJobDespliegueVersion job;

	public RespuestaJob(EntJobDespliegueVersion job) {
		this.job = job;
	}

	@Override
	public void completed(Response response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void failed(Throwable throwable) {
		ServicioJobDespliegueVersion.getInstanciaTransaccional().cambiarEstado(job, throwable.getMessage(), EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS);
		// TODO Auto-generated method stub

	}

}