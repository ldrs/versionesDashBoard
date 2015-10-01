package rd.huma.dashboard.servicios.background.ejecutores.svn;

import java.util.Optional;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.SVNOrigenBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;

public class EjecutorRevisaOrigenBranch extends AEjecutor {

	private static final String PUNTO_ORIGEN_BRANCH = "branches/";
	private static final String PUNTO_ORIGEN_TAG = "tag/";

	private SVNOrigenBranch origen;
	private EntAplicacion aplicacion;


	public EjecutorRevisaOrigenBranch(SVNOrigenBranch origen,EntAplicacion aplicacion) {
		this.origen = origen;
		this.aplicacion = aplicacion;
	}

	@Override
	public void ejecutar() {
		String branchOrigen = origen.getOrigenBranch();
		int index = branchOrigen.indexOf(PUNTO_ORIGEN_BRANCH);
		String branch;
		if (index==-1){
			index = branchOrigen.indexOf(PUNTO_ORIGEN_TAG);
			if (index == -1){
				return;
			}
			branch = branchOrigen.substring(index+PUNTO_ORIGEN_TAG.length());
		}else{
			branch = branchOrigen.substring(index+PUNTO_ORIGEN_BRANCH.length());
		}
		ServicioBranch servicioBranch = ServicioBranch.getInstanciaTransaccional();

		Optional<EntBranch> posibleBranch = servicioBranch.buscaBranch(branch);
		if (posibleBranch.isPresent()){
			return;
		}else{
			SVNOrigenBranch pasadoOrigen = ServicioSVN.para(aplicacion).buscaOrigenBranchPorRevision(branch, origen.getRevision());
			EntBranch ebranch = new EntBranch();
			ebranch.setAplicacion(aplicacion);
			ebranch.setBranch(branch);
			ebranch.setOrigen(pasadoOrigen.getOrigenBranch());
			ebranch.setRevisionOrigen(pasadoOrigen.getRevision());
			servicioBranch.grabar(ebranch);
		}
	}
}