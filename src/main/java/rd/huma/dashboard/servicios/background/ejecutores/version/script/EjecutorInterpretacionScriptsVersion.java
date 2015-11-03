package rd.huma.dashboard.servicios.background.ejecutores.version.script;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

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
			String datos = busca(versionScript.getUrlScript());
			if (datos.isEmpty()){
				return;
			}
			try {
				new InterpretaScriptAdvertencias(servicioVersion, versionScript, datos).validar();
			}catch(Exception e){

			}

			Map<ObjectoCambio, Integer> informacionScripts = new ServicioParseoObjectoQuerys(datos).buscar();

			for (Entry<ObjectoCambio, Integer> entry : informacionScripts.entrySet()) {
				ObjectoCambio objectoCambio = entry.getKey();
				EntVersionCambioObjectoSql cambioObjectoSql = new EntVersionCambioObjectoSql();
				cambioObjectoSql.setScript(versionScript);
				cambioObjectoSql.setCantidad(entry.getValue());
				cambioObjectoSql.setTipo(objectoCambio.getCambioTabla());
				cambioObjectoSql.setVersion(version);
				cambioObjectoSql.setColumnas(objectoCambio.getColumnastoString());
				cambioObjectoSql.setObjecto(entry.getKey().getNombre());
				servicioVersion.crearCambioObjectoSQL(cambioObjectoSql);
			}


		}catch(Exception e){

		}
	}


	private String busca(String url){

		Response resultado = ClientBuilder.newClient().target(url).request().buildGet().invoke();
		if (resultado.getStatus()==200){
			return resultado.readEntity(String.class);
		}
		return "";
	}

}