package rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EjecutorSeleccionFila extends AEjecutor {
	private static  final Logger LOGGER = Logger.getLogger(EjecutorSeleccionFila.class.getSimpleName());

	private EntVersion version;

	private Set<EntFilaDespliegue> filasIgnorar;

	public EjecutorSeleccionFila(EntVersion version) {
		this.version = version;
	}

	public EjecutorSeleccionFila(EntVersion version, Set<EntFilaDespliegue> filasIgnorar) {
		this.version = version;
		this.filasIgnorar = filasIgnorar;
	}


	@Override
	public void ejecutar() {

		LOGGER.info(String.format("Procesando la fila de la version %s", version.getNumero()));


		List<EntFilaDespliegue> filas =  new ServicioSeleccionFila(version).filasParaVersion();
		if (filasIgnorar!=null){
			filas = filas.stream().filter(fila -> filasIgnorar.contains(fila)).collect(Collectors.toList());
		}
		if (filas.isEmpty()){
			LOGGER.warning(String.format("No se ha encontrado una fila para la version %s", version.getNumero()));
		}

		ServicioFila servicio = ServicioFila.getInstanciaTransaccional();
		filas.stream().sorted().forEach(fila -> servicio.crearVersionFila(version, fila));
	}
}