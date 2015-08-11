package rd.huma.dashboard.model.transaccional.dominio;

public enum EEstadoVersion {

	ESPERANDO_DATOS_INTEGRACION,
	DATOS_INTEGRADO,
	BRANCH_ELIMINADO{
		@Override
		public boolean activo() {
			return false;
		}
	},
	NEXUS_ELIMINADO{
		@Override
		public boolean activo() {
			return false;
		}
	},
	ESPERANDO_FILA,
	CANCELADA_POR_ERROR_DESPLIEGUE_JENKINS,
	CANCELADA_POR_ERROR_DESPLIEGUE,
	EJECUTO_EXITOSO_JENKINS,
	DESPLIEGE_EXITOSO
	;

	public boolean activo(){
		return true;
	}

}