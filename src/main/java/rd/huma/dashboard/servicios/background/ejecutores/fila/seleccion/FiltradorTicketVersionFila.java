package rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class FiltradorTicketVersionFila {

	private EntVersion version;
	private List<EntFilaDeployement> filas;

	public FiltradorTicketVersionFila(EntVersion version,List<EntFilaDeployement> filas) {
		this.version = version;
		this.filas = filas;
	}

	public List<EntFilaDeployement> filtra() {
		Set<String> estados = ServicioVersion.getInstanciaTransaccional().buscaTickets(version)
				.stream().map(EntVersionTicket::getTicketSysAid).map(EntTicketSysAid::getEstado).collect(Collectors.toSet());

		return filas.stream().filter(f ->  f.isPermiteSinTicketSysAid() ||  Arrays.asList(f.getEstadosSysAid().split(",")).stream().filter(s -> estados.contains(s)).findFirst().isPresent()).collect(Collectors.toList());
	}
}