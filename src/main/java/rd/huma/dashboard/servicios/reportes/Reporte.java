package rd.huma.dashboard.servicios.reportes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

abstract class Reporte {


	public ReporteAgrupado getReporte(){
		Map<String, Set<DatoAgrupado>> datos = new HashMap<>();
		int mesInicio = 12;
		int mesFin = 0;
		for (Object[] convierte : ejecutaBusquedaReporte()){
			int mes =  Integer.valueOf(convierte[2].toString());
			if (mesInicio>mes){
				mesInicio = mes;
			}
			if (mes>mesFin){
				mesFin= mes;
			}
			DatoAgrupado datoAgrupado = new DatoAgrupado(Long.valueOf(convierte[0].toString()), convierte[1].toString(), mes);
			Set<DatoAgrupado> list =  datos.get(datoAgrupado.getSerie());
			if (list == null){
				list = new TreeSet<>();
				datos.put(datoAgrupado.getSerie(), list);
			}
			list.add(datoAgrupado);
		}
		return new ReporteAgrupado(datos, mesInicio, mesFin);
	}


	protected abstract List<Object[]> ejecutaBusquedaReporte();
}