package rd.huma.dashboard.servicios.integracion.svn;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

public class SimulaServicioSVN {


	public static ServicioSVN servicioSvn(){
		ServicioSVN svn = new ServicioSVN();
		EntAplicacion aplicacion = new EntAplicacion();
		aplicacion.setSvnPath("sigef");
		aplicacion.setJiraKey("SGF");
		svn.configurar(new EntConfiguracionGeneral(), aplicacion);
		return svn;
	}
}