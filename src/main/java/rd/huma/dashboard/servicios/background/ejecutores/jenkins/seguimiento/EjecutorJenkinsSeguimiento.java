package rd.huma.dashboard.servicios.background.ejecutores.jenkins.seguimiento;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import rd.huma.dashboard.model.jenkins.Actions;
import rd.huma.dashboard.model.jenkins.JenkinsJobStatus;
import rd.huma.dashboard.model.jenkins.Parameters;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.integracion.jenkins.EEstadoJobJenkins;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorJenkinsSeguimiento extends AEjecutor {

	private String url;
	private String urlBase;
	private Consumer<ResultadoSeguimientoJenkins> handlerResult;
	private JenkinsJobStatus jenkinsJob;
	private Predicate<Parameters> filtroEncontrar;


	public EjecutorJenkinsSeguimiento(String url,Predicate<Parameters> filtroEncontrar,  Consumer<ResultadoSeguimientoJenkins> handlerResult) {
		this(url,"lastBuild/api/json",filtroEncontrar,handlerResult);
	}

	public EjecutorJenkinsSeguimiento(String url, String sufijo,Predicate<Parameters> filtroEncontradoJob,  Consumer<ResultadoSeguimientoJenkins> handlerResult) {
		this.urlBase = url;
		this.url = url+sufijo;
		this.filtroEncontrar = filtroEncontradoJob;
		if (filtroEncontradoJob == null){
			throw new IllegalArgumentException("El Filtro no puede ser nulo");
		}
		this.handlerResult = handlerResult;
	}

	@Override
	public void ejecutar() {

		Response respuesta  = ClientBuilder.newClient().target(url).request().buildGet().invoke();
		if (respuesta.getStatus()!=200){
			handlerResult.accept(new ResultadoSeguimientoJenkins(EEstadoJobJenkins.NO_ENCONTRADO));
			return;
		}

		jenkinsJob = respuesta.readEntity(JenkinsJobStatus.class);
		Actions[] acciones = jenkinsJob.getActions();
		if (isEjecucionJobRetornoErroneo(acciones)){
			handlerResult.accept(new ResultadoSeguimientoJenkins(EEstadoJobJenkins.NO_ENCONTRADO));
			return;
		}
		if (Arrays.stream(acciones[0].getParameters()).filter(filtroEncontrar).findFirst().isPresent()){
			encontradoJob();
		}else{
			seguirBuscandoVersion();
		}
	}

	private void encontradoJob() {
		if (jenkinsJob.getResult() == null){
			handlerResult.accept(new ResultadoSeguimientoJenkins(EEstadoJobJenkins.PROCESO, jenkinsJob));
		}else{
			handlerResult.accept(new ResultadoSeguimientoJenkins("FAILURE".equals(jenkinsJob.getResult())?EEstadoJobJenkins.FALLIDO : EEstadoJobJenkins.EXITOSO,jenkinsJob));
		}
	}

	private void seguirBuscandoVersion(){
		int numeroAnterior = Integer.valueOf(jenkinsJob.getNumber())-1;
		ServicioVersion.getInstanciaTransaccional().ejecutarJob(new EjecutorJenkinsSeguimiento(urlBase+numeroAnterior+"/api/json",filtroEncontrar,handlerResult));
	}

	public EjecutorJenkinsSeguimiento setFiltroEncontrar(Predicate<Parameters> filtroEncontrar) {
		this.filtroEncontrar = filtroEncontrar;
		return this;
	}

	private boolean isEjecucionJobRetornoErroneo(Actions[] acciones){
		return ( acciones==null || acciones.length==0 || acciones[0].getParameters() == null || acciones[0].getParameters().length==0);
	}
}