package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArrayBuilder;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.EntAmbiente;
import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntFilaDeployement;
import rd.huma.dashboard.model.EntFilaDeployementVersion;
import rd.huma.dashboard.model.EntJira;
import rd.huma.dashboard.model.EntServidor;
import rd.huma.dashboard.model.EntVersion;

@Path("/filaDeploymentVersion")
public class WSEntFilaDeployementVersion {
	//Json a retornar:
	//{"prioridad":1, "numero":"10.19278.7","autor":"ronny_jimenez",
	//"dueno":"alejandra_perez", "branchOrigen":"19270.00", "fechaVersion":"17/06/2015 11:52",
	//"controles":"block", "tickets" : ["1050"], "jiras":["SGF-1550"]},

	@GET
	public String aplicaciones(){
		JsonArrayBuilder builder = createArrayBuilder();
		getFilaDeploymentVersion().stream().forEach(f -> builder.add(createObjectBuilder()
																				.add("prioridad", "")
																				.add("numero", f.getVersion().getNumero())
																				.add("autor", f.getVersion().getAutor())
																				.add("dueno", "")
																				.add("branchOrigen", f.getVersion().getBranchOrigen())
																				.add("fechaVersion", f.getFecha().toString())
																				.add("controles", "")
																				.add("tickets", "")
																				.add("jiras", f.getFila().getAmbiente().getAplicacion().getJiraKey())
																	)
													);
		return builder.build().toString();
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
		filaDeployementVersion.setFecha(fecha);
		filaDeployementVersion.setVersion(version);
		filaDeployementVersion.setFila(fila);

		return filaDeployementVersion;
	}
}
