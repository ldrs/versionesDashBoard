package rd.huma.dashboard.servicios.background.ejecutores.fila.eliminacion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.fila.FilaBranch;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EliminarVersionConBranchDuplicado {

	
	private ServicioFila servicio;
	
	public EliminarVersionConBranchDuplicado(ServicioFila servicio) {
		this.servicio = servicio;
	}
	
	public Set<EntFilaDespliegue> ejecutar(){
		Set<EntFilaDespliegue> fila = new HashSet<>();
		servicio.getFilasPorBranchDuplicado(getEstadosActivos()).stream().forEach( f-> fila.add(procesar(f)));
		return fila;
	}
	
	private EntFilaDespliegue procesar(FilaBranch fila){
		 List<EntFilaDespliegueVersion> filas = servicio.getFilas(fila);
		 EntFilaDespliegueVersion activa =  filas.get(filas.size()-1);
		 filas.stream().filter(f -> !f.equals(activa)).forEach(f -> servicio.salirFila(f));
		 return fila.getFila();
	}
	
	private Set<EEstadoVersion> getEstadosActivos(){
		return Arrays.asList(EEstadoVersion.values()).stream().filter(EEstadoVersion::activo).collect(Collectors.toSet());
	}
}