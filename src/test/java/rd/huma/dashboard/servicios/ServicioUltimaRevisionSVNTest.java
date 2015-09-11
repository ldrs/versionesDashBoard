package rd.huma.dashboard.servicios;

import rd.huma.dashboard.servicios.integracion.svn.util.ServicioUltimaRevisionSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.UltimaRevision;

import org.junit.Test;

public class ServicioUltimaRevisionSVNTest {

	public void probar(){
		UltimaRevision rev =  new  ServicioUltimaRevisionSVN("http://172.16.7.35:9880/svn/sigef/trunk/scripts/contabilidad/SGF-1624_Actualizar_Vista_CG_V_AC_TES_SUBCTA_FDO_CP-CG_V_AC_TES_SUBCTA_FDO_IP.sql").revision();
		System.out.println(rev.getNumeroRevision());
		System.out.println(rev.getUsuarioSVN());

	}
}
