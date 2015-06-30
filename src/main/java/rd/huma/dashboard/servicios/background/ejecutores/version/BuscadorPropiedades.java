package rd.huma.dashboard.servicios.background.ejecutores.version;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntVersion;

public class BuscadorPropiedades {

	private EntConfiguracionGeneral entConfiguracionGeneral;
	private EntAplicacion aplicacion;
	private EntVersion version;


	public BuscadorPropiedades(EntConfiguracionGeneral entConfiguracionGeneral,	EntAplicacion aplicacion, EntVersion version) {
		this.entConfiguracionGeneral = entConfiguracionGeneral;
		this.aplicacion = aplicacion;
		this.version = version;
	}

	public void procesar(){
		
	}

}