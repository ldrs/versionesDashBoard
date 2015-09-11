package rd.huma.dashboard.servicios.integracion.nexus;

import org.junit.Test;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

public class ServicioNexusTest {

	@Test
	public void probar(){
		ServicioNexus.nuevo(new EntConfiguracionGeneral()).eliminarModulo("dr.gov.sigef", "admfinanciera", "10.0.1.0.0");
	}
}
