package rd.huma.dashboard.servicios.integracion.nexus;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

public class SimulaNexus {


	public static ServicioNexus nuevo(){
		return ServicioNexus.nuevo(new EntConfiguracionGeneral());
	}
}
