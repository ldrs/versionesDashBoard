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
	}
	;
	
	public boolean activo(){
		return true;
	}

}