package rd.huma.dashboard.servicios.background.ejecutores.alertas;

import java.util.ArrayList;
import java.util.List;

import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorEnvioAlertasCorreo extends AEjecutor {


	@Override
	public void ejecutar() {
		ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();
		ServicioEmail servicioEmail =  ServicioEmail.getInstanciaTransaccional();
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
		StringBuilder sbMensaje = new StringBuilder(400 + 50*alertas.size());
		for (EntVersionAlerta entVersionAlerta : alertas) {
			if (entVersionAlerta.getPathFile()!=null){
				archivos.add(entVersionAlerta.getPathFile());
			}
			sbMensaje.append("En la fecha ").append(entVersionAlerta.getFechaRegistro()).append(" ocurrio la notificacion del tipo: ").append(entVersionAlerta.getAlerta().name())
			.append("\n")
			.append(entVersionAlerta.getMensaje())
			.append("\n");
		}

		servicioEmail.enviar(getCorreos(servicioVersion, version), "Notificaciones de la version :" + version.getNumero(), sbMensaje.toString(), archivos);
		servicioVersion.moverHistorico(alertas);
	}

	private String getCorreos(ServicioVersion servicioVersion, EntVersion version){
		StringBuilder correos = new StringBuilder();
		servicioVersion.buscaParticipantes(version).stream().map(EntVersionParticipante::getParticipante).map(EntPersona::getCorreo).distinct().forEachOrdered(e -> correos.append(e).append(',') );
		correos.deleteCharAt(correos.length()-1);
		return correos.toString();
	}
}