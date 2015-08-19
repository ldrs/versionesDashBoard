package rd.huma.dashboard.util;

import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class UtilFecha {

	private static final String FORMATO_FECHA = "dd/MM/yyyy HH:mm a";

	private UtilFecha() {
	}


	public static String getFechaFormateada(TemporalAccessor fecha){
		DateTimeFormatter formatter =  DateTimeFormatter.ofPattern(FORMATO_FECHA).withZone(ZoneId.systemDefault());

		return formatter.format(fecha);
	}

	public static String getFechaFormateada(ChronoLocalDateTime<?> fecha){
		return fecha.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
	}
}