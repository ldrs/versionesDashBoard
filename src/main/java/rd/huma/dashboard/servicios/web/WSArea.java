package rd.huma.dashboard.servicios.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.transaccional.dato.AreaPorTicket;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioArea;


@Path("area")
public class WSArea {

	private @Servicio @Inject ServicioArea servicioArea;


	@GET
	@Path("tickets")
	public String tickets(){
		JsonArrayBuilder arreglo = Json.createArrayBuilder();
		mapeaTicketsPorArea().forEach( (area,tickets) -> arreglo.add(toJsonArea(area,tickets)));

		return arreglo.build().toString();

	}

	private JsonObjectBuilder toJsonArea(String area, AreaAgrupado tickets){
		JsonArrayBuilder todos = Json.createArrayBuilder();
		tickets.getTickets().forEach( numero -> todos.add(Json.createObjectBuilder().add("numero", numero)));
		return Json.createObjectBuilder().add("nombre", area).add("orden",tickets.getOrden()) .add("tickets", todos);
	}

	private Map<String, AreaAgrupado> mapeaTicketsPorArea(){
		Map<String, AreaAgrupado> areaPorTickets = new HashMap<>();
		 for (AreaPorTicket areaTicket : servicioArea.ticketsPorArea()){
			 AreaAgrupado tickets =  areaPorTickets.get(areaTicket.getNombre());
			 if (tickets == null){
				 tickets = new AreaAgrupado();
				 tickets.setOrden(areaTicket.getOrden());
				 areaPorTickets.put(areaTicket.getNombre(), tickets);
			 }
			 tickets.getTickets().add(areaTicket.getNumero());
		 }
		 return areaPorTickets;
	}
}

class AreaAgrupado{
	private int orden;
	private List<Long> tickets = new ArrayList<>();

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public int getOrden() {
		return orden;
	}

	public List<Long> getTickets() {
		return tickets;
	}
}