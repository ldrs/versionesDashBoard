package rd.huma.dashboard.servicios.background.ejecutores.fila.eliminacion;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import rd.huma.dashboard.model.fila.FilaBranch;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

public class EliminarVersionConBranchDuplicado {

	private static final Logger LOGGER = Logger.getLogger("EliminarVersionConBranchDuplicado");


	private ServicioFila servicio;

	public EliminarVersionConBranchDuplicado(ServicioFila servicio) {
		this.servicio = servicio;
	}

	public Set<EntFilaDespliegue> ejecutar(){
		Set<EntFilaDespliegue> fila = new HashSet<>();
		servicio.getFilasPorBranchDuplicado().stream().forEach( f-> fila.add(procesar(f)));
		return fila;
	}

	private EntFilaDespliegue procesar(FilaBranch fila){
		LOGGER.info("Haciendo el proceso de eliminar el branch " + fila.getBranch());

		 List<EntFilaDespliegueVersion> filas = servicio.getFilas(fila);
		 filas.sort( (a,b) -> a.getVersion().getRevisionSVN().compareTo(b.getVersion().getRevisionSVN()));
		 EntFilaDespliegueVersion activa =  filas.get(filas.size()-1);
		 filas.stream().filter(f -> !f.equals(activa)).forEach(f -> servicio.salirFila(f));
		 return fila.getFila();
	}
}