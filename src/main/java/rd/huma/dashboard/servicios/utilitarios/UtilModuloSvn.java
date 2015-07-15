package rd.huma.dashboard.servicios.utilitarios;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntAmbienteSVNModulo;
import rd.huma.dashboard.servicios.background.ejecutores.svn.ambiente.EjecutorModulosAmbienteSVN;
import rd.huma.dashboard.servicios.transaccional.ServicioModuloAmbienteSVN;

public class UtilModuloSvn {

	
	public static List<EntAmbienteSVNModulo> modulos(String ruta){
		List<EntAmbienteSVNModulo> modulos = ServicioModuloAmbienteSVN.getInstanciaTransaccional().getModulos(ruta);
		if (modulos.isEmpty()){
			 new EjecutorModulosAmbienteSVN(ruta).ejecutar();
			 modulos = ServicioModuloAmbienteSVN.getInstanciaTransaccional().getModulos(ruta);
		}
		return modulos;
	}
}