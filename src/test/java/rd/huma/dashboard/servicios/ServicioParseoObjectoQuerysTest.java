package rd.huma.dashboard.servicios;

import java.util.Map;

import rd.huma.dashboard.model.transaccional.dominio.ObjectoCambio;
import rd.huma.dashboard.servicios.utilitarios.ServicioParseoObjectoQuerys;

import org.junit.Test;

public class ServicioParseoObjectoQuerysTest {

	@Test
	public void probar(){
		Map<ObjectoCambio, Integer> resultado = new ServicioParseoObjectoQuerys("http://172.16.7.35:9880/svn/sigef/trunk/scripts/framework/FRAMEWORK-472.sql").buscar();
	}
}
