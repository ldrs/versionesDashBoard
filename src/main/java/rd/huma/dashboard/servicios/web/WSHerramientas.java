package rd.huma.dashboard.servicios.web;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Path;

import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.jira.Status;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.CacheJiraEstado;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioJira;
import rd.huma.dashboard.servicios.transaccional.ServicioTicketSysaid;

@Path("herramientas")
public class WSHerramientas {

	private static final Logger LOGGER =  Logger.getLogger(WSHerramientas.class.getSimpleName());

	@Servicio @Inject
	private ServicioTicketSysaid servicioTicketSysaid;

	@Servicio @Inject
	private ServicioJira servicioJira;

	@Servicio @Inject
	private ServicioConfiguracionGeneral servicioConfiguracionGeneral;


	@Path("recalcularTicketSysaid")
	public void calcularTicketSysaid(){
		servicioTicketSysaid.buscarTodos().forEach(servicioTicketSysaid::refrescarEstado);
	}

	@Path("recalcularJiraEstado")
	public void recalcularEstadoJira(){
		servicioJira.buscarTodos().stream().forEach(this::calcularJiraEstado);
	}

	private void calcularJiraEstado(EntJira jira){
		try{			List<Issues> issues = new BuscadorJiraRestApi(new JiraQuery(servicioConfiguracionGeneral.getConfiguracionGeneral().get(), ETipoQueryJira.KEY, jira.getNumero())).getIssues();
			issues.stream().findFirst().ifPresent(e -> computarCambioJiraEstado(e,jira));
		}catch(Exception e){
			LOGGER.warning(String.format("El jira %s buscando su estado en jira, dio el siguiente error %s", jira.getNumero(), e.getMessage()));
		}

	}

	private void computarCambioJiraEstado(Issues e, EntJira jira) {

		Status status = e.getFields().getStatus();
		jira.setJiraEstado(CacheJiraEstado.at(status.getId(), status.getName()));
		servicioJira.salva(jira);
	}
}