package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;


@Path("metrica")
public class WSReporteMetrica {

	private @Servicio @Inject ServicioVersion servicioVersion;

	@Path("aplicacion/{id}")
	@GET
	public String aplicacion(@PathParam("id") String id){



		JsonObjectBuilder arreglos = Json.createObjectBuilder();
		arreglos.add("nombre", "deploys");

		//Json.createArrayBuilder().add()


		return Json.createObjectBuilder().add("deploys", Json.createArrayBuilder().add(15).add(2).add(3).add(45).add(7))
								  .add("falloDeploys", Json.createArrayBuilder().add(1).add(23).add(33).add(4).add(44))
								  .build().toString();

	}

}