package rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class FiltradorJiraVersionFila {

	private EntVersion version;
	private List<EntFilaDespliegue> filas;

	public FiltradorJiraVersionFila(EntVersion version,List<EntFilaDespliegue> filas) {
		this.version = version;
		this.filas = filas;
	}

	public List<EntFilaDespliegue> filtra() {
		Set<String> estados = ServicioVersion.getInstanciaTransaccional().buscaJiras(version)
				.stream().map(EntVersionJira::getJira).map(EntJira::getEstado).collect(Collectors.toSet());

		return filas.stream().filter(f ->  Arrays.asList(f.getEstadosJiras().split(",")).stream().filter(s -> estados.contains(s)).findFirst().isPresent()).collect(Collectors.toList());
	}
}