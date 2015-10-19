package rd.huma.dashboard.servicios.background.ejecutores.svn.branch;

import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntBranchMerge;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.EncuentraUltimaRevision;
import rd.huma.dashboard.servicios.integracion.svn.util.SVNOrigenBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;

public class ProcesadorOrigenNull {

//	private static final Logger LOGGER =  Logger.getLogger(ProcesadorOrigenNull.class.getSimpleName());

	private ServicioBranch servicioBranch = ServicioBranch.getInstanciaTransaccional();

	public void procesar() {

		for (EntBranch branch: servicioBranch.buscaBranchesOrigenNull()){
			SVNOrigenBranch origen = ServicioSVN.para(branch.getAplicacion()).getOrigen(branch.getBranch());
			if (origen.getRevision()>0){
				branch.setRevisionOrigen(origen.getRevision());
				branch.setOrigen(origen.getOrigenBranch());
				if (origen.getUltimaRevision()!=0){
					branch.setRevisionUltima(origen.getUltimaRevision());
				}
				servicioBranch.actualizar(branch);
			}
		}
		//Segundo Algoritmo
		for (EntBranch branch: servicioBranch.buscaBranchesOrigenNull()){
			ServicioSVN servicio = ServicioSVN.para(branch.getAplicacion());

//			LOGGER.info(String.format("Buscando informacion del branch %s", branch.getBranch()));

			for (EntBranchMerge merge: servicioBranch.buscaOrigenBranch(branch)){
				String branchEncontrar = branch.getBranch();
				String branchDondeBuscar = merge.getBranchDestino().getBranch();
				long revisionDondeBuscar = merge.getBranchDestino().getRevisionUltima();

//				LOGGER.info(String.format("Buscando origen del branch(%s) buscando del merge %s de la revision %s ",branchEncontrar,branchDondeBuscar, revisionDondeBuscar));
				long revision = new EncuentraUltimaRevision(servicio,branchEncontrar,branchDondeBuscar,revisionDondeBuscar).revision();
//				LOGGER.info(String.format("Buscando origen del %s revicion encontrada %s buscando del merge %s de la revision %s ", branchEncontrar,revision,branchDondeBuscar, revisionDondeBuscar));
				if (revision!=0 && branch.getRevisionUltima()<revision){
					branch.setRevisionUltima(revision);
				}
			}

			if (branch.getRevisionUltima()>0){
	//			LOGGER.info(String.format("Proceso para grabar el origen del %s ", branch.getBranch()));

				SVNOrigenBranch origen = servicio.buscaOrigenBranchPorRevision(branch.getBranch(), branch.getRevisionUltima());
				branch.setOrigen(origen.getOrigenBranch());
				branch.setRevisionOrigen(origen.getRevision());
				servicioBranch.actualizar(branch);
			}
		}
	}
}