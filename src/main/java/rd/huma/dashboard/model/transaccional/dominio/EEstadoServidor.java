package rd.huma.dashboard.model.transaccional.dominio;

public enum EEstadoServidor {

	LIBRE{
		@Override
		public boolean disponibleVersion() {
			return true;
		}
	},
	OCUPADO,
	NO_DISPONIBLE;


	public boolean disponibleVersion(){
		return false;
	};
}