package rd.huma.dashboard.model.transaccional.dominio;

import rd.huma.dashboard.servicios.cache.ServicioCacheRepositorioDuenos;

public enum ETipoAdvertencia {

	DUENO_ESQUEMA{
		@Override
		public CausaAdvertencia causaAdvertencia(String datos) {
			StringBuilder causa = null;
			for (String dueno : ServicioCacheRepositorioDuenos.getCache()){
				if (datos.contains(dueno+'.')){
					if (causa == null){
						causa = new StringBuilder();
					}
					causa.append("Contiene el nombre de esquema ").append(dueno).append("\n");
				}
			}
			return new CausaAdvertencia(causa!=null).setCausa(causa);
		}

	},
	SOBREPASADO_LIMITE_LINEA {
		@Override
		public CausaAdvertencia causaAdvertencia(String datos) {
			StringBuilder causa = null;
			String[] lineas = datos.split("\n");
			for (int i=0;i<lineas.length;i++){
				if (lineas[i].length()>1000){
					if (causa == null){
						causa = new StringBuilder();
					}
					causa.append("En la linea ").append(i+1).append(" tiene ").append(lineas[i].length()).append(" y deberia tener menos de 1000 por linea\n");
				}
			}

			return new CausaAdvertencia(causa!=null).setCausa(causa);
		}
	}
	;

	public abstract CausaAdvertencia causaAdvertencia(String datos);
}