package rd.huma.dashboard.servicios.web;

import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.dominio.EMes;
import rd.huma.dashboard.servicios.reportes.DatoAgrupado;
import rd.huma.dashboard.servicios.reportes.ReporteAgrupado;
import rd.huma.dashboard.servicios.reportes.agrupados.ReporteAgrupadoGenerico;
import rd.huma.dashboard.servicios.reportes.agrupados.ReporteDespliegueReportes;
import rd.huma.dashboard.servicios.reportes.agrupados.ReporteDespliegueScripts;
import rd.huma.dashboard.servicios.reportes.agrupados.ReporteDespliegueVersiones;
import rd.huma.dashboard.servicios.reportes.agrupados.ReporteVersiones;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioReporte;


@Path("metrica")
public class WSReporteMetrica {

	private @Servicio @Inject ServicioJobDespliegueVersion servicioDespliegueVersion;

	private @Servicio @Inject ServicioReporte servicioReporte;

	@Path("deployPorMes")
	@GET
	public String deployPorMes(){
		return toJson(new ReporteDespliegueVersiones(servicioDespliegueVersion).getReporte()).build().toString();
	}

	@Path("scriptPorMes")
	@GET
	public String scriptPorMes(){
		return toJson(new ReporteDespliegueScripts(servicioDespliegueVersion).getReporte()).build().toString();
	}

	@Path("reportePorMes")
	@GET
	public String reportePorMes(){
		return toJson(new ReporteDespliegueReportes(servicioDespliegueVersion).getReporte()).build().toString();
	}

	@Path("versionesPorMes")
	@GET
	public String versionesPorMes(){
		return toJson(new ReporteVersiones().getReporte()).build().toString();
	}

	@Path("reporteScript/{id}")
	@GET
	public String reporteScript(@PathParam("id") String id){
		return toJson(new ReporteAgrupadoGenerico(servicioReporte.getResultadoReporte(id)).getReporte()).build().toString();
	}

	@Path("reportesScript")
	@GET
	public String reportes(){
		JsonArrayBuilder arreglos = Json.createArrayBuilder();
		servicioReporte.getReportes().forEach( r -> arreglos.add(Json.createObjectBuilder().add("id", r.getId()).add("nombre", r.getNombre()) ));
		return arreglos.build().toString();
	}

	private JsonObjectBuilder toJson(ReporteAgrupado agrupado){
		return Json.createObjectBuilder()
		.add("series", getSeries(agrupado))
		.add("xAxis", xAxis(agrupado.getMesInicio(),agrupado.getMesFin()))
		.add("yAxis", yAxis(agrupado.getMesInicio(),agrupado.getMesFin()))
		.add("mesInicio", agrupado.getMesInicio())
		.add("mesFin", agrupado.getMesFin())
		;
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