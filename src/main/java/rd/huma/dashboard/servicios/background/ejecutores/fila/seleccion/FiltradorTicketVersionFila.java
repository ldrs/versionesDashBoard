package rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class FiltradorTicketVersionFila {

	private EntVersion version;
	private List<EntFilaDespliegue> filas;

	public FiltradorTicketVersionFila(EntVersion version,List<EntFilaDespliegue> filas) {
		this.version = version;
		this.filas = filas;
	}

	public List<EntFilaDespliegue> filtra() {
		Set<String> estados = ServicioVersion.getInstanciaTransaccional().buscaTickets(version)
				.stream().map(EntVersionTicket::getTicketSysAid).map(EntTicketSysAid::getEstado).collect(Collectors.toSet());

		return filas.stream().filter(f ->  f.isPermiteSinTicketSysAid() ||  Arrays.asList(f.getEstadosSysAid().split(",")).stream().filter(s -> estados.contains(s)).findFirst().isPresent()).collect(Collectors.toList());
	}
}