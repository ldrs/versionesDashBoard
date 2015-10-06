package rd.huma.dashboard.model.transaccional.dominio;

public enum EEstadoServidor {

	LIBRE{
		@Override
		public boolean disponibleVersion() {
			return true;
		}
	},
	OCUPADO,
	OCUPADO_DESPLIEGE_EN_PROCESO,
	NO_DISPONIBLE,
	NO_DISPONIBLE_ACTUALIZACION_DB,
	NO_DISPONIBLE_MANUAL;


	public boolean disponibleVersion(){
		return false;
	};
}