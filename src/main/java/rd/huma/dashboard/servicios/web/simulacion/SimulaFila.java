package rd.huma.dashboard.servicios.web.simulacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;

public class SimulaFila {
	
	private static Map<String, List<EntFilaDeployementVersion>> filas = new HashMap<>(); 
	private static List<EntFilaDeployementVersion> tmp = new ArrayList<>();
	static{
		filas.put(SimulacionAmbientes.getAmbientes().get(SimulacionAplicacion.getSigef().getId()).stream().findFirst().get().getId() , tmp);
		SimulaVersion.getVersiones().forEach(SimulaFila::nuevaFilaDeploymentVersion);
	}
	
	public static List<EntFilaDeployementVersion> filas(String id){
		return filas.getOrDefault(id, Collections.emptyList());
	}

	
	private static EntFilaDeployementVersion nuevaFilaDeploymentVersion(EntVersion version){
		EntAplicacion app = SimulacionAplicacion.getSigef();

		EntAmbiente ambiente = new EntAmbiente();
		ambiente.setAplicacion(app);
		ambiente.setJobJenkinsDeployements("http://localhost:8080");
		ambiente.setNombre("Desarrollo");
		ambiente.setOrden(1);

		EntFilaDeployement fila = new EntFilaDeployement();
		fila.setAmbiente(ambiente);
		
		LocalDateTime fecha = LocalDateTime.now();

		EntFilaDeployementVersion filaDeployementVersion = new EntFilaDeployementVersion();
		filaDeployementVersion.setPrioridad(filas.size()+1);
		filaDeployementVersion.setFecha(fecha);
		filaDeployementVersion.setVersion(version);
		filaDeployementVersion.setFila(fila);
		tmp.add(filaDeployementVersion);
		return filaDeployementVersion;
	}
}