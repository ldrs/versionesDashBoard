package rd.huma.dashboard.servicios.background.ejecutores.version.script;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoScript;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorProcesaResultadoScripts extends AEjecutor {

	private Path rutaResultadoScripts;
	private ServicioVersion servicioVersion;
	private ServicioRepositorioDatos servicioRepositorioDatos;

	public EjecutorProcesaResultadoScripts(Path rutaResultadoScripts) {
		this.rutaResultadoScripts = rutaResultadoScripts;
	}

	@Override
	public void ejecutar() {
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
		this.servicioRepositorioDatos = ServicioRepositorioDatos.getInstanciaTransaccional();
		Path directorio = rutaResultadoScripts.getFileName();


		servicioVersion.buscaPorNumero(directorio.toString()).stream().findFirst().ifPresent(this::procesar);
	}

	private void procesar(EntVersion version) {
		List<EntRepositorioDatosScriptEjecutados> scripts =  servicioRepositorioDatos.getScriptEjecutadosPorVersion(version.getNumero());

		Map<String,EntRepositorioDatosScriptEjecutados> mapeo = getNombres(scripts);
		File[] archivos = rutaResultadoScripts.toFile().listFiles();
		for (File file : archivos) {
			String key =  file.getName().substring(0, file.getName().lastIndexOf('.'));
			EntRepositorioDatosScriptEjecutados script = mapeo.get(key);
			String resultado;
			try {
			 resultado = new String(Files.readAllBytes(file.toPath()));
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}

			EntVersionAlerta alerta = new EntVersionAlerta();
			alerta.setVersion(version);
			alerta.setPathFile(file.getPath());
			if (script == null){
				alerta.setAlerta(ETipoAlertaVersion.SCRIPT_NO_ENCONTRADO_EN_VERSION);
				alerta.setMensaje(new StringBuilder().append("El archivo ").append(file.getName()).append(" no aparenta ser parte de la version fue ejecutado").toString());
			}else{
				alerta.setAlerta(ETipoAlertaVersion.SCRIPT_RESULTADO);

				script.setResultado(resultado);
				script.setEstadoScript(resultado.contains("ORA-")? EEstadoScript.EJECUCION_FALLIDO :EEstadoScript.EJECUCION_FALLIDO);
				script.setFechaEjecucion(LocalDateTime.now());
				servicioRepositorioDatos.actualizarScript(script);
				mapeo.remove(key);
			}
			servicioVersion.crearAlerta(alerta);
		}
		mapeo.values().forEach(this::marcarComoResultadoDesconocido);

	}

	private void marcarComoResultadoDesconocido(EntRepositorioDatosScriptEjecutados repositorioDatosScriptEjecutados){
		repositorioDatosScriptEjecutados.setEstadoScript(EEstadoScript.RESULTADO_EJECUCION_DESCONOCIDO);
		repositorioDatosScriptEjecutados.setFechaEjecucion(LocalDateTime.now());
		servicioRepositorioDatos.actualizarScript(repositorioDatosScriptEjecutados);
	}

	private Map<String,EntRepositorioDatosScriptEjecutados>  getNombres(List<EntRepositorioDatosScriptEjecutados> scripts){
		Map<String, EntRepositorioDatosScriptEjecutados> mapeo = new HashMap<>();
		scripts.stream().forEach(e -> mapeo.put(nombreFromUrl(e.getScript().getUrlScript()), e));

		return mapeo;
	}

	private String nombreFromUrl(String valor){
		String tmp = valor.substring(valor.lastIndexOf('/'));
		return tmp.substring(0, tmp.lastIndexOf('.'));
	}
}