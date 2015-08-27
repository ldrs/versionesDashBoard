package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.util.Collections;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
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
			DeployReporte deployReporte = new DeployReporte(jobDeployVersion);
			deployReporte.inicializar();
			deployReporte.ejecutar();


			DeployVersionScript deployScript = new DeployVersionScript(jobDeployVersion,false,Collections.emptyList());
			deployScript.inicializar();
			deployScript.ejecutar();
			enviarAlertaVersionSubiendo(jobDeployVersion.getVersion());
			servicioVersion.actualizarEstado(EEstadoVersion.EJECUTO_EXITOSO_JENKINS, jobDeployVersion.getVersion());


		}else{
			servicioVersion.actualizarEstado(EEstadoVersion.CANCELADA_POR_ERROR_DESPLIEGUE_JENKINS, jobDeployVersion.getVersion());
		}
	}


	private void enviarAlertaVersionSubiendo(EntVersion version){
		EntVersionAlerta alerta = new EntVersionAlerta();
		alerta.setAlerta(ETipoAlertaVersion.VERSION_SUBIENDO);
		alerta.setVersion(version);
		alerta.setMensaje(new StringBuilder(150).append("La aplicación del branch ")
												.append(version.getBranchOrigen()).append(" se esta subiendo en el ambiente <a href=\"").append(jobDeployVersion.getServidor().getRutaEntrada())
												.append("\">").append(jobDeployVersion.getServidor().getNombre()).append("</a>")
												.append(" Para más información de la versión entrar en <a href=\"http://dashboard.version.sigefint.gov.do/dashboard/version.html?versionId=").append(version.getId()).append("\">").append(version.getNumero()).append("</a>")
												.toString());
		servicioVersion.crearAlerta(alerta);
	}
}