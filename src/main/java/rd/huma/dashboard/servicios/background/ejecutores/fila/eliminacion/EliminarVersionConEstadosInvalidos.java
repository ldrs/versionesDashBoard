package rd.huma.dashboard.servicios.background.ejecutores.fila.eliminacion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.EEstadoDeployement;
import rd.huma.dashboard.model.EEstadoVersion;
import rd.huma.dashboard.model.EntFilaDeployement;
import rd.huma.dashboard.model.EntFilaDeployementVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EliminarVersionConEstadosInvalidos {
	
	private ServicioFila servicio;
	
	public EliminarVersionConEstadosInvalidos(ServicioFila servicio) {
		this.servicio = servicio;
	}
	
	public Set<EntFilaDeployement> ejecutar(){
		Set<EntFilaDeployement> filas = new HashSet<>();
		servicio.getFilasPorEstadoVersion(getEstadosInactivos()).forEach(f ->  filas.add(procesar(servicio,f)));
		return filas;
	}
	
	

	private EntFilaDeployement procesar(ServicioFila servicio, EntFilaDeployementVersion filaVersion){
		servicio.salirFila(filaVersion, EEstadoDeployement.CANCELADO_VERSION_NO_DISPONIBLE);
		
		return filaVersion.getFila();
	}
	
	private Set<EEstadoVersion> getEstadosInactivos(){
		return Arrays.asList(EEstadoVersion.values()).stream().filter(e-> !e.activo()).collect(Collectors.toSet());
	}
}
