package rd.huma.dashboard.model.transaccional.dominio;

public enum ETipoReporteScript {

	AGRUPADO_POR_MES {
		@Override
		String getSubTipo() {
			return "Mes";
		}
	};


	abstract String getSubTipo();
}