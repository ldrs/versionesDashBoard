package rd.huma.dashboard.servicios.integracion.jira;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.jira.Jiras;
import rd.huma.dashboard.model.transaccional.EntJira;

public class BuscadorJiraRestApi {

	private List<Issues> issues = Collections.emptyList();
	private JiraQuery jiraQuery;
	private boolean ejecuto = false;

	public BuscadorJiraRestApi(	JiraQuery jiraQuery){
		this.jiraQuery = jiraQuery;
	}

	public List<EntJira> encuentra(){
		ejecuto = true;
		List<EntJira> jiraRetorno = new ArrayList<EntJira>();
		Jiras jiras = ClientBuilder	.newClient()
						.target(jiraQuery.getUrl())
						.request(MediaType.APPLICATION_JSON)
						.get(Jiras.class)
						;
		this.issues = Arrays.asList(jiras.getIssues());
		issues.stream().forEach( j -> jiraRetorno.add(nuevoJira(j.getKey(), j.getFields().getStatus().getName())));

		return jiraRetorno;
	}

	private EntJira nuevoJira(String numero, String estado){
		EntJira nuevoJira = new EntJira();
		nuevoJira.setNumero(numero);
		nuevoJira.setEstado(estado);
		return nuevoJira;
	}

	public List<Issues> getIssues() {
		if (ejecuto == false){
			encuentra();
		}

		return issues;
	}}