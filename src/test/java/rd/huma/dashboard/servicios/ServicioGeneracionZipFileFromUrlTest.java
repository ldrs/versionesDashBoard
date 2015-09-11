package rd.huma.dashboard.servicios;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import rd.huma.dashboard.servicios.utilitarios.ServicioGeneracionZipFileFromUrls;

public class ServicioGeneracionZipFileFromUrlTest {

	@Test
	public void probar(){
		List<String> listas = Arrays.asList(new String[]{"http://172.16.7.35:9880/svn/sigef/trunk/reportes/sigef/contabilidad/cg_matriz_subcuenta_fondo_ing_pend.rdf"});

		 try (ServicioGeneracionZipFileFromUrls s = new ServicioGeneracionZipFileFromUrls("reporte",listas)){
			 if (s.generar().exists()){
				 System.out.println("funciona");
			 }
		 };
	}
}
