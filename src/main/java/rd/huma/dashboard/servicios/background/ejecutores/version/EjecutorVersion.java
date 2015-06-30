package rd.huma.dashboard.servicios.background.ejecutores.version;

import static rd.huma.dashboard.servicios.transaccional.ServicioAplicacion.getCacheAplicacion;
import static rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral.getCacheConfiguracionGeneral;
import static rd.huma.dashboard.servicios.transaccional.ServicioVersion.getInstanciaTransaccional;
import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.servicios.background.Ejecutor;

public class EjecutorVersion  extends Ejecutor{

	private EntVersion version;
	private EntConfiguracionGeneral configuracionGeneral;
	private ProcesadorTickets procesadorTickets;

	public EjecutorVersion(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		configuracionGeneral = getCacheConfiguracionGeneral().orElseThrow(
																			() -> new IllegalStateException("Configuracion General No esta")
																		);

		version =	getInstanciaTransaccional().actualizarVersion(
																	version.getId(),
																	new BuscadorComentario(version, configuracionGeneral).encuentraComentario()
																	);
		EntAplicacion aplicacion = getCacheAplicacion(version.getSvnOrigen()).orElseThrow(() -> new IllegalStateException("aplicacion  No esta"));

		procesadorTickets = ProcesadorTickets.of(configuracionGeneral, version, aplicacion).procesaJiras();

		new ProcesadorDatos(procesadorTickets).grabarDatos();

		new BuscadorPropiedades(configuracionGeneral, aplicacion, version).procesar();

	}
}