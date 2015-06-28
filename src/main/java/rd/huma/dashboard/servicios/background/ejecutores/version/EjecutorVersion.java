package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.Optional;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.servicios.background.Ejecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.util.IntentoBuscadorCandado;

public class EjecutorVersion  extends Ejecutor{

	private EntVersion version;
	private EntConfiguracionGeneral configuracionGeneral;

	private String comentario;
	private int intentos;

	public EjecutorVersion(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		configuracionGeneral = buscaConfiguracion();
		comentario = new BuscadorComentario(version, configuracionGeneral).encuentraComentario();
		new BuscadorJiraEnComentario(comentario,version.)

	}
	
	private EntConfiguracionGeneral buscaConfiguracion(){
		return (EntConfiguracionGeneral) IntentoBuscadorCandado.of (ServicioConfiguracionGeneral::getCacheConfiguracionGeneral).get();
	}

	private EntAplicacion buscaAplicacion(){
		IntentoBuscadorCandado.of(s -> getAplicacion());
		
		return (EntAplicacion) IntentoBuscadorCandado.of (s -> getAplicacion()).get();
	}
	
	private Optional<EntAplicacion> getAplicacion(){
		return ServicioAplicacion.getCacheAplicacion(version.getSvnOrigen());
	}
	
	
}
