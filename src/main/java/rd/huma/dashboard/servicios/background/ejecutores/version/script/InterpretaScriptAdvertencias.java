package rd.huma.dashboard.servicios.background.ejecutores.version.script;

import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.EntVersionScriptAdvertencia;
import rd.huma.dashboard.model.transaccional.dominio.CausaAdvertencia;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAdvertencia;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilString;

public class InterpretaScriptAdvertencias {

	private ServicioVersion servicioVersion;
	private String datos;
	private EntVersionScript versionScript;


	InterpretaScriptAdvertencias(ServicioVersion servicioVersion, 	EntVersionScript versionScript,String datos) {
		this.servicioVersion = servicioVersion;
		this.versionScript = versionScript;
		this.datos = datos;
	}


	public void validar(){

		for (ETipoAdvertencia tipo : ETipoAdvertencia.values()){
			CausaAdvertencia causa = tipo.causaAdvertencia(datos);
			if (causa.isCausoProblema()){
				EntVersionScriptAdvertencia advertencia = new  EntVersionScriptAdvertencia();
				advertencia.setAdvertencia(tipo);
				advertencia.setVersionScript(versionScript);
				advertencia.setCausa(UtilString.defecto(causa.getCausa()));
				servicioVersion.crearAdvertencia(advertencia);
			}
		}
	}
}