package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;

@Path("/configuracionAplicacion")
public class WSConfiguracionAplicacion {

	@Inject
	private @Servicio ServicioAplicacion servicioAplicacion;

	@GET
	public String configurar(@QueryParam("nombre") String nombre,@QueryParam("jiraKey") String jiraKey,@QueryParam("svnPath")  String svnPath,@QueryParam("orden")  int orden, @QueryParam("nombrePropiedadesPom") String nombrePropiedadesPom){
		return servicioAplicacion.configurarCrearAplicacion(nombre, jiraKey, svnPath, orden, nombrePropiedadesPom).getId();
	}
}