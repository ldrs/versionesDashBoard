package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createObjectBuilder;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;

@Path("versionBuscar")
public class WSVersionesBuscar {

	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@GET
	public String buscar(@QueryParam("s") String query){
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		 for (EntVersion version : servicioVersion.buscarVersionPorNumeroObranchOTicket(query)){
			 arrayBuilder.add(toJson(version));
		 }
		 return Json.createObjectBuilder().add("resultados", arrayBuilder).build().toString();
	}


	private JsonObjectBuilder toJson(EntVersion version){
		return		createObjectBuilder()
		.add("id", version.getId())
		.add("numero", version.getNumero())
		.add("branch", version.getBranchOrigen())
		.add("fecha", UtilFecha.getFechaFormateada(version.getFechaRegistro()))
		.add("tickets", consultaTickets(version))
		;
	}


	private String consultaTickets(EntVersion version){
		StringBuilder sb = new StringBuilder(150);
		servicioVersion.buscaTickets(version).stream().forEach(j -> sb.append(j.getTicketSysAid().getNumero()).append(' ') );
		return sb.toString();
	}
}