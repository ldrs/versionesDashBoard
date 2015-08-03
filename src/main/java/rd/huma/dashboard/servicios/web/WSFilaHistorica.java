package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntHistoricoDespliegue;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioFilaHistorica;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

@Path("filaHistorica")
public class WSFilaHistorica {

	private @Servicio @Inject ServicioFilaHistorica servicioFilaHistorica;
	private @Servicio @Inject ServicioVersion servicioVersion;

	@GET
	@Path("versiones/{idAmbiente}")
	public String getVersiones(@PathParam("idAmbiente") String idAmbiente){
		JsonArrayBuilder arreglo = Json.createArrayBuilder();
		servicioFilaHistorica.getVersiones(idAmbiente).forEach(a -> arreglo.add(agregarVersion(a)));
		return arreglo.build().toString();
	}

	private JsonObjectBuilder agregarVersion(EntHistoricoDespliegue versionFilaHistorica){
		EntVersion version = versionFilaHistorica.getVersion();
		return Json.createObjectBuilder()

					.add("id", versionFilaHistorica.getId())
					.add("estado", versionFilaHistorica.getEstado().name())
					.add("fecha", versionFilaHistorica.getFechaRegistro().toString())
					.add("numero", version.getNumero())
					.add("branch", version.getBranchOrigen())
					.add("autor", version.getAutor())
					.add("fechaVersion", version.getMomentoCreacion().toString())
					.add("jiras", consultaJiras(version))
					.add("tickets", consultaTickets(version))
					.add("participantes", consultaParticipantes(version))
					;
	}

	private JsonArrayBuilder consultaJiras(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaJiras(version).stream().forEach(j -> agrega(builder, j));
		return builder;
	}

	private JsonArrayBuilder consultaTickets(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();

		servicioVersion.buscaTickets(version).stream().forEach(j -> agrega(builder, j));
		return builder;
	}

	private JsonArrayBuilder consultaParticipantes(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaParticipantes(version).stream().forEach(j -> agrega(builder, j));
		return builder;
	}

	private void agrega(JsonArrayBuilder builder, EntVersionJira jira){
		builder.add(jira.getJira().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionTicket jira){
		builder.add(jira.getTicketSysAid().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionParticipante jira){
		builder.add(jira.getParticipante().getNombreNullSafe());
	}
}