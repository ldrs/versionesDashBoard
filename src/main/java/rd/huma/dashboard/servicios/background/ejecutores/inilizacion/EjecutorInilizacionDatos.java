package rd.huma.dashboard.servicios.background.ejecutores.inilizacion;

import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;

public class EjecutorInilizacionDatos extends AEjecutor {

	@Override
	public void ejecutar() {
		if (inicializar()){
			ServicioConfiguracionGeneral.getInstanciaTransaccional().nuevaConfiguracionDefecto();
			new InicializacionAmbienteAplicacion().inicializar();
		}
	}

	private boolean inicializar(){
		return !ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().isPresent();
	}

}
