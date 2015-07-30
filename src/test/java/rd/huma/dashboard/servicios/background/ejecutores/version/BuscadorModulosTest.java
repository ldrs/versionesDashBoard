package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;

public class BuscadorModulosTest {


	@Test
	public void probar(){
		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();
		EntAplicacion aplicacion = new EntAplicacion();
		aplicacion.setSvnPath("sigef");
		aplicacion.setRutaSvnAmbiente("http://172.16.7.35:9880/svn/ambientes/sigef/");
		EntVersion version = new EntVersion();
		version.setBranchOrigen("201505026.15222.01.00.Contabilidad");
		version.setSvnOrigen("sigef");
		List<EntVersionModulo> buscador = new BuscadorModulos(configuracionGeneral,aplicacion,version).buscar();

		Assert.assertFalse(buscador.isEmpty());
	}

}