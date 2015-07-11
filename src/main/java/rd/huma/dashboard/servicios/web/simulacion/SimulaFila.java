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
		filaDeployementVersion.setPrioridad(tmp.size()+1);
		filaDeployementVersion.setFecha(fecha);
		filaDeployementVersion.setVersion(version);
		filaDeployementVersion.setFila(fila);
		tmp.add(filaDeployementVersion);
		return filaDeployementVersion;
	}


	public static void subePrioridad(String id) {
		tmp.stream().filter(f -> f.getId().equals(id)).findFirst().ifPresent(version -> findOther(version,-1));
	}


	public static void bajaPrioridad(String id) {
		tmp.stream().filter(f -> f.getId().equals(id)).findFirst().ifPresent(version -> findOther(version,1));
	}
	
	private static void findOther(EntFilaDeployementVersion version, int direccion){
		tmp.stream().filter(f -> f.getPrioridad()==version.getPrioridad()+direccion).findFirst().ifPresent(vother -> intercambiaPrioridad(version,vother));
	}
	
	private static void intercambiaPrioridad(EntFilaDeployementVersion v1, EntFilaDeployementVersion v2){
		int prioridadTmp = v1.getPrioridad();
		v1.setPrioridad(v2.getPrioridad());
		v2.setPrioridad(prioridadTmp);
	}


	public static void elimina(String id) {
		tmp.stream().filter(f -> f.getId().equals(id)).findFirst().ifPresent(version-> tmp.remove(version));
	}
}