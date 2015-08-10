package rd.huma.dashboard.servicios.background.ejecutores.fila.eliminacion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EliminarVersionConEstadosInvalidos {
	
	private ServicioFila servicio;
	
	public EliminarVersionConEstadosInvalidos(ServicioFila servicio) {
		this.servicio = servicio;
	}
	
	public Set<EntFilaDespliegue> ejecutar(){
		Set<EntFilaDespliegue> filas = new HashSet<>();
		servicio.getFilasPorEstadoVersion(getEstadosInactivos()).forEach(f ->  filas.add(procesar(servicio,f)));
		return filas;
	}
	
	

	private EntFilaDespliegue procesar(ServicioFila servicio, EntFilaDespliegueVersion filaVersion){
		servicio.salirFila(filaVersion);
		
		return filaVersion.getFila();
	}
	
	private Set<EEstadoVersion> getEstadosInactivos(){
		return Arrays.asList(EEstadoVersion.values()).stream().filter(e-> !e.activo()).collect(Collectors.toSet());
	}
}
