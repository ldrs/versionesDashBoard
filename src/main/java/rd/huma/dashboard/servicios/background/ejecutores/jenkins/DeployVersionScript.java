package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.integracion.svn.util.ServicioUltimaRevisionSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.UltimaRevision;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;


public class DeployVersionScript extends ADeployVersion {

	private boolean antes;
	private List<EntVersionScript> scripts;
	private ServicioVersion servicioVersion;
	private EntVersion version;
	private ServicioPersona servicioPersona;
	private ServicioRepositorioDatos servicioRepositorioDatos;


	public DeployVersionScript(EntJobDespliegueVersion job, boolean antes, List<EntVersionScript> scripts) {
		super(job);
		this.antes = antes;
		this.scripts = scripts;
	}

	@Override
	void inicializar() {
		super.inicializar();
		servicioPersona = ServicioPersona.getInstanciaTransaccional();
		servicioRepositorioDatos = ServicioRepositorioDatos.getInstanciaTransaccional();
	}

	@Override
	public void ejecutar() {
		this.servicioVersion = getServicioVersion();
		this.version = getVersion();
		if (antes){
			deployScriptAntesEjecucion(scripts);
		}else{
			desployDespuesVersionScript();
		}

	}


	void desployDespuesVersionScript(){
		List<EntVersionScript> scriptsDespues = servicioVersion.getScriptDespuesEjecucion(version);
		if (!scriptsDespues.isEmpty()){
			deployScriptEjecucion(scriptsDespues, ETipoScript.DESPUES_SUBIDA);
		}
	}

	void deployScriptAntesEjecucion(List<EntVersionScript> scriptAntesEjecucion){
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

					servicioRepositorioDatos.crearScript(repositorioDatosScriptEjecutados);
				}
			}
		}


		InvocadorJenkins invocadorJenkins = nuevoInvocador();
		invocadorJenkins.setURL(getURLDeployScriptEjecucionJob()+"buildWithParameters");
		invocadorJenkins.adicionarParametro("URL_SCRIPT_FILE", getURLScriptFile() + jobScript.getId());
		invocadorJenkins.adicionarParametro("JOB_ID", jobScript.getId());
		invocadorJenkins.invocar();
	}

}
