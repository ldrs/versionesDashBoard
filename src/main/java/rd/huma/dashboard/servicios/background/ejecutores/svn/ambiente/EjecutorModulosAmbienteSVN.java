package rd.huma.dashboard.servicios.background.ejecutores.svn.ambiente;

import rd.huma.dashboard.model.transaccional.EntAmbienteSVN;
import rd.huma.dashboard.servicios.background.Ejecutor;
import rd.huma.dashboard.servicios.integracion.svn.ambiente.ServicioAmbienteSvnBuscaModulos;
import rd.huma.dashboard.servicios.transaccional.ServicioModuloAmbienteSVN;

public class EjecutorModulosAmbienteSVN extends Ejecutor {

	private String ruta;
	private ServicioModuloAmbienteSVN servicio =  ServicioModuloAmbienteSVN.getInstanciaTransaccional();

	public EjecutorModulosAmbienteSVN() {
	}

	public EjecutorModulosAmbienteSVN(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public void ejecutar() {
		if (ruta == null){
			 servicio.getAmbientes().forEach(this::procesar);
		}else{
		    procesar(servicio.getAmbiente(ruta));
		}
	}

	private void procesar(EntAmbienteSVN ambienteSVN){
		new ServicioAmbienteSvnBuscaModulos(ambienteSVN).procesar().forEach(servicio::buscarCrear);
	}
}