package rd.huma.dashboard.servicios.reportes;

import java.util.Map;
import java.util.Set;

public class ReporteAgrupado {
	 private Map<String, Set<DatoAgrupado>> datos;
	 private int mesInicio;
	 private int mesFin;


	ReporteAgrupado(Map<String, Set<DatoAgrupado>> datos, int mesInicio,int mesFin) {
		this.datos = datos;
		this.mesInicio = mesInicio;
		this.mesFin = mesFin;
	}

	public Map<String, Set<DatoAgrupado>> getDatos() {
		return datos;
	}

	public int getMesInicio() {
		return mesInicio;
	}

	public int getMesFin() {
		return mesFin;
	}
}