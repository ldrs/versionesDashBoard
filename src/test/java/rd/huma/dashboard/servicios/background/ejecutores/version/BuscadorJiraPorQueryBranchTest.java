package rd.huma.dashboard.servicios.background.ejecutores.version;

import org.junit.Ignore;
import org.junit.Test;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;

public class BuscadorJiraPorQueryBranchTest {

	@Test
	@Ignore
	public void probar(){
		EntConfiguracionGeneral config = new EntConfiguracionGeneral();
		config.setRutaJira("http://172.16.7.42:8080/");
		config.setUsrJira("pds");
		config.setPwdJira("pdsv229");

		EntAplicacion aplicacion = new EntAplicacion();
		aplicacion.setJiraKey("SGF");
		aplicacion.setNombre("sigef");
		aplicacion.setOrden(1);
		aplicacion.setSvnPath("sigef");

		new BuscadorJiraRestApi(config,aplicacion, "20150629.19491.00").encuentra();
	}
}
