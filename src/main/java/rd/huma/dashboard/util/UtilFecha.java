package rd.huma.dashboard.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class UtilFecha {

	private static final String FORMATO_FECHA = "dd/MM/yyyy HH:mm a";

	private UtilFecha() {
	}

	public static Instant getFechaJenkins(String fecha){
		 try {
			return new  SimpleDateFormat("YYYY-MM-DD_hh-mm-ss").parse(fecha).toInstant();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DateTimeParseException(e.getMessage(),"",1,e);
		}
	}


	public static String getFechaFormateada(TemporalAccessor fecha){
		DateTimeFormatter formatter =  DateTimeFormatter.ofPattern(FORMATO_FECHA).withZone(ZoneId.systemDefault());

		return formatter.format(fecha);
	}

	public static String getFechaFormateada(ChronoLocalDateTime<?> fecha){
		return fecha.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
	}
}