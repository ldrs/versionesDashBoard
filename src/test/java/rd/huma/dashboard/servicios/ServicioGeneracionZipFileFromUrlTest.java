package rd.huma.dashboard.servicios;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import rd.huma.dashboard.servicios.utilitarios.ServicioGeneracionZipFileFromUrls;

public class ServicioGeneracionZipFileFromUrlTest {

	@Test @Ignore
	public void probar(){
		List<String> listas = Arrays.asList(new String[]{"http://172.16.7.35:9880/svn/sigef/trunk/scripts/gastos/SA9799-SGf2357-Gasto-ReporteDinamicoImputacionDelGasto.sql"});

		 ServicioGeneracionZipFileFromUrls s = new ServicioGeneracionZipFileFromUrls("reporte",listas);
		 File file = s.generar();
		 if (file.exists()){
			 System.out.println("funciona + " +file.getPath());
		 }

	}
}
