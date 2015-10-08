package rd.huma.dashboard.servicios.background.ejecutores.alertas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

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
			e.printStackTrace();
			ServicioEmail.LOGGER.warning("No se pudo mandar el correo por "+ e.getMessage());
		}
	}

	private void mandarCorreo(ServicioVersion servicioVersion, ServicioEmail servicioEmail, EntVersion version){

		Collection<Set<EntVersionAlerta>> agrupado = agrupaPorTipo(servicioVersion.buscaAlerta(version));

		for (Set<EntVersionAlerta> alertasAgrupado : agrupado ) {
			StringBuilder sbMensaje = new StringBuilder(150).append("\n");
			List<String> archivos = new ArrayList<>();
			Set<EntPersona> personas = new HashSet<>();
			Set<EntVersionAlerta> alertasSinModificar = new ConcurrentSkipListSet<>(alertasAgrupado);

			ETipoAlertaVersion tipo = null;
			for (EntVersionAlerta entVersionAlerta : alertasSinModificar) {
				tipo = entVersionAlerta.getAlerta();
				List<EntPersona> personasAlerta =  servicioVersion.buscaPersonasPorAlertar(entVersionAlerta);
				if (personasAlerta.isEmpty()){
					servicioVersion.moverHistorico(entVersionAlerta);
					alertasAgrupado.remove(entVersionAlerta);
					continue;
				}
				personas.addAll(personasAlerta);

				if (entVersionAlerta.getPathFile()!=null){
					archivos.add(entVersionAlerta.getPathFile());
				}

				sbMensaje.append("\n").append(entVersionAlerta.getMensaje()).append("\n");
			}
			if (tipo!=null){
				sbMensaje.append("Tipo de Notificacion :").append(tipo.name());
			}
			String mensaje = sbMensaje.toString();
			if (mensaje.length()>0 && servicioEmail.enviar(getCorreos(personas), "Notificaciones de la version :" + version.getNumero(), mensaje, archivos)){
				for (EntVersionAlerta entVersionAlerta : alertasAgrupado ){
					servicioVersion.moverHistorico(entVersionAlerta);
				}
			}else if (mensaje.length()==0){
				for (EntVersionAlerta entVersionAlerta : alertasAgrupado ){
					servicioVersion.moverHistorico(entVersionAlerta);
				}
			}
		}
	}

	private Collection<Set<EntVersionAlerta>> agrupaPorTipo(List<EntVersionAlerta> alertas){
		Map<String, Set<EntVersionAlerta>> agrupado = new HashMap<>();
		for (EntVersionAlerta entVersionAlerta : alertas) {
			String key = entVersionAlerta.getAlerta().name()+entVersionAlerta.getVersion().getNumero();
			Set<EntVersionAlerta> agrupados = agrupado.get(key);
			if (agrupados == null){
				agrupados = new ConcurrentSkipListSet<>();
				agrupado.put(key, agrupados);
			}
			agrupados.add(entVersionAlerta);
		}
		return agrupado.values();
	}


	private String getCorreos(Collection<EntPersona> personas){
		StringBuilder correos = new StringBuilder();
		personas.stream().map(EntPersona::getCorreo).distinct().forEachOrdered(e -> correos.append(e).append(',') );
		if (correos.length()>0){
			correos.deleteCharAt(correos.length()-1);
		}
		return correos.toString();
	}
}