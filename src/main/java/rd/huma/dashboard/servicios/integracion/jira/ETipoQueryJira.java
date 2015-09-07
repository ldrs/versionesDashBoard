package rd.huma.dashboard.servicios.integracion.jira;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

public enum ETipoQueryJira {

	KEY{

		@Override
		String getURLInterno(StringBuilder url,	EntConfiguracionGeneral configuracionGeneral, String value) {
			return url.append("key").append('=').append(value).toString();
		}



	},
	KEY_CAMBIOS{

		@Override
		String getURL(EntConfiguracionGeneral configuracionGeneral, String value) {
			return new StringBuilder(150)	.append(configuracionGeneral.getRutaJira()).append("rest/api/2/issue/").append(value).append("?expand=changelog").toString();
		}

	},
	BRANCH{

		@Override
		String getURLInterno(StringBuilder url,	EntConfiguracionGeneral configuracionGeneral, String value) {
			return url.append(configuracionGeneral.getNombreCampoJiraLineaDesarrollo()).append('~').append(value).toString() ;
		}



	};

	String getURL(EntConfiguracionGeneral configuracionGeneral,	String value){
		return getURLInterno(new StringBuilder(150)	.append(configuracionGeneral.getRutaJira()).append("rest/api/2/search?jql="), configuracionGeneral,	value);
	}

	String getURLInterno(StringBuilder url,EntConfiguracionGeneral configuracionGeneral, String value){
		return "";
	};
}