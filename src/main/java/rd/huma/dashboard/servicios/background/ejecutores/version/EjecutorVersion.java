package rd.huma.dashboard.servicios.background.ejecutores.version;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.servicios.background.Ejecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;

public class EjecutorVersion  extends Ejecutor{

	private EntVersion version;
	private EntConfiguracionGeneral configuracionGeneral;

	private String comentario;

	public EjecutorVersion(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		configuracionGeneral = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().orElseThrow(() -> new IllegalStateException("Configuracion General No esta"));
		comentario = new BuscadorComentario(version, configuracionGeneral).encuentraComentario();
		EntAplicacion aplicacion = ServicioAplicacion.getCacheAplicacion(version.getSvnOrigen()).orElseThrow(()-> new IllegalStateException("aplicacion  No esta"));
		new BuscadorJiraEnComentario(comentario,aplicacion.getJiraKey());

	}

	
	
	
}
