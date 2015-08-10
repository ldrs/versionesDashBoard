package rd.huma.dashboard.servicios.background.ejecutores.fila.eliminacion;

import java.util.HashSet;
import java.util.Set;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EjecutorEliminadorFilas extends AEjecutor  {

	public void ejecutar() {

		ServicioFila servicio = ServicioFila.getInstanciaTransaccional();
		Set<EntFilaDespliegue> filasPorOrdenas = new HashSet<>();
		filasPorOrdenas.addAll(
							new EliminarVersionConEstadosInvalidos(servicio).ejecutar()
		);
		
		filasPorOrdenas.addAll(
					new EliminarVersionConBranchDuplicado(servicio).ejecutar()
				);
		
		filasPorOrdenas.forEach(servicio::ordenarFila);
		
	}

}