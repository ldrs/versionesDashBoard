package rd.huma.dashboard.model.transaccional.dominio;

public enum EEstadoVersion {

	ESPERANDO_DATOS_INTEGRACION,
	DATOS_INTEGRADO(true,true),
	BRANCH_ELIMINADO(false,true),
	NEXUS_ELIMINADO(false,true),
	ESPERANDO_FILA(true,true),
	CANCELADA_POR_ERROR_DESPLIEGUE_JENKINS(false,true),
	CANCELADA_POR_ERROR_DESPLIEGUE(false,true),
	EJECUTO_EXITOSO_JENKINS(true,true),
	DESPLIEGE_EXITOSO(true,true)
	;

	private boolean activo = true;

	private boolean datoIntegrado = false;

	private EEstadoVersion() {
	}

	private EEstadoVersion(boolean activo, boolean integracionDato){
		this.activo = activo;
		this.datoIntegrado = integracionDato;
	}

	public boolean activo(){
		return activo;
	}

	public boolean isDatoIntegrado() {
		return datoIntegrado;
	}

	public boolean isProcesandoDato(){
		return activo && !datoIntegrado;
	}
}