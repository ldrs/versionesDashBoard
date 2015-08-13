package rd.huma.dashboard.servicios.inicializacion.datos;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import rd.huma.dashboard.servicios.background.watchers.aplicacion.ProcesadorWatcherScript;

public class InicializacionDeWatchersDirectorio {

	private static final Logger LOGGER = Logger.getLogger("Inicilizacion");

	public void ejecutar() {
		Path script = Paths.get("/logs/scripts");
		if (script.toFile().exists()){
			LOGGER.info("Inicializando Notificador de log de scripts");
			new ProcesadorWatcherScript(script).start();
		}else{
			LOGGER.warning("No existe la ruta de log (/logs/scripts) de script");
		}


		Path aplicacion = Paths.get("/logs/application");
		if (!aplicacion.toFile().exists()){
			LOGGER.warning("No existe la ruta de log (/logs/application) de aplicacion");
		}
	}
}