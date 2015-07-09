package rd.huma.dashboard.servicios.web.simulacion;

import java.util.ArrayList;
import java.util.List;

import rd.huma.dashboard.model.transaccional.EntAplicacion;

public class SimulacionAplicacion {

	private static EntAplicacion sigef;
	private static EntAplicacion esigef;
	private static List<EntAplicacion> aplicaciones = new ArrayList<>();
	static {
		sigef = new EntAplicacion();
		sigef.setJiraKey("SGF");
		sigef.setNombre("sigef");
		sigef.setOrden(1);
		sigef.setNombrePropiedadesPom("versionPDS, emsambladores.version");
		sigef.setSvnPath("sigef");
		
		esigef = new EntAplicacion();
		esigef.setJiraKey("ESG");
		esigef.setNombre("esigef");
		esigef.setOrden(2);
		esigef.setNombrePropiedadesPom("versionPDS, emsambladores.version");
		esigef.setSvnPath("esigef");
		aplicaciones.add(sigef);
		aplicaciones.add(esigef);
		
	}
	
	public static EntAplicacion getSigef() {
		return sigef;
	}
	
	public static EntAplicacion getEsigef() {
		return esigef;
	}
	public static List<EntAplicacion> getAplicaciones() {
		return aplicaciones;
	}	
}