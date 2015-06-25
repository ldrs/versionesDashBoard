package rd.huma.dashboard.servicios.background;

public abstract class Ejecutor implements Runnable {

	@Override
	public final void run() {
		ejecutar();
	}
	
	public abstract void ejecutar();

}
