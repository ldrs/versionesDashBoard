package rd.huma.dashboard.servicios.inicializacion.datos;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.inilizacion.EjecutorInilizacionDatos;
import rd.huma.dashboard.servicios.integracion.sysaid.ServicioIntegracionSYSAID;

@WebListener
public class InicializadorAplicacion implements ServletContextListener {

	@Inject
	private MonitorEjecutor monitorEjecutor;


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		monitorEjecutor.ejecutarAsync(new EjecutorInilizacionDatos());
		new InicializacionDeWatchersDirectorio().ejecutar();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}