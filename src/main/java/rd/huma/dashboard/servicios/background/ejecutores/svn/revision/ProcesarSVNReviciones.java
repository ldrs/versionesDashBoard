package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntBranchRevision;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;
import rd.huma.dashboard.util.UtilFecha;

public class ProcesarSVNReviciones {

	private ServicioBranch servicioBranch = ServicioBranch.getInstanciaTransaccional();
	private ServicioPersona servicioPersona = ServicioPersona.getInstanciaTransaccional();

	public void ejecutar() {
		List<EntBranch> branchesPorRevisar = servicioBranch.buscaBranchAunFaltanRevision();
		branchesPorRevisar.stream()	.filter(branch -> branch.getRevisionOrigen()>0)
									.filter(branch -> branch.getRevisionUltima()>0)
									.forEach(this::branch);
	}

	private void branch(EntBranch branch){
		String comentario = ServicioSVN.para(branch.getAplicacion()).buscaRevisiones(branch.getBranch(),branch.getRevisionUltima());
		String[] revisiones = comentario.split(ServicioSVN.SEPERADOR_REVISION);
		for (String revision : revisiones) {
			parseaRevision(revision,branch);
		}
	}

	private void parseaRevision(String revision,EntBranch branch){
		EntBranchRevision eRevision=  new EntBranchRevision();
		eRevision.setBranch(branch);

		String[] lineas = revision.split("\n");
		for (int i = 0; i < lineas.length; i++) {
			String linea = lineas[i];
			if (i ==0){
				String[] partes = linea.split("|");
				eRevision.setRevision(Long.valueOf(partes[0].trim().substring(1)));
				eRevision.setPersona(servicioPersona.buscaOCreaPersona(partes[1].trim()));
				eRevision.setFechaRevision(UtilFecha.getFechaSVN(partes[2].trim()));
			}else{
				if (i>1){

				}
			}
		}
	}
}