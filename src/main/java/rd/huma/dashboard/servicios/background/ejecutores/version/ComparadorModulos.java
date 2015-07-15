package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntAmbienteSVNModulo;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.servicios.utilitarios.UtilModuloSvn;

public class ComparadorModulos {

	private EntVersion version;
	
	private EntVersionAlerta alerta;

	public ComparadorModulos(EntVersion version) {
		this.version = version;
	}
	
	public void procesar(){
		Set<Artefacto> ambientes = UtilModuloSvn.modulos(version.getRutaSvnAmbiente()).stream().map(EntAmbienteSVNModulo::getArtefacto) .collect(Collectors.toSet());
		ServicioVersion.getInstanciaTransaccional().buscaModulos(version)
		.stream().map(EntVersionModulo::getArtefacto).filter(m -> !ambientes.contains(m)).distinct()
		
		.forEach(this::crearAlerta);
		
		ServicioVersion.getInstanciaTransaccional().crearAlerta(alerta);
	}
	
	private void crearAlerta(Artefacto artefacto){
		if (alerta == null){
			alerta = new EntVersionAlerta();
			alerta.setAlerta(ETipoAlertaVersion.CONFIGURACION_POM_AMBIENTE_MODULOS_INCOMPATIBLES);
			alerta.setVersion(version);
		}
		String msg = alerta.getMensaje();
		String artToStr = artefacto.toString();
		alerta.setMensaje(msg == null ? " Falta el modulo " +artToStr : msg + " , " + artToStr);
		
	}
}