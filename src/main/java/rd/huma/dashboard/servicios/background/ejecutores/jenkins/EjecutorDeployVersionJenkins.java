package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;

public class EjecutorDeployVersionJenkins extends AEjecutor {

	private EntServidor servidorDeploy;
	private EntVersion version;
	
	
	
	public EjecutorDeployVersionJenkins(EntServidor servidorDeploy,EntVersion version) {
		this.servidorDeploy = servidorDeploy;
		this.version = version;
	}



	@Override
	public void ejecutar() {
		
		
	}

}
