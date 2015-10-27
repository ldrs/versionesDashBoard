package rd.huma.dashboard.servicios;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Ignore;
import org.junit.Test;

import rd.huma.dashboard.model.transaccional.dominio.ObjectoCambio;
import rd.huma.dashboard.servicios.utilitarios.ServicioParseoObjectoQuerys;

public class ServicioParseoObjectoQuerysTest {

	@Test @Ignore
	public void probar(){
		Map<ObjectoCambio, Integer> resultado = new ServicioParseoObjectoQuerys("http://172.16.7.35:9880/svn/sigef/trunk/scripts/tesoreria/admPagos/comprobantesPago/Script_SGF-2443_MejoraFuncionalidaddeConfirmaciondeCheques").buscar();
		for (Entry<ObjectoCambio, Integer> entry : resultado.entrySet()){
			System.out.println(entry.getKey().getCambioTabla() + " "+ entry.getKey().getNombre() +"- >" + entry.getValue());
		}
	}
}
