package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

@Path("/nuevaVersion")
public class WSVersiones {

	@Inject
	private ServicioVersion servicioVersion;


	@GET
	@Produces("text/plain")
	public String versiones(@QueryParam("numero") String numeroVersion, @QueryParam("autor") String autor,@QueryParam("svnOrigen") String svnOrigen,@QueryParam("branch") String branchOrigen,@QueryParam("revision") String revisionSVN){
		return servicioVersion.crearVersion(numeroVersion, autor , svnOrigen,branchOrigen, revisionSVN).getId();
	}
}