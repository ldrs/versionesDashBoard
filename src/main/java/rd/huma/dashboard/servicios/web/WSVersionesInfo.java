package rd.huma.dashboard.servicios.web;

import java.time.Duration;
import java.time.Instant;
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
		long versionesProcesando = servicioVersion.buscaVersiones(Arrays.stream(EEstadoVersion.values()).filter(EEstadoVersion::isProcesandoDato).collect(Collectors.toSet())).stream().count();


		long versionesConError = servicioVersion.buscaVersiones(Arrays.stream(EEstadoVersion.values()).filter( e-> !e.activo()).collect(Collectors.toSet())).stream().filter(v -> v.getFechaRegistro().isAfter(Instant.now().minus(Duration.ofDays(3))) ) .count();

		return Json.createObjectBuilder()
										.add("procesandoDato", versionesProcesando)
										.add("conError", versionesConError)
										.build()
										.toString();

	}

}