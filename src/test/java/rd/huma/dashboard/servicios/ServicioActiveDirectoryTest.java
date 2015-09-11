package rd.huma.dashboard.servicios;

import org.junit.Test;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.integracion.activedirectory.ServicioActiveDirectory;

public class ServicioActiveDirectoryTest {

	@Test
	public void probar(){
		ServicioActiveDirectory bla = new ServicioActiveDirectory(new EntConfiguracionGeneral(), "pedro.gonzalez", "01234567");
		if (bla.isValido()){
			System.out.println("aaa");
		}
	}

}
