package rd.huma.dashboard.servicios.web;

import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.transaccional.dominio.EMes;
import rd.huma.dashboard.servicios.reportes.DatoAgrupado;
import rd.huma.dashboard.servicios.reportes.ReporteAgrupado;
import rd.huma.dashboard.servicios.reportes.ReporteDespliegueVersiones;
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

		ReporteAgrupado agrupado = new ReporteDespliegueVersiones(servicioDespliegueVersion).getReporte();


		JsonObjectBuilder datosReporte = Json.createObjectBuilder();
		datosReporte.add("series", getSeries(agrupado));
		datosReporte.add("xAxis", xAxis(agrupado.getMesInicio(),agrupado.getMesFin()));
		datosReporte.add("yAxis", yAxis(agrupado.getMesInicio(),agrupado.getMesFin()));
		datosReporte.add("mesInicio", agrupado.getMesInicio());
		datosReporte.add("mesFin", agrupado.getMesFin());
		return datosReporte.build().toString();
	}

	private JsonArrayBuilder getSeries(ReporteAgrupado reporteAgrupado){
		JsonArrayBuilder retorno = Json.createArrayBuilder();

		for (Entry<String, Set<DatoAgrupado>> entry :  reporteAgrupado.getDatos().entrySet()){
			 retorno.add(Json.createObjectBuilder().add("name", entry.getKey())
			.add("lineWidth", 1)
			.add("data", to(reporteAgrupado.getMesInicio(), entry.getValue())));

		}

		return retorno;
	}

	private JsonArrayBuilder to(int primerMes, Set<DatoAgrupado> dato){
		JsonArrayBuilder builder = Json.createArrayBuilder();
		boolean primeraVes = true;
		for (DatoAgrupado datoAgrupado : dato) {
			if (primeraVes){
				primeraVes = false;
				for (int i=primerMes;i<datoAgrupado.getMes();i++){
					builder.add(0);
				}
			}
			builder.add(datoAgrupado.getCantidad());
		}

		return builder;

	}

	private JsonObjectBuilder xAxis(int mesIni, int mesFin){
		JsonArrayBuilder meses = Json.createArrayBuilder();
		EMes[] todosMeses =  EMes.values();
		for (int i=mesIni;i<=mesFin;i++){
			meses.add(todosMeses[i-1].name());
		}

		return Json.createObjectBuilder().add("categories", meses);
	}

	private JsonObjectBuilder yAxis(int i, int j){
		return Json.createObjectBuilder().add("title", Json.createObjectBuilder().add("text", "Cantidad"));
	}


}