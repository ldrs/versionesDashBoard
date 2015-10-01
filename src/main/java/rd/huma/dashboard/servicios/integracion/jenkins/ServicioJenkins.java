package rd.huma.dashboard.servicios.integracion.jenkins;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;

public class ServicioJenkins {

	private EntAplicacion aplicacion;
	private EntConfiguracionGeneral configuracionGeneral;
	private String cacheJobScriptReporte;
	private String cacheJobScript;
	private String cacheJobVersion;

	private ServicioJenkins() {
	}

	public String getURLDeployJobReporte(){
		if (cacheJobScriptReporte == null){
			cacheJobScriptReporte = baseRoot().append(aplicacion.getNombreJobOracleReportJenkins()).append("/").toString();
		}
		return cacheJobScriptReporte;
	}

	public String getURLDeployJobScript(){
		if (cacheJobScript == null){
			cacheJobScript = baseRoot().append(aplicacion.getNombreJobSQLJenkins()).append("/").toString();
		}

		return cacheJobScript;
	}

	public String getURLDeployVersion(){
		if (cacheJobVersion == null){
			cacheJobVersion = baseRoot().append(aplicacion.getNombreJobDeployJenkins()).append("/").toString();
		}

		return cacheJobVersion;
	}

	private StringBuilder baseRoot(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaJenkins()).append("job/");
	}


	public static ServicioJenkins para(EntAplicacion aplicacion){
		ServicioJenkins servicioJenkins = new ServicioJenkins();
		servicioJenkins.aplicacion = aplicacion;
		servicioJenkins.configuracionGeneral = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();
		return servicioJenkins;
	}
}