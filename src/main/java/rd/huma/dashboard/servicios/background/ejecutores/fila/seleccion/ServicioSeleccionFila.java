package rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class ServicioSeleccionFila {

	private EntVersion version;
	private ServicioFila servicio = ServicioFila.getInstanciaTransaccional();

	public ServicioSeleccionFila(EntVersion version) {
		this.version = version;
	}

	public List<EntFilaDespliegue> filasParaVersion() {
		return new  FiltradorTicketVersionFila(version, new FiltradorJiraVersionFila(version, servicio.getFilasDeploment()).filtra()).filtra();
	}
}