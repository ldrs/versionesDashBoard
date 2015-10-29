package rd.huma.dashboard.servicios.reportes.agrupados;

import java.util.List;

import rd.huma.dashboard.servicios.reportes.Reporte;

public class ReporteAgrupadoGenerico extends Reporte {

	private List<Object[]> valor;


	public ReporteAgrupadoGenerico(List<Object[]> valor) {
		this.valor = valor;
	}


	@Override
	protected List<Object[]> ejecutaBusquedaReporte() {
		return valor ;
	}

}
