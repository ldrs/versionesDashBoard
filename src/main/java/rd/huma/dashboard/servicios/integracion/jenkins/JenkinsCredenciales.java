package rd.huma.dashboard.servicios.integracion.jenkins;

import java.util.Base64;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

public class JenkinsCredenciales {

	private EntConfiguracionGeneral configuracionGeneral;

	public JenkinsCredenciales(EntConfiguracionGeneral configuracionGeneral) {
		this.configuracionGeneral = configuracionGeneral;
	}

	public String getCredenciales(){
		return "Basic " + Base64.getEncoder().encodeToString( (configuracionGeneral.getUsrJenkins() + ":" + configuracionGeneral.getPwdJenkins()).getBytes() );
	}
}