package rd.huma.dashboard.servicios.integracion.svn.util;

import org.junit.Test;

import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.SimulaServicioSVN;

public class ServicioSvnBuscaOrigenBranchTest {

	@Test
	public void probar(){

		ServicioSVN servicioSVN = SimulaServicioSVN.servicioSvn();

		SVNOrigenBranch origen =servicioSVN.buscaOrigenBranchPorRevision("20150922.21006.00", 21080);
		for (String m : 	origen.getMergeBranches()) {
			System.out.println(m);
		}
	}

}