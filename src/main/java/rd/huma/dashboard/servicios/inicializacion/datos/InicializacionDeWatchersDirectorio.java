package rd.huma.dashboard.servicios.inicializacion.datos;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.util.logging.Logger;

public class InicializacionDeWatchersDirectorio {

	private static final Logger LOGGER = Logger.getLogger("Inicilizacion");

	public void ejecutar() {
		Path script = Paths.get("/log/script");
		if (!script.toFile().exists()){
			LOGGER.warning("No existe la ruta de log (/log/script) de script");
		}
		WatchService watchService =  FileSystems.getDefault().newWatchService();
		script.register(watcher, events)


		Path aplicacion = Paths.get("/log/aplicacion");
		if (!aplicacion.toFile().exists()){
			LOGGER.warning("No existe la ruta de log (/log/aplicacion) de aplicacion");
		}

	}

}
