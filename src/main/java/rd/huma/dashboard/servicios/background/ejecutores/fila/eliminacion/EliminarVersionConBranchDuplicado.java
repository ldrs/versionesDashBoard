package rd.huma.dashboard.servicios.background.ejecutores.fila.eliminacion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.servicios.FilaBranch;
import rd.huma.dashboard.model.transaccional.EEstadoVersion;
import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EliminarVersionConBranchDuplicado {

	
	private ServicioFila servicio;
	
	public EliminarVersionConBranchDuplicado(ServicioFila servicio) {
		this.servicio = servicio;
	}
	
	public Set<EntFilaDeployement> ejecutar(){
		Set<EntFilaDeployement> fila = new HashSet<>();
		servicio.getFilasPorBranchDuplicado(getEstadosActivos()).stream().forEach( f-> fila.add(procesar(f)));
		return fila;
	}
	
	private EntFilaDeployement procesar(FilaBranch fila){
		 List<EntFilaDeployementVersion> filas = servicio.getFilas(fila);
		 EntFilaDeployementVersion activa =  filas.get(filas.size()-1);
		 filas.stream().filter(f -> !f.equals(activa)).forEach(f -> servicio.salirFila(f));
		 return fila.getFila();
	}
	
	private Set<EEstadoVersion> getEstadosActivos(){
		return Arrays.asList(EEstadoVersion.values()).stream().filter(EEstadoVersion::activo).collect(Collectors.toSet());
	}
}