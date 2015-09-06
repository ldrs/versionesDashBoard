package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.Collections;
import java.util.List;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.integracion.svn.util.ServicioUltimaRevisionSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.UltimaRevision;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorConfirmacionVersion extends AEjecutor {

	private EntJobDespliegueVersion jobDeployVersion;
	private ServicioVersion servicioVersion;

	public EjecutorConfirmacionVersion(EntJobDespliegueVersion jobDespliegue) {
		this.jobDeployVersion = jobDespliegue;
	}

	@Override
	public void ejecutar() {
		new EjecutorJenkinsSeguimientoDespliegue(jobDeployVersion.getURL(), jobDeployVersion, this::resultado).ejecutar();
	}

	private void resultado(Boolean resultado){
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
		if (resultado){
			enviarAlertaVersionSubiendo(jobDeployVersion);
			
			enviarAJobReporteSiNecesita();
			
			enviarAJobScriptSiNecesita();

		}else{
			servicioVersion.actualizarEstado(EEstadoVersion.CANCELADA_POR_ERROR_DESPLIEGUE_JENKINS, jobDeployVersion.getVersion());
		}
	}
	
	private void enviarAJobReporteSiNecesita(){
		List<EntVersionReporte> reportes = servicioVersion.buscaReportesVersion(jobDeployVersion.getVersion());
		if (!reportes.isEmpty()){
			DeployReporte deployReporte = new DeployReporte(jobDeployVersion);
			deployReporte.inicializar();
			deployReporte.ejecutar();
			
		}
	}
	
	private void enviarAJobScriptSiNecesita(){
		List<EntVersionScript> scriptEjecucion = servicioVersion.getScriptDespuesEjecucion(jobDeployVersion.getVersion());
		if (!scriptEjecucion.isEmpty() && tieneScriptPorEjecutar(scriptEjecucion)){
			
			DeployVersionScript deployScript = new DeployVersionScript(jobDeployVersion,false,Collections.emptyList());
			deployScript.inicializar();
			deployScript.ejecutar();
			servicioVersion.actualizarEstado(EEstadoVersion.EJECUTO_EXITOSO_JENKINS, jobDeployVersion.getVersion());
		}

	}


	private void enviarAlertaVersionSubiendo(EntJobDespliegueVersion job){
		EntVersion version = job.getVersion();
		EntVersionAlerta alerta = new EntVersionAlerta();
		alerta.setAlerta(ETipoAlertaVersion.VERSION_SUBIENDO);
		alerta.setVersion(version);
		alerta.setAmbiente(job.getFilaDespliegue().getAmbiente().getAmbiente());
		alerta.setMensaje(new StringBuilder(150).append("La aplicación del branch ")
												.append(version.getBranchOrigen()).append(" se esta subiendo en el ambiente ").append(jobDeployVersion.getServidor().getRutaEntrada())
												.append(" Para más información de la versión(").append(version.getNumero())
												.append(") entrar en http://dashboard.version.sigefint.gov.do/dashboard/version.html?versionId=").append(version.getId())
												.append(" debe durar al menos 2 minutos para que la versión este disponible.")
												.toString());
		servicioVersion.crearAlerta(alerta);
	}
	
	private boolean tieneScriptPorEjecutar(List<EntVersionScript> scripts){
		ServicioRepositorioDatos servicioRepositorioDatos = ServicioRepositorioDatos.getInstanciaTransaccional();
		
		for (EntVersionScript versionScript: scripts){
			UltimaRevision ultimaRevision = new ServicioUltimaRevisionSVN(versionScript.getUrlScript()).revision();
			if (ultimaRevision!=null){
				List<EntRepositorioDatosScriptEjecutados> scriptEjecutados = servicioRepositorioDatos.getScriptEjecutados(jobDeployVersion.getServidor().getBaseDatos(), versionScript.getUrlScript());

				if (scriptEjecutados.stream().filter(s -> s.getEstadoScript().puedeEjecutarDeNuevo())
								.filter(s -> s.getRevisionScript()<=ultimaRevision.getNumeroRevision())
								.count()==0){
					return true;
				}
			}
		}
		return false;
	}
}