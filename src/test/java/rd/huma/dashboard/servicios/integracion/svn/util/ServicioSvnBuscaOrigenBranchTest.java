package rd.huma.dashboard.servicios.integracion.svn.util;

import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.SimulaServicioSVN;

public class ServicioSvnBuscaOrigenBranchTest {

	//@Test
	public void probar(){

		/**

 origen del branch(20141215.15204.00.Tesoreria) buscando del merge 20150908.20648.00 de la revision 20687
 origen del       (20141215.15204.00.Tesoreria) revicion encontrada 0 buscando del merge 20150908.20648.00 de la revision 20687


		 */

		ServicioSVN servicioSVN = SimulaServicioSVN.servicioSvn();

		String branch = "20141215.15204.00.Tesoreria";
		String branchDonde = "20150908.20648.00";
		long revisionDonde = 20687;

		long revisionEncontre = new EncuentraUltimaRevision(servicioSVN, branch, branchDonde, revisionDonde).revision();

		 System.out.println(revisionEncontre);


		SVNOrigenBranch origen =servicioSVN.buscaOrigenBranchPorRevision(branch, revisionEncontre);
		System.out.println(origen.getOrigenBranch());
//		for (String m : 	origen.getMergeBranches()) {
//			System.out.println(m);
//		}
	}

}