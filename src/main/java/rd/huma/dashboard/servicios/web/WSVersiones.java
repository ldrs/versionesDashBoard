package rd.huma.dashboard.servicios.web;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.model.EntVersion;

@Path("/nuevaVersion")
public class WSVersiones {



	@GET
	@Produces("text/plain")
	public String versiones(@QueryParam("numero") String numeroVersion, @QueryParam("autor") String autor,@QueryParam("branch") String branchOrigen,@QueryParam("revision") String revisionSVN){
		EntVersion version = new EntVersion();
		version.setNumero(numeroVersion);
		version.setAutor(autor);
		version.setBranchOrigen(branchOrigen);
		version.setRevisionSVN(revisionSVN);
		return version.getId();
	}
}