package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntJira;
import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.jira.Jiras;

public class BuscadorJiraRestApi {

	private EntConfiguracionGeneral configuracionGeneral;
	private EntAplicacion aplicacion;
	private String branchOrigen;
	private List<Issues> issues;



	public BuscadorJiraRestApi(
			EntConfiguracionGeneral configuracionGeneral,
			EntAplicacion aplicacion, String branchOrigen) {
		this.configuracionGeneral = configuracionGeneral;
		this.aplicacion = aplicacion;
		this.branchOrigen = branchOrigen;
	}

	public List<EntJira> encuentra(){
		List<EntJira> jiraRetorno = new ArrayList<EntJira>();
		Jiras jiras = ClientBuilder	.newClient()
						.target(getUrlJira())
						.request(MediaType.APPLICATION_JSON)
						.get(Jiras.class)
						;
		this.issues = Arrays.asList(jiras.getIssues());
		issues.stream().forEach( j -> jiraRetorno.add(nuevoJira(j.getKey())));

		return jiraRetorno;
	}

	private EntJira nuevoJira(String numero){
		EntJira nuevoJira = new EntJira();
		nuevoJira.setNumero(numero);
		return nuevoJira;
	}

	public List<Issues> getIssues() {
		return issues;
	}

	private String getUrlJira(){
		return new StringBuilder(150)	.append(configuracionGeneral.getRutaJira())
								.append("rest/api/2/search?jql=")
								.append(aplicacion.getNombreCampoJiraLineaDesarrollo())
								.append("~")
								.append(branchOrigen).toString();
	}
}