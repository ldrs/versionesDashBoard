package rd.huma.dashboard.servicios.background.ejecutores.alertas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;

public class EjecutorEnvioAlertasCorreo extends AEjecutor {


	@Override
	public void ejecutar() {
		ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();
		ServicioEmail servicioEmail =  new ServicioEmail();
		List<EntVersion> versiones = servicioVersion.getVersionesQueContienenAlertas();
		try{

			for (EntVersion version : versiones){
				mandarCorreo(servicioVersion, servicioEmail, version);
			}
		}catch(Exception e){
			ServicioEmail.LOGGER.warning("No se pudo mandar el correo por "+ e.getMessage());
		}
	}

	private void mandarCorreo(ServicioVersion servicioVersion, ServicioEmail servicioEmail, EntVersion version){

		List<EntVersionAlerta> alertas = servicioVersion.buscaAlerta(version);
		List<String> archivos = new ArrayList<>();

		for (EntVersionAlerta entVersionAlerta : Collections.unmodifiableList(alertas)) {
			List<EntPersona> personas =  servicioVersion.buscaPersonasPorAlertar(entVersionAlerta);
			if (personas.isEmpty()){
				servicioVersion.moverHistorico(entVersionAlerta);
				alertas.remove(entVersionAlerta);
				continue;
			}

			if (entVersionAlerta.getPathFile()!=null){
				archivos.add(entVersionAlerta.getPathFile());
			}
			StringBuilder sbMensaje = new StringBuilder().append("\n")
			.append(entVersionAlerta.getMensaje())
			.append("\n")
			.append("Tipo de Notificacion :").append(entVersionAlerta.getAlerta().name()).append(" ").append(UtilFecha.getFechaFormateada(entVersionAlerta.getFechaRegistro()));


			if (servicioEmail.enviar(getCorreos(personas), "Notificaciones de la version :" + version.getNumero(), sbMensaje.toString(), archivos)){
				servicioVersion.moverHistorico(entVersionAlerta);
			}
		}
	}


	private String getCorreos(List<EntPersona> personas){
		StringBuilder correos = new StringBuilder();
		personas.stream().map(EntPersona::getCorreo).distinct().forEachOrdered(e -> correos.append(e).append(',') );
		correos.deleteCharAt(correos.length()-1);
		return correos.toString();
	}
}