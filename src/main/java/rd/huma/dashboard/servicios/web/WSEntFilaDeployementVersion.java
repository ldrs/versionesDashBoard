package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionDuenos;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedades;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

@Path("/filaDeploymentVersion")
public class WSEntFilaDeployementVersion {
	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@GET
	public String aplicaciones(){
		JsonArrayBuilder builder = createArrayBuilder();
		getFilaDeploymentVersion().stream().forEach(f -> builder.add(createObjectBuilder()
																				.add("prioridad", f.getPrioridad())
																				.add("numero", f.getVersion().getNumero())
																				.add("autor", f.getVersion().getAutor())
																				.add("dueno", consultaDuenos(f.getVersion()))
																				.add("branchOrigen", f.getVersion().getBranchOrigen())
																				.add("fechaVersion", f.getVersion().getMomentoCreacion().toString())
																				.add("controles", "block")
																				.add("tickets", consultaTickets(f.getVersion()))
																				.add("jiras", consultaJiras(f.getVersion())))
													);
		return builder.build().toString();
	}


	private JsonArrayBuilder consultaJiras(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaJiras(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private JsonArrayBuilder consultaTickets(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaTickets(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private JsonArrayBuilder consultaDuenos(EntVersion version){
		JsonArrayBuilder builder = createArrayBuilder();
		servicioVersion.buscaDuenos(version).stream().forEach(j -> {agrega(builder, j);});
		return builder;
	}

	private void agrega(JsonArrayBuilder builder, EntVersionJira jira){
		builder.add(jira.getJira().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionTicket jira){
		builder.add(jira.getTicketSysAid().getNumero());
	}

	private void agrega(JsonArrayBuilder builder, EntVersionPropiedades jira){
		builder.add(createObjectBuilder().add(jira.getPropiedad(), jira.getValor()));
	}

	private void agrega(JsonArrayBuilder builder, EntVersionDuenos jira){
		builder.add(jira.getDueno().getUsuarioSvn());
	}

	private List<EntFilaDeployementVersion> getFilaDeploymentVersion(){
		List<EntFilaDeployementVersion> lst = new ArrayList<>();
		lst.add(nuevaFilaDeploymentVersion());
		return lst;
	}

	private EntFilaDeployementVersion nuevaFilaDeploymentVersion(){
		EntServidor servidor = new 	EntServidor();
		servidor.setNombre("PRUEBA");

		EntVersion version = new EntVersion();
		version.setNumero("50154");

		EntAplicacion app = new EntAplicacion();
		app.setJiraKey("FWK");
		app.setNombre("SIGEF");
		app.setNombrePropiedadesPom("versionPDS, emsambladores.version");
		app.setOrden(98);
		app.setSvnPath("http://172.16.7.35:9880/svn/");

		EntAmbiente ambiente = new EntAmbiente();
		ambiente.setAplicacion(app);
		ambiente.setJobJenkinsDeployements("http://localhost:8080");
		ambiente.setNombre("PRUEBA");
		ambiente.setOrden(99);

		EntFilaDeployement fila = new EntFilaDeployement();
		fila.setAmbiente(ambiente);

		LocalDateTime fecha = LocalDateTime.now();

		EntFilaDeployementVersion filaDeployementVersion = new EntFilaDeployementVersion();
		filaDeployementVersion.setPrioridad(0);
		filaDeployementVersion.setFecha(fecha);
		filaDeployementVersion.setVersion(version);
		filaDeployementVersion.setFila(fila);

		return filaDeployementVersion;
	}
}
