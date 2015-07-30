package rd.huma.dashboard.servicios.background.ejecutores.fila.deploy;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;

public class EjecutorDeployVersionAutomatico extends AEjecutor{

	@Override
	public void ejecutar() {
		ServicioFila servicioFila = ServicioFila.getInstanciaTransaccional();
		ServicioServidor servicioServidor = ServicioServidor.getInstanciaTransaccional();

		List<EntFilaDeployement> filas = servicioFila.getFilasDeploment();
		filas.forEach( fila -> new ProcesaFilaDeployAutomatico(servicioFila, servicioServidor, fila).procesar());
	}
}