package rd.huma.dashboard.servicios.background.ejecutores.svn.branch;

import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntBranchMerge;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.EncuentraUltimaRevision;
import rd.huma.dashboard.servicios.integracion.svn.util.SVNOrigenBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;

public class ProcesadorOrigenNull {

	private static final Logger LOGGER =  Logger.getLogger(ProcesadorOrigenNull.class.getSimpleName());

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

			for (EntBranchMerge merge: servicioBranch.buscaDestinoBranch(branch)){
				long revision = new EncuentraUltimaRevision(servicio,branch.getBranch(),merge.getBranchOrigen().getBranch(),merge.getBranchOrigen().getRevisionUltima()).revision();
				LOGGER.info(String.format("Buscando origen del %s revicion encontrada %s buscando del merge %s de la revision %s ", branch.getBranch(),revision,merge.getBranchOrigen().getBranch(), merge.getBranchOrigen().getRevisionUltima()));
				if (revision!=0 && branch.getRevisionUltima()<revision){
					branch.setRevisionUltima(revision);
				}
			}

			if (branch.getRevisionUltima()>0){
				LOGGER.info(String.format("Proceso para grabar el origen del %s ", branch.getBranch()));

				SVNOrigenBranch origen = servicio.buscaOrigenBranchPorRevision(branch.getBranch(), branch.getRevisionUltima());
				branch.setOrigen(origen.getOrigenBranch());
				branch.setRevisionOrigen(origen.getRevision());
				servicioBranch.actualizar(branch);
			}
		}
	}
}