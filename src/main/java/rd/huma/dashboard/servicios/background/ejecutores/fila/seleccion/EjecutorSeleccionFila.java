package rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion;

import java.util.List;
import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EjecutorSeleccionFila extends AEjecutor {
	private static  final Logger LOGGER = Logger.getLogger(EjecutorSeleccionFila.class.getSimpleName());

	private EntVersion version;

	public EjecutorSeleccionFila(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		LOGGER.info(String.format("Procesando la fila de la version %s", version.getNumero()));

		ServicioFila servicio = ServicioFila.getInstanciaTransaccional();
		List<EntFilaDeployement> filasPorFiltrar = new FiltradorJiraVersionFila(version, servicio.getFilasDeploment()).filtra();

		filasPorFiltrar = new  FiltradorTicketVersionFila(version, filasPorFiltrar).filtra();

		if (filasPorFiltrar.isEmpty()){
			LOGGER.warning(String.format("No se ha encontrado una fila para la version %s", version.getNumero()));
		}

		filasPorFiltrar.stream().sorted().forEach(fila -> servicio.crearVersionFila(version, fila));

	}
}