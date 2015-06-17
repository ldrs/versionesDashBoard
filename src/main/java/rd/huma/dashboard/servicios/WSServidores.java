package rd.huma.dashboard.servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/servidores")
public class WSServidores {

	
	@GET
	public String servidores(){
		return "{\"aplicaciones\":\"sigef,esigef\"}";
	}
}
