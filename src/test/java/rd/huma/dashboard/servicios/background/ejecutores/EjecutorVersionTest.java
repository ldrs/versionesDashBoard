package rd.huma.dashboard.servicios.background.ejecutores;

import org.junit.Ignore;
import org.junit.Test;

import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.background.ejecutores.version.EjecutorVersion;

public class EjecutorVersionTest {

	@Test
	@Ignore
	public void probar(){
		EntVersion version = new EntVersion();
		version.setSvnOrigen("sigef");
		version.setBranchOrigen("19401.00-tk8323");
		version.setNumero("19401.00");
		version.setRevisionSVN("19401");

		EjecutorVersion ejecutorVersion = new EjecutorVersion(version);
		ejecutorVersion.ejecutar();
	}
}