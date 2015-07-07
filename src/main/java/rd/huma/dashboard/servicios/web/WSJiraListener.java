package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.jira.EjecutorJiraCambio;

@Path("/jiraListener/{param}")
public class WSJiraListener {
	
	@Inject
	private MonitorEjecutor monitorEjecutor;

	@GET
	public void issue(@PathParam("param") String numero) {
		monitorEjecutor.ejecutarAsync(new EjecutorJiraCambio(numero));
	}
}