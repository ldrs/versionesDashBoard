package rd.huma.dashboard.servicios.background.watchers.aplicacion;

import java.nio.file.Path;

import rd.huma.dashboard.servicios.background.ejecutores.version.script.EjecutorProcesaResultadoScripts;
import rd.huma.dashboard.servicios.background.watchers.ProcesadorWatcher;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class ProcesadorWatcherScript extends ProcesadorWatcher {

	public ProcesadorWatcherScript(Path ruta) {
		super(ruta);
	}

	@Override
	protected void procesarArchivoCreado(Path pathArchivoCreado) {
		super.procesarArchivoCreado(pathArchivoCreado);
		if (pathArchivoCreado.toFile().isDirectory()){
			ServicioVersion.getInstanciaTransaccional().ejecutarJob(new EjecutorProcesaResultadoScripts(pathArchivoCreado));
		}
	}
}