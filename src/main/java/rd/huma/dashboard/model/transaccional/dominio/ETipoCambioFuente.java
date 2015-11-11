package rd.huma.dashboard.model.transaccional.dominio;

public enum ETipoCambioFuente {

	ADICION {
		@Override
		public String getSVNTipo() {
			return "A";
		}
	},
	MODIFICACION {
		@Override
		public String getSVNTipo() {
			return "M";
		}
	},
	BORRADO {
		@Override
		public String getSVNTipo() {
			return "D";
		}
	}
	;

	public abstract String getSVNTipo();
}
