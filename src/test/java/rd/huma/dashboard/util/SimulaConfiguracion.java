package rd.huma.dashboard.util;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

public class SimulaConfiguracion {

	public static EntConfiguracionGeneral simulaConfiguracion(){
		EntConfiguracionGeneral entConfiguracionGeneral = new EntConfiguracionGeneral();
		entConfiguracionGeneral.setRutaJira("http://172.16.7.42:8080/");
		return entConfiguracionGeneral;
	}

}
