package rd.huma.dashboard.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilFecha {

	private UtilFecha() {
	}

	public static String getFechaFormateada(LocalDateTime fecha){
		return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a"));
	}
}