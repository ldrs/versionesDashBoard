package rd.huma.dashboard.servicios.integracion.jira;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.jira.Histories;
import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.jira.Jiras;
import rd.huma.dashboard.model.transaccional.EntJira;

public class BuscadorJiraRestApi {

	private List<Issues> issues = Collections.emptyList();
	private JiraQuery jiraQuery;
	private boolean ejecuto;
	private Jiras jiras;

	public BuscadorJiraRestApi(	JiraQuery jiraQuery){
		this.jiraQuery = jiraQuery;
	}

	public List<EntJira> encuentra(){
		ejecuto = true;
		List<EntJira> jiraRetorno = new ArrayList<EntJira>();
		this.jiras = ClientBuilder	.newClient()
						.target(jiraQuery.getUrl())
						.request(MediaType.APPLICATION_JSON)
						.get(Jiras.class)
						;
		if (jiras.getIssues()!=null){
			this.issues = Arrays.asList(jiras.getIssues());
			issues.stream().forEach( j -> jiraRetorno.add(nuevoJira(j.getKey(), j.getFields())));
		}

		return jiraRetorno;
	}

	private EntJira nuevoJira(String numero, Fields camposJira){
		EntJira nuevoJira = new EntJira();
		nuevoJira.setNumero(numero);
		nuevoJira.setJiraEstado(CacheJiraEstado.at(camposJira));
		return nuevoJira;
	}

	public List<Issues> getIssues() {
		if (ejecuto == false){
			encuentra();
		}

		return issues;
	}

	public List<Histories> getHistories(){
		if (ejecuto == false){
			encuentra();
		}
		return Arrays.asList(jiras.getChangelog().getHistories());
	}
}