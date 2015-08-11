package rd.huma.dashboard.servicios.background.ejecutores.version.script;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorProcesaResultadoScripts extends AEjecutor {

	private Path rutaResultadoScripts;
	private ServicioVersion servicioVersion;

	public EjecutorProcesaResultadoScripts(Path rutaResultadoScripts) {
		this.rutaResultadoScripts = rutaResultadoScripts;
	}

	@Override
	public void ejecutar() {
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
		Path directorio = rutaResultadoScripts.getFileName();


		servicioVersion.buscaPorNumero(directorio.toString())
				.stream().findFirst().ifPresent(this::procesar);


	}

	private void procesar(EntVersion version) {
		List<EntVersionScript> scripts = servicioVersion.getScript(version);
		Map<String,EntVersionScript> mapeo = getNombres(scripts);
		File[] archivos = rutaResultadoScripts.toFile().listFiles();
		for (File file : archivos) {
			EntVersionScript script = mapeo.get(file.getName());
			String resultado;
			try {
			 resultado = new String(Files.readAllBytes(file.toPath()));
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}

			if (script == null){
				EntVersionAlerta alerta = new EntVersionAlerta();
				alerta.setAlerta(ETipoAlertaVersion.SCRIPT_NO_ENCONTRADO_EN_VERSION);
				alerta.setMensaje(new StringBuilder().append("El archivo ").append(file.getName()).append(" no es parte de la version, y fue ejecutado.. el resultado fue \n").append(resultado).toString());
				alerta.setVersion(version);
				servicioVersion.crearAlerta(alerta);

			}else{
				script.setResultado(resultado);
				servicioVersion.actualizarVersionScript(script);
			}
		}

	}

	private Map<String,EntVersionScript>  getNombres(List<EntVersionScript> scripts){
		Map<String, EntVersionScript> mapeo = new HashMap<>();
		scripts.stream().forEach(e -> mapeo.put(e.getUrlScript().substring(e.getUrlScript().lastIndexOf('/')), e));

		return mapeo;
	}
}