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
import rd.huma.dashboard.model.EntServidor;
import rd.huma.dashboard.model.EntVersion;

@Path("/filaDeploymentVersion")
public class WSEntFilaDeployementVersion {
	@GET
	public String aplicaciones(){
		JsonArrayBuilder builder = createArrayBuilder();
		getFilaDeploymentVersion().stream().forEach(s -> builder.add(createObjectBuilder()
																				.add("nombre", s.getNombre())
																				.add("css", "")
																				.add("id",s.getId())
																				.add("orden",s.getOrden())
														 )
										);

		return builder.build().toString();
	}

	private List<EntFilaDeployementVersion> getFilaDeploymentVersion(){
		List<EntAplicacion> lst = new ArrayList<>();
		lst.add(nuevaFilaDeploymentVersion("sigef"));
		lst.add(nuevaFilaDeploymentVersion("esigef"));
		return lst;
	}

	private EntFilaDeployementVersion nuevaFilaDeploymentVersion(String nombre){
		EntServidor servidor = new 	EntServidor();
		servidor.setNombre("PRUEBA");

		EntVersion version = new EntVersion();
		version.setNumero("50154");

		EntAplicacion app = new EntAplicacion();
		app.setJiraKey(jiraKey);
		app.setNombre(nombre);
		app.setNombreCampoJiraLineaDesarrollo(nombreCampoJiraLineaDesarrollo);
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


		return servidor;
	}
}
