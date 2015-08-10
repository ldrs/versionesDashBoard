package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;

@Path("filaConsulta")
public class WSFilaConsulta {
	@Servicio @Inject
	private ServicioFila servicioFila;


	@Path("todas")
	@GET
	public String consulta(){
		JsonArrayBuilder arreglo = Json.createArrayBuilder();
		servicioFila.getFilasDeploment().forEach(fila -> arreglo.add(filaToJson(fila)));
		return arreglo.build().toString();
	}

	private JsonObjectBuilder filaToJson(EntFilaDespliegue fila){
		JsonObjectBuilder retorno = Json.createObjectBuilder();
		retorno.add("id", fila.getId());
		retorno.add("nombreAmbiente", fila.getAmbiente().getAmbiente().getNombre());
		JsonArrayBuilder versiones = Json.createArrayBuilder();
		servicioFila.getFilas(fila).forEach(v ->  versiones.add(versiones.add(filaVersionToJson(v))));
		retorno.add("versiones", versiones);
		return retorno;
	}

	private JsonObjectBuilder filaVersionToJson(EntFilaDespliegueVersion v) {
		return  Json.createObjectBuilder()
				.add("id", v.getId())
				.add("prioridad", v.getPrioridad())
				.add("numeroVersion", v.getVersion().getNumero())
				.add("fechaRegistro", v.getFechaRegistro().toString())

				;
	}
}