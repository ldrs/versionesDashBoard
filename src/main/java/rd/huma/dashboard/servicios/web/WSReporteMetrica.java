package rd.huma.dashboard.servicios.web;

import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("metrica")
public class WSReporteMetrica {

	@Path("aplicacion")
	@GET
	public String aplicacion(){
		return Json.createObjectBuilder().add("deploys", Json.createArrayBuilder().add(15).add(2).add(3).add(45).add(7))
								  .add("falloDeploys", Json.createArrayBuilder().add(1).add(23).add(33).add(4).add(44))
								  .build().toString();
		
	}
	
}