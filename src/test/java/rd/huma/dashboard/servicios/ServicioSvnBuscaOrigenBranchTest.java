package rd.huma.dashboard.servicios;

import org.junit.Ignore;
import org.junit.Test;

import rd.huma.dashboard.servicios.integracion.svn.util.SVNOrigenBranch;
import rd.huma.dashboard.servicios.integracion.svn.util.ServicioSvnBuscaOrigenBranch;

public class ServicioSvnBuscaOrigenBranchTest {

	@Test @Ignore
	public void bla(){
		SVNOrigenBranch s = new  ServicioSvnBuscaOrigenBranch("http://172.16.7.35:9880/svn/sigef/branches/20150812.16604.02.SGF-1807/", "SGF-","20150812.16604.02.SGF-1807").getOrigen();
		System.out.println(s.getOrigenBranch());
		System.out.println(s.getRevision());
	}

}
