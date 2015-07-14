package rd.huma.dashboard.servicios.background.ejecutores.version;

import static rd.huma.dashboard.servicios.transaccional.ServicioAplicacion.getCacheAplicacion;
import static rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral.getCacheConfiguracionGeneral;
import static rd.huma.dashboard.servicios.transaccional.ServicioVersion.getInstanciaTransaccional;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.background.Ejecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

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

		ServicioVersion servicio = getInstanciaTransaccional();
		version =	servicio.actualizarVersion(
																	version.getId(),
																	new BuscadorComentario(version, configuracionGeneral).encuentraComentario()
																	);
		EntAplicacion aplicacion = getCacheAplicacion(version.getSvnOrigen()).orElseThrow(() -> new IllegalStateException("aplicacion  No esta"));

		procesadorTickets = ProcesadorTickets.of(configuracionGeneral, version, aplicacion).procesaJiras();

		new ProcesadorDatos(procesadorTickets).grabarDatos();

		new BuscadorPropiedades(configuracionGeneral, aplicacion, version).procesar();

		new BuscadorModulos(configuracionGeneral,aplicacion, version).procesar();

		servicio.actualizarEstado(EEstadoVersion.ESPERANDO_FILA,version);
	}
}