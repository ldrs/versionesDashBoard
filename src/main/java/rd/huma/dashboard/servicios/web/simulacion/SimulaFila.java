package rd.huma.dashboard.servicios.web.simulacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;

public class SimulaFila {
	
	private static List<EntFilaDeployementVersion> filas = new ArrayList<>();
	
	static{
		SimulaVersion.getVersiones().forEach(SimulaFila::nuevaFilaDeploymentVersion);
	}
	
	public static List<EntFilaDeployementVersion> filas(){
		return filas;
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
		filas.add(filaDeployementVersion);
		return filaDeployementVersion;
	}
}