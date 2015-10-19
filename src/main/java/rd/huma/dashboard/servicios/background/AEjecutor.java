package rd.huma.dashboard.servicios.background;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AEjecutor implements Runnable {

	private static Logger LOG = Logger.getLogger("MONITORES");

	@Override
	public final void run() {
		try{
		//	LOG.info("Inicio de ejecucion del proceso " + getClass().getSimpleName());

			ejecutar();
		}catch(Exception e){
			e.printStackTrace();
			LOG.log(Level.SEVERE, e.getMessage());
		}
	}

	public abstract void ejecutar();

}