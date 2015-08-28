package rd.huma.dashboard.servicios.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;


@Path("metrica")
public class WSReporteMetrica {

	private @Servicio @Inject ServicioVersion servicioVersion;
	private @Servicio @Inject ServicioJobDespliegueVersion servicioDespliegueVersion;

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
		JsonArrayBuilder retorno = Json.createArrayBuilder();

		for (Entry<String, Set<DatoAgrupado>> entry :  buscaVersiones().entrySet()){
			 retorno.add(Json.createObjectBuilder().add("name", entry.getKey())
			.add("lineWidth", 1)
			.add("data", to(entry.getValue())));

		}
//
//		deploys = Json.createObjectBuilder()
//		.add("name", "deploys")
//		.add("lineWidth", 1)
//		.add("data", Json.createArrayBuilder().add(15).add(2).add(3).add(45).add(7));
//
//
//		JsonObjectBuilder fallos = Json.createObjectBuilder()
//				.add("name", "falloDeploys")
//				.add("lineWidth", 1)
//				.add("data", Json.createArrayBuilder().add(4).add(6).add(35).add(5).add(4).add(55));

		return retorno;// Json.createArrayBuilder().add(deploys).add(fallos);
	}

	private JsonArrayBuilder to(Set<DatoAgrupado> dato){
		JsonArrayBuilder builder = Json.createArrayBuilder();
		boolean primeraVes = true;
		for (DatoAgrupado datoAgrupado : dato) {
			if (primeraVes){
				primeraVes = false;
				for (int i=0;i<datoAgrupado.getMes();i++){
					builder.add(0);
				}
			}
			builder.add(datoAgrupado.getCantidad());
		}

		return builder;

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

	private Map<String, Set<DatoAgrupado>> buscaVersiones(){
		Map<String, Set<DatoAgrupado>> datos = new HashMap<>();
		for (Object[] convierte : servicioDespliegueVersion.buscaVersionesAgrupadasPorTipo(ETipoDespliegueJob.VERSION)){
			DatoAgrupado datoAgrupado = new DatoAgrupado(Long.valueOf(convierte[0].toString()), convierte[1].toString(), Integer.valueOf(convierte[2].toString()));
			Set<DatoAgrupado> list =  datos.get(datoAgrupado.getSerie());
			if (list == null){
				list = new TreeSet<>();
				datos.put(datoAgrupado.getSerie(), list);
			}
			list.add(datoAgrupado);
		}
		return datos;
	}
}

class DatoAgrupado implements Comparable<DatoAgrupado>{
	private long cantidad;
	private String serie;
	private int mes;

	DatoAgrupado(long cantidad, String serie, int mes) {
		this.cantidad = cantidad;
		this.serie = serie;
		this.mes = mes;
	}

	public long getCantidad() {
		return cantidad;
	}
	public String getSerie() {
		return serie;
	}

	public int getMes() {
		return mes;
	}

	@Override
	public int compareTo(DatoAgrupado o) {
		return Integer.compare(mes, o.mes);
	}


}