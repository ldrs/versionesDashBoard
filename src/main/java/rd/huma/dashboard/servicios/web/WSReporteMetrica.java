package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;


@Path("metrica")
public class WSReporteMetrica {
	
	private @Servicio @Inject ServicioVersion servicioVersion;

	@Path("aplicacion")
	@GET
	public String aplicacion( String id){
		
		JsonObjectBuilder datosReporte = Json.createObjectBuilder();
		datosReporte.add("titulo", "Metricas");
		datosReporte.add("subtitulo", "Deploy por mes");
		datosReporte.add("series", getSeries());
		datosReporte.add("xAxis", xAxis());
		datosReporte.add("yAxis", yAxis());
		return datosReporte.build().toString();
		
	
	
	}
	
	
	private JsonArrayBuilder getSeries(){

		JsonObjectBuilder deploys = Json.createObjectBuilder()
		.add("name", "deploys")
		.add("lineWidth", 1)
		.add("data", Json.createArrayBuilder().add(15).add(2).add(3).add(45).add(7));
		
		
		JsonObjectBuilder fallos = Json.createObjectBuilder()
				.add("name", "falloDeploys")
				.add("lineWidth", 1)
				.add("data", Json.createArrayBuilder().add(4).add(6).add(35).add(5).add(4).add(55));
		
		return Json.createArrayBuilder().add(deploys).add(fallos);
	}
	
	private JsonObjectBuilder xAxis(){
		return Json.createObjectBuilder().add("categories", Json.createArrayBuilder()
																	.add("Enero")
																	.add("Febrero")
																	.add("Marzo")
																	.add("Abril")
																	.add("Mayo")
																	.add("Junio")
																	.add("Julio")
																	.add("Agosto")
																	.add("Septiembre")
																	.add("Octubre")
																	.add("Noviembre")
																	.add("Diciembre")																	
																	
											);
	}
	
	private JsonObjectBuilder yAxis(){
		return Json.createObjectBuilder().add("title", Json.createObjectBuilder().add("text", "Cantidad"));
	}
	
}