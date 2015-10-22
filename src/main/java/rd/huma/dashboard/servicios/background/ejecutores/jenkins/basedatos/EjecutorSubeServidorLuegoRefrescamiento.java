package rd.huma.dashboard.servicios.background.ejecutores.jenkins.basedatos;

import java.util.List;

import rd.huma.dashboard.model.jenkins.JenkinsJobStatus;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosActualizacion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoServidor;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorScriptTodos;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.seguimiento.ResultadoSeguimientoJenkins;
import rd.huma.dashboard.servicios.integracion.jenkins.EEstadoJobJenkins;
import rd.huma.dashboard.servicios.integracion.jenkins.InvocadorJenkins;
import rd.huma.dashboard.servicios.integracion.jenkins.ResultadoInvocadorJenkins;
import rd.huma.dashboard.servicios.integracion.jenkins.ServicioJenkins;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorSubeServidorLuegoRefrescamiento extends AEjecutor {

	private EntRepositorioDatosActualizacion repositorio;
	private ServicioVersion servicioVersion;
	private ServicioServidor servicioServidor;
	private ServicioRepositorioDatos servicioRepositorioDatos;
	private EntConfiguracionGeneral configuracionGeneral;
	private ServicioJobDespliegueVersion servicioJob;

	public EjecutorSubeServidorLuegoRefrescamiento(EntRepositorioDatosActualizacion repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void ejecutar() {
		this.servicioRepositorioDatos = ServicioRepositorioDatos.getInstanciaTransaccional();
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
		this.servicioServidor = ServicioServidor.getInstanciaTransaccional();
		this.configuracionGeneral = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();
		this.servicioJob = ServicioJobDespliegueVersion.getInstanciaTransaccional();

		for (EntServidor servidor : servicioRepositorioDatos.getServidores(repositorio)) {
			EntJobDespliegueVersion job = new EntJobDespliegueVersion();
			job.setServidor(servidor);
			job.setTipoDespliegue(ETipoDespliegueJob.SUBIR_SERVIDOR);

			servicioJob.nuevoJob(job);


			new InvocadorJenkins(configuracionGeneral)
														.setURL(ServicioJenkins.para(servidor.getAmbiente().getAplicacion()).getURLSubeServidores())
														.adicionarParametro("SVN_AMBIENTE", servidor.getAmbiente().getAplicacion().getRutaSvnAmbiente())
														.adicionarParametro("Servidor", servidor.getNombreServidorJenkins())
														.adicionarParametro("JOB_ID",  job.getId())

														.setFiltroEncontrar(p -> "JOB_ID".equals(p.getName()) && job.getId().equals(p.getValue()))
														.responseHanlder(r -> resultadoEjecucion(r,job,servidor))
														.invocar();
		}



	}


	private void resultadoEjecucion(ResultadoInvocadorJenkins resultadoInvocadorJenkins,EntJobDespliegueVersion job, EntServidor servidor) {
		ResultadoSeguimientoJenkins resultadoJob = resultadoInvocadorJenkins.getResultado();
		if (resultadoJob!=null){
			JenkinsJobStatus estadoJenkins = resultadoJob.getEstadoJob();
			if (estadoJenkins != null){
				job.setURL(estadoJenkins.getUrl());
				job.setInicioJob(estadoJenkins.getDuration());
			}
		}
		job.setEstado(resultadoInvocadorJenkins.getEstado() == EEstadoJobJenkins.EXITOSO ? EEstadoJobDespliegue.DEPLOY_EXITOSO
				: resultadoInvocadorJenkins.getEstado() == EEstadoJobJenkins.PROCESO ?  EEstadoJobDespliegue.EN_PROCESO_DEPLOY_JENKINS
						: EEstadoJobDespliegue.FALLIDO_DEPLOY_JENKINS
				);


		if (job.getEstado() == EEstadoJobDespliegue.DEPLOY_EXITOSO){
			subirVersionesQueYaTenianServidor(servidor);
		}
	}

	private void subirVersionesQueYaTenianServidor(EntServidor servidor){

		servidor.setEstadoServidor(servidor.getVersionActual() == null? EEstadoServidor.LIBRE : EEstadoServidor.OCUPADO);
		servicioServidor.actualizarServidor(servidor);
		if (servidor.getVersionActual()!=null){
			List<EntVersionScript> scripts = servicioVersion.buscaScript(servidor.getVersionActual());
			if (!scripts.isEmpty()){
				servicioVersion.ejecutarJob(new EjecutorScriptTodos(servidor.getVersionActual()));
			}
		}

	}
}