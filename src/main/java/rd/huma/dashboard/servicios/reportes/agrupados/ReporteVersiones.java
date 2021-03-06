package rd.huma.dashboard.servicios.reportes.agrupados;

import java.util.List;

import rd.huma.dashboard.servicios.reportes.Reporte;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class ReporteVersiones extends Reporte {

	@Override
	protected List<Object[]> ejecutaBusquedaReporte() {
		return ServicioVersion.getInstanciaTransaccional().getVersionesPorMes();
	}
}