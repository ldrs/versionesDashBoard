package rd.huma.dashboard.servicios.web.simulacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatos;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoServidor;

public class SimulaServidores {

	private static Map<String, List<EntServidor>> servidores = new HashMap<>();
	
	static{
		servidores.put(SimulacionAmbientes.getDesarrolloSigef().getId() , getServidoresSimulado(SimulacionAmbientes.getDesarrolloSigef()));
	}
	
	public static List<EntServidor> getServidores(String id){
		return servidores.getOrDefault(id, Collections.emptyList());
	}

	private static List<EntServidor> getServidoresSimulado(EntAmbienteAplicacion entAmbiente) {
		List<EntServidor> lst = new ArrayList<>();
		lst.add(nuevoServidor("172.16.7.30:7777",entAmbiente,"MHDDEV02",true));
		lst.add(nuevoServidor("172.16.7.21:7777",entAmbiente,"MHDDEV03",true));
		lst.add(nuevoServidor("172.16.7.30:7101",entAmbiente,"MHDDEV01",false));
		return lst;
	}

	private static EntServidor nuevoServidor(String nombre, EntAmbienteAplicacion ambiente, String baseDatos,boolean conVersion){
		EntRepositorioDatos datos = new EntRepositorioDatos();
		datos.setSchema("SIGEF_PRD");
		datos.setServicio(baseDatos);
		
		EntServidor servidor = new EntServidor();
		servidor.setNombre(nombre);
		servidor.setAmbiente(ambiente);
		servidor.setBaseDatos(datos);
		servidor.setEstadoServidor(conVersion?EEstadoServidor.OCUPADO:EEstadoServidor.LIBRE);
		if (conVersion){
			servidor.setVersionActual(SimulaVersion.getVersionesServidores().stream().findFirst().get());
		}
		return servidor;
	}
	
}