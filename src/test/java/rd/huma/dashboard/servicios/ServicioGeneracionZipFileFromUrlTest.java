package rd.huma.dashboard.servicios;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import rd.huma.dashboard.servicios.utilitarios.ServicioGeneracionZipFileFromUrls;

public class ServicioGeneracionZipFileFromUrlTest {

	@Test
	public void probar(){
		List<String> listas = Arrays.asList(new String[]{"http://172.16.7.35:9880/svn/sigef/trunk/reportes/sigef/contabilidad/cg_balanza_de_comprobacion_corte_v2.rdf"});

		 ServicioGeneracionZipFileFromUrls s = new ServicioGeneracionZipFileFromUrls("reporte",listas);
		 File file = s.generar();
		 if (file.exists()){
			 System.out.println("funciona + " +file.getPath());
		 }

	}
}
