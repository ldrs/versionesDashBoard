package rd.huma.dashboard.servicios.reportes.agrupados;

import java.util.List;

import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.servicios.reportes.Reporte;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;

public class ReporteDespliegueVersiones extends Reporte {

	private ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	public ReporteDespliegueVersiones(ServicioJobDespliegueVersion servicioJobDespliegueVersion) {
		this.servicioJobDespliegueVersion = servicioJobDespliegueVersion;
	}

	@Override
	protected List<Object[]> ejecutaBusquedaReporte() {
		return servicioJobDespliegueVersion.buscaVersionesAgrupadasPorTipo(ETipoDespliegueJob.VERSION);
	}
}