package rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioTicketSysaid;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class FiltradorTicketVersionFila {

	private EntVersion version;
	private List<EntFilaDespliegue> filas;

	public FiltradorTicketVersionFila(EntVersion version,List<EntFilaDespliegue> filas) {
		this.version = version;
		this.filas = filas;
	}

	public List<EntFilaDespliegue> filtra() {
		ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();
		Set<EntTicketSysAid> ticketsActualizados = servicioVersion.actualizaEstadosSysaid(version);
		Optional<EntAmbiente> ambiente = ServicioTicketSysaid.getInstanciaTransaccional().getAmbientesTickets(ticketsActualizados).stream().findFirst();

		if (ambiente.isPresent()){
			EntAmbiente ambienteFila = ambiente.get();
			return filas.stream().filter(f -> f.getAmbiente().getAmbiente().equals(ambienteFila)).collect(Collectors.toList());

		}else{
			return Collections.emptyList();
		}
	}
}