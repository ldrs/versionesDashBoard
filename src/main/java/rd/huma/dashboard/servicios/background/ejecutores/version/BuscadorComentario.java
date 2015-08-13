package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.util.IOUtil;

class BuscadorComentario {

	private EntVersion version;
	private EntConfiguracionGeneral configuracionGeneral;

	public BuscadorComentario(EntVersion version,EntConfiguracionGeneral configuracionGeneral) {
		this.version = version;
		this.configuracionGeneral = configuracionGeneral;
	}


	private String getRutaSvn(){
		return configuracionGeneral.getRutaSvn() + version.getSvnOrigen() + "/branches/" + version.getBranchOrigen();
	}


	public String  encuentraComentario(){
		try {
			Process proceso = Runtime.getRuntime().exec("svn log -r"+version.getRevisionSVN()+" "+getRutaSvn());
			proceso.waitFor(3, TimeUnit.SECONDS);

			return IOUtil.toString(proceso.getInputStream());
		} catch (IOException | InterruptedException e) {
			throw new IllegalStateException("No pudo ser encontrado el log.");
		}
	}

}