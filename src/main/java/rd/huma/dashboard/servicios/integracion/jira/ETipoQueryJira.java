package rd.huma.dashboard.servicios.integracion.jira;

import rd.huma.dashboard.model.EntConfiguracionGeneral;

public enum ETipoQueryJira {

	KEY{

		@Override
		String getURLInterno(StringBuilder url,	EntConfiguracionGeneral configuracionGeneral, String value) {
			return url.append(configuracionGeneral.getNombreCampoJiraLineaDesarrollo()).append('=').append(value).toString() ;
		}



	},
	BRANCH{

		@Override
		String getURLInterno(StringBuilder url,	EntConfiguracionGeneral configuracionGeneral, String value) {
			return url.append("key").append('=').append(value).toString();
		}



	};

	String getURL(EntConfiguracionGeneral configuracionGeneral,	String value){
		return getURLInterno(new StringBuilder(150)	.append(configuracionGeneral.getRutaJira()).append("rest/api/2/search?jql="), configuracionGeneral,	value);
	}

	abstract String getURLInterno(StringBuilder url,EntConfiguracionGeneral configuracionGeneral, String value);
}