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
import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoScript;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorProcesaResultadoScripts extends AEjecutor {

	private static final Logger LOGGER = Logger.getLogger(EjecutorProcesaResultadoScripts.class.getSimpleName());

	private Path rutaResultadoScripts;
	private ServicioVersion servicioVersion;
	private ServicioRepositorioDatos servicioRepositorioDatos;

	private ServicioJobDespliegueVersion servicioJob;

	public EjecutorProcesaResultadoScripts(Path rutaResultadoScripts) {
		this.rutaResultadoScripts = rutaResultadoScripts;
	}

	@Override
	public void ejecutar() {
		this.servicioJob = ServicioJobDespliegueVersion.getInstanciaTransaccional();
		this.servicioRepositorioDatos = ServicioRepositorioDatos.getInstanciaTransaccional();
		Path directorio = rutaResultadoScripts.getFileName();
		LOGGER.info(String.format("Procesando los logs de script del job(%s)", directorio.toString()));

		EntJobDespliegueVersion job = servicioJob.getJob(directorio.toString());
		if (job!=null){
			this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
			actualizaResultado(job);
		}
	}

	private void actualizaResultado(EntJobDespliegueVersion job){
		 List<EntRepositorioDatosScriptEjecutados> scripts = servicioRepositorioDatos.getScriptEjecutadosPorJob(job.getId());
		 procesar(job,job.getVersion(), scripts);
	}

	private void procesar(EntJobDespliegueVersion job, EntVersion version, List<EntRepositorioDatosScriptEjecutados> scripts) {
		Map<String,EntRepositorioDatosScriptEjecutados> mapeo = getNombres(scripts);
		File[] archivos = rutaResultadoScripts.toFile().listFiles();
		for (File file : archivos) {

			String key =  file.getName().substring(0, file.getName().indexOf('.'));
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
			alerta.setAmbiente(job.getFilaDespliegue().getAmbiente().getAmbiente());
			if (script == null){
				alerta.setAlerta(ETipoAlertaVersion.SCRIPT_NO_ENCONTRADO_EN_VERSION);
				alerta.setMensaje(new StringBuilder().append("El archivo ").append(file.getName()).append(" no aparenta ser parte de la version fue ejecutado").toString());
			}else{
				alerta.setAlerta(ETipoAlertaVersion.SCRIPT_RESULTADO);

				script.setResultado(resultado);
				script.setEstadoScript(resultado.contains("ORA-")? EEstadoScript.EJECUCION_FALLIDO :EEstadoScript.EJECUCION_EXITOSA);
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
		for (EntRepositorioDatosScriptEjecutados e : scripts) {
			mapeo.put( nombreFromUrl(e.getScript().getUrlScript()), e);
		}

		return mapeo;
	}

	private String nombreFromUrl(String valor){
		String tmp = valor.substring(valor.lastIndexOf('/')+1);
		return tmp.substring(0, tmp.indexOf('.'));
	}
}