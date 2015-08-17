package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.version.EjecutorVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

@Path("/versionNueva")
public class WSVersionesNueva {

	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@Inject
	private MonitorEjecutor monitorEjecutor;

	@GET
	public String versiones(@QueryParam("numero") String numeroVersion, @QueryParam("autor") String autor,@QueryParam("svnOrigen") String svnOrigen,@QueryParam("branch") String branchOrigen,@QueryParam("revision") String revisionSVN, @QueryParam("inicioJob") String inicioJob ){
		EntVersion version = servicioVersion.crearVersion(numeroVersion, autor , svnOrigen,branchOrigen, revisionSVN,inicioJob);
		if (version == null){
			return "Esta versión ya existe por tanto no se agrego a ninguna fila,  si aun la desea de nuevo navegue a <a href=\"/dashboard/restaurarVersion.html\"> restaurar la versión </a>";
		}
		monitorEjecutor.ejecutarAsync(new EjecutorVersion(version));
		return version.getId();
	}
}