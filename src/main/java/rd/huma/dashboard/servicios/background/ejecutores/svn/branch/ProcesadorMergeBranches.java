package rd.huma.dashboard.servicios.background.ejecutores.svn.branch;

import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.SVNOrigenBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;

class ProcesadorMergeBranches {
	private ServicioBranch servicioBranch =  ServicioBranch.getInstanciaTransaccional();

	void procesar(){
		servicioBranch.buscaBranchesQueDebenTenerMerge().stream().forEach(this::procesaBranch);
	}


	private void procesaBranch(EntBranch branch){
		SVNOrigenBranch datosOrigen = ServicioSVN.para(branch.getAplicacion()).buscaOrigenBranchPorRevision(branch.getBranch(), branch.getRevisionUltima());
		for (String mergedBranch : datosOrigen.getMergeBranches()){
			servicioBranch.buscarOCrear(branch,mergedBranch);
			branch.setMerge(true);
			servicioBranch.actualizar(branch);
		}
	}
}