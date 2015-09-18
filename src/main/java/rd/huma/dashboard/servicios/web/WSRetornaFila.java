package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion.EjecutorSeleccionFila;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;


@Path("retornaFila/{version}")
public class WSRetornaFila {

	@Servicio @Inject
	private ServicioVersion servicioVersion;

	@Inject
	private MonitorEjecutor monitorEjecutor;

	@GET
	public String volverGestionarFila(@PathParam("version") String numeroVersion ){
		servicioVersion.buscaPorNumero(numeroVersion).stream().findFirst().ifPresent(
				version -> monitorEjecutor.ejecutarAsync(new EjecutorSeleccionFila(version))
				);
		;
		return "";
	}

}
