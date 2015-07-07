package rd.huma.dashboard.model.transaccional;

public enum EEstadoVersion {

	ESPERANDO_DATOS_INTEGRACION,
	ESPERANDO_FILA,
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