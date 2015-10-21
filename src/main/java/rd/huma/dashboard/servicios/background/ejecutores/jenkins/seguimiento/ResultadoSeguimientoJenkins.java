package rd.huma.dashboard.servicios.background.ejecutores.jenkins.seguimiento;

import rd.huma.dashboard.model.jenkins.JenkinsJobStatus;
import rd.huma.dashboard.servicios.integracion.jenkins.EEstadoJobJenkins;

public class ResultadoSeguimientoJenkins {

	private EEstadoJobJenkins estado;
	private JenkinsJobStatus estadoJob;

	public ResultadoSeguimientoJenkins(EEstadoJobJenkins estado) {
		this.estado = estado;
	}

	public ResultadoSeguimientoJenkins(EEstadoJobJenkins estado, JenkinsJobStatus estadoJob) {
		this.estado = estado;
		this.estadoJob= estadoJob;
	}

	public EEstadoJobJenkins getResultado() {
		return estado;
	}

	public JenkinsJobStatus getEstadoJob() {
		return estadoJob;
	}
}