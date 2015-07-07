package rd.huma.dashboard.servicios.background.ejecutores.fila.eliminacion;

import java.util.HashSet;
import java.util.Set;

import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.servicios.background.Ejecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EjecutorEliminadorFilas extends Ejecutor  {

	public void ejecutar() {

		ServicioFila servicio = ServicioFila.getInstanciaTransaccional();
		Set<EntFilaDeployement> filasPorOrdenas = new HashSet<>();
		filasPorOrdenas.addAll(
							new EliminarVersionConEstadosInvalidos(servicio).ejecutar()
		);
		
		filasPorOrdenas.addAll(
					new EliminarVersionConBranchDuplicado(servicio).ejecutar()
				);
		
		filasPorOrdenas.forEach(servicio::ordenarFila);
		
	}

}