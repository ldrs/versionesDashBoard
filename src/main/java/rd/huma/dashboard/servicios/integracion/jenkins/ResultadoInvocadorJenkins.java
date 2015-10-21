package rd.huma.dashboard.servicios.integracion.jenkins;

import rd.huma.dashboard.servicios.background.ejecutores.jenkins.seguimiento.ResultadoSeguimientoJenkins;

public class ResultadoInvocadorJenkins {

	private EEstadoJobJenkins estado;
	private ResultadoSeguimientoJenkins resultado;

	public ResultadoInvocadorJenkins(EEstadoJobJenkins estado) {
		this.estado = estado;
	}

	public ResultadoInvocadorJenkins(ResultadoSeguimientoJenkins resultado) {
		this.resultado = resultado;
	}

	public EEstadoJobJenkins getEstado() {
		return estado;
	}

	public ResultadoSeguimientoJenkins getResultado() {
		return resultado;
	}
}