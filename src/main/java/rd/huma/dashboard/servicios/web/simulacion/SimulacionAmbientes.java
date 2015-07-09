package rd.huma.dashboard.servicios.web.simulacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntAplicacion;

public class SimulacionAmbientes {

	private static final Map<String, List<EntAmbiente>> AMBIENTES = new HashMap<String, List<EntAmbiente>>();
	
	static{
		EntAplicacion sigef = SimulacionAplicacion.getSigef();
		
		AMBIENTES.put(sigef.getId(), getAmbienteSigef(sigef));
		EntAplicacion esigef = SimulacionAplicacion.getEsigef();
		AMBIENTES.put(esigef.getId(), getAmbienteESigef(esigef));
		
	}
	
	public static Map<String, List<EntAmbiente>> getAmbientes() {
		return AMBIENTES;
	}
	
	private static List<EntAmbiente> getAmbienteESigef(EntAplicacion esigef){
		List<EntAmbiente> ambientesSigef = new ArrayList<>();
		ambientesSigef.add(nuevoAmbiente(esigef,"desarrollo",1));
		ambientesSigef.add(nuevoAmbiente(esigef,"testing",2));
		ambientesSigef.add(nuevoAmbiente(esigef,"helpdesk",3));
		ambientesSigef.add(nuevoAmbiente(esigef,"produccion",4));
		return ambientesSigef;
	}
	
	private static List<EntAmbiente> getAmbienteSigef(EntAplicacion sigef){
		List<EntAmbiente> ambientesSigef = new ArrayList<>();
		ambientesSigef.add(nuevoAmbiente(sigef,"desarrollo",1));
		ambientesSigef.add(nuevoAmbiente(sigef,"testing",2));
		ambientesSigef.add(nuevoAmbiente(sigef,"preproduccion",3));
		ambientesSigef.add(nuevoAmbiente(sigef,"helpdesk",4));
		ambientesSigef.add(nuevoAmbiente(sigef,"produccion",5));
		return ambientesSigef;
	}
	

	private static EntAmbiente nuevoAmbiente(EntAplicacion aplicacion, String nombre, int orden){
		EntAmbiente ambiente = new EntAmbiente();
		ambiente.setAplicacion(aplicacion);
		ambiente.setNombre(nombre);
		ambiente.setOrden(orden);
		return ambiente;
	}
}