package rd.huma.dashboard.servicios.web;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

@Path("versionesInfo")
public class WSVersionesInfo {

	private @Servicio @Inject ServicioVersion servicioVersion;

	@GET
	public String info(){
		long versionesProcesando = servicioVersion.buscaVersionesRecientes(Arrays.stream(EEstadoVersion.values()).filter(EEstadoVersion::isProcesandoDato).collect(Collectors.toSet())).stream().count();


		long versionesConError = servicioVersion.versionesConError().count();

		return Json.createObjectBuilder()
										.add("procesandoDato", versionesProcesando)
										.add("conError", versionesConError)
										.build()
										.toString();

	}

}