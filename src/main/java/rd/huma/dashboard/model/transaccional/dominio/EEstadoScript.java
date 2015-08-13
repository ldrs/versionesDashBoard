package rd.huma.dashboard.model.transaccional.dominio;

public enum EEstadoScript {

	PENDIENTE_EJECUCION{

		@Override
		public boolean puedeEjecutarDeNuevo() {
			return false;
		}
	},
	EJECUCION_EXITOSA{

		@Override
		public boolean puedeEjecutarDeNuevo() {
			return false;
		}
	},
	EJECUCION_FALLIDO,
	EXITOSO_MARCADO_EJECUTA_NUEVO,
	FALLIDO_MARCADO_EJECUTA_NUEVO,
	RESULTADO_EJECUCION_DESCONOCIDO
	;

	public boolean puedeEjecutarDeNuevo(){
		return true;
	}
}