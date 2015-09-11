package rd.huma.dashboard.servicios.background.ejecutores.version;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;

class BuscadorComentario {

	private EntVersion version;
	private EntAplicacion aplicacion;

	public BuscadorComentario(EntVersion version,EntAplicacion aplicacion) {
		this.version = version;
		this.aplicacion = aplicacion;
	}

	public String  encuentraComentario(){
		return ServicioSVN.para(aplicacion).buscaComentario("/branches/" + version.getBranchOrigen(), version.getRevisionSVN());
	}
}