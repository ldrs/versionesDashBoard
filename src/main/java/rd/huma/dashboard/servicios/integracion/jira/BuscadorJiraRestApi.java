package rd.huma.dashboard.servicios.integracion.jira;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.EntJira;
import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.jira.Jiras;

public class BuscadorJiraRestApi {

	private List<Issues> issues = Collections.emptyList();
	private JiraQuery jiraQuery;

	public BuscadorJiraRestApi(	JiraQuery jiraQuery){
		this.jiraQuery = jiraQuery;
	}

	public List<EntJira> encuentra(){
		List<EntJira> jiraRetorno = new ArrayList<EntJira>();
		Jiras jiras = ClientBuilder	.newClient()
						.target(jiraQuery.getUrl())
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
}