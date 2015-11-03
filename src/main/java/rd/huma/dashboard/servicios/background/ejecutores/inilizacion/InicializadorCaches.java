package rd.huma.dashboard.servicios.background.ejecutores.inilizacion;

import rd.huma.dashboard.model.transaccional.EntRepositorioDatos;
import rd.huma.dashboard.servicios.cache.ServicioCacheRepositorioDuenos;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;

public class InicializadorCaches {


	public void ejecutar(){
		cargaCacheDuenos();
	}

	private void cargaCacheDuenos(){
		ServicioRepositorioDatos.getInstanciaTransaccional().repositoriosActivos().stream().map(EntRepositorioDatos::getSchema).forEach(s -> ServicioCacheRepositorioDuenos.adicionar(s));
	}
}