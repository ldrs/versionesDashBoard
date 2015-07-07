package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import rd.huma.dashboard.model.*;
import rd.huma.dashboard.model.transaccional.EEstadoDeployement;
import rd.huma.dashboard.model.transaccional.EntHistoricoDeployement;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;

@Path("/historicoDeployment")
public class WSHistoricosDeployment {
	@GET
	public String filas(@QueryParam("aplicacion") String aplicacion) {
		JsonArrayBuilder builder = createArrayBuilder();
		getHistoricos().stream().forEach(s -> builder
								.add(createObjectBuilder()
										.add("estado", EEstadoDeployement.ESPERA.toString())
										.add("fecha", LocalDateTime.now().toString())
										.add("servidor", "PRUEBA")
										.add("version", s.getVersion().getNumero())
									)
								);
		return builder.build().toString();
	}

	private List<EntHistoricoDeployement> getHistoricos() {
		List<EntHistoricoDeployement> lst = new ArrayList<>();
		lst.add(nuevoHistoricoDeployment());
		return lst;
	}

	private EntHistoricoDeployement nuevoHistoricoDeployment() {
		EntServidor servidor = new 	EntServidor();
		servidor.setNombre("PRUEBA");

		EntVersion version = new EntVersion();
		version.setNumero("50154");

		EntHistoricoDeployement fila = new EntHistoricoDeployement();
		fila.setEstado(EEstadoDeployement.ESPERA);
		fila.setFecha(LocalDateTime.now());
		fila.setServidor(servidor);
		fila.setVersion(version);

		return fila;
	}
}

