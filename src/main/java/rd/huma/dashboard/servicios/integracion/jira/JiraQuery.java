package rd.huma.dashboard.servicios.integracion.jira;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

public class JiraQuery {

	private ETipoQueryJira tipoQueryJira;
	private String value;
	private EntConfiguracionGeneral configuracionGeneral;

	public JiraQuery(EntConfiguracionGeneral configuracionGeneral, ETipoQueryJira tipoQueryJira, String value) {
		this.tipoQueryJira = tipoQueryJira;
		this.value = value;
		this.configuracionGeneral = configuracionGeneral;
	}

	String getUrl(){
		return tipoQueryJira.getURL(configuracionGeneral, value);
	}

}