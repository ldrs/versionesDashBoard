package rd.huma.dashboard.model;

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