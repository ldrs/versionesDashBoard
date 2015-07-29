package rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EjecutorSeleccionFila extends AEjecutor {

	private EntVersion version;

	public EjecutorSeleccionFila(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		ServicioFila servicio = ServicioFila.getInstanciaTransaccional();
		List<EntFilaDeployement> filasPorFiltrar = new FiltradorJiraVersionFila(version, servicio.getFilasDeploment()).filtra();

		filasPorFiltrar = new  FiltradorTicketVersionFila(version, filasPorFiltrar).filtra();
		filasPorFiltrar.stream().sorted().forEach(fila -> servicio.crearVersionFila(version, fila));
		
	}
}