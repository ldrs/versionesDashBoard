package rd.huma.dashboard.servicios.background.ejecutores.version.script;

import java.util.Map;
import java.util.Map.Entry;

import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionCambioObjectoSql;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.ObjectoCambio;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.servicios.utilitarios.ServicioParseoObjectoQuerys;

public class EjecutorInterpretacionScriptsVersion extends AEjecutor {

	private EntVersion version;
	private ServicioVersion servicioVersion;

	public EjecutorInterpretacionScriptsVersion(EntVersion version){
		this.version = version;
	}

	@Override
	public void ejecutar() {
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
		servicioVersion.eliminarCambiosObjectoCambio(version);
		servicioVersion.buscaScript(version).forEach(this::intepreta);
	}

	private void intepreta(EntVersionScript versionScript){
		try {
			Map<ObjectoCambio, Integer> informacionScripts = new ServicioParseoObjectoQuerys(versionScript.getUrlScript()).buscar();

			for (Entry<ObjectoCambio, Integer> entry : informacionScripts.entrySet()) {
				ObjectoCambio objectoCambio = entry.getKey();
				EntVersionCambioObjectoSql cambioObjectoSql = new EntVersionCambioObjectoSql();
				cambioObjectoSql.setScript(versionScript);
				cambioObjectoSql.setCantidad(entry.getValue());
				cambioObjectoSql.setTipo(objectoCambio.getCambioTabla());
				cambioObjectoSql.setVersion(version);
				cambioObjectoSql.setColumnas(objectoCambio.getColumnastoString());
				servicioVersion.crearCambioObjectoSQL(cambioObjectoSql);
			}


		}catch(Exception e){

		}
	}

}