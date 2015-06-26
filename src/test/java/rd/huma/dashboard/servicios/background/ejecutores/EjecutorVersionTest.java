package rd.huma.dashboard.servicios.background.ejecutores;

import org.junit.Test;

import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntVersion;

public class EjecutorVersionTest {

	@Test
	public void probar(){
		EntVersion version = new EntVersion();
		version.setSvnOrigen("sigef");
		version.setBranchOrigen("19401.00-tk8323");
		version.setNumero("19401.00");
		version.setRevisionSVN("19401");

		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();
		configuracionGeneral.setRutaSvn("http://172.16.7.35:9880/svn/");

		EjecutorVersion ejecutorVersion = new EjecutorVersion(version, configuracionGeneral);
		ejecutorVersion.ejecutar();
	}
}