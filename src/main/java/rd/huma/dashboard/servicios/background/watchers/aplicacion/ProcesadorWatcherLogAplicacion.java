package rd.huma.dashboard.servicios.background.watchers.aplicacion;

import java.nio.file.Path;

import rd.huma.dashboard.servicios.background.ejecutores.aplicacion.log.EjecutorAlertaLogDisponible;
import rd.huma.dashboard.servicios.background.watchers.ProcesadorWatcher;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class ProcesadorWatcherLogAplicacion extends ProcesadorWatcher{

	public ProcesadorWatcherLogAplicacion(Path ruta) {
		super(ruta);
	}

	@Override
	protected void procesarArchivoCreado(Path pathArchivoCreado) {
		super.procesarArchivoCreado(pathArchivoCreado);
		ServicioVersion.getInstanciaTransaccional().ejecutarJob(new EjecutorAlertaLogDisponible(pathArchivoCreado));
	}
}