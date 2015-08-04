package rd.huma.dashboard.servicios.background.ejecutores.version;

import static rd.huma.dashboard.servicios.transaccional.ServicioAplicacion.getCacheAplicacion;
import static rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral.getCacheConfiguracionGeneral;
import static rd.huma.dashboard.servicios.transaccional.ServicioVersion.getInstanciaTransaccional;

import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorVersion  extends AEjecutor{
	static  final Logger LOGGER = Logger.getLogger(EjecutorVersion.class.getSimpleName());

	private EntVersion version;
	private EntConfiguracionGeneral configuracionGeneral;
	private ProcesadorTickets procesadorTickets;

	public EjecutorVersion(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		LOGGER.info(String.format("Procesando la version %s del branch %s", version.getNumero(), version.getBranchOrigen()));

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

		new BuscadorModulos(configuracionGeneral,aplicacion, version).procesar();

		new ComparadorModulos(version).procesar();

		servicio.actualizarEstado(EEstadoVersion.DATOS_INTEGRADO,version);

		servicio.gestionarFila(version);
	}
}