package rd.huma.dashboard.util;

public class Aplicacion {

	private static volatile boolean aplicacionUp = true;

	private Aplicacion() {
	}


	public static void bajarAplicacion() {
		Aplicacion.aplicacionUp = false;
	}

	public static boolean isAplicacionUp() {
		return aplicacionUp;
	}

}