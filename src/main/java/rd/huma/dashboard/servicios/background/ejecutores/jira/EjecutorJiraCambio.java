package rd.huma.dashboard.servicios.background.ejecutores.jira;

import java.util.Optional;

import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.background.Ejecutor;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioJira;

public class EjecutorJiraCambio extends Ejecutor {
	
	private String numeroJira;

	public EjecutorJiraCambio(String numeroJira) {
		this.numeroJira = numeroJira;
	}



	@Override
	public void ejecutar() {
		EntConfiguracionGeneral configuracion = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();
		
		Optional<Issues> opcional = new BuscadorJiraRestApi(new JiraQuery(configuracion, ETipoQueryJira.KEY, numeroJira)).getIssues().stream().findFirst();
		if (opcional.isPresent()){
			ServicioJira.getInstanciaTransaccional().encuentraOSalva(numeroJira, opcional.get().getFields().getStatus().getStatusCategory().getName());			  
		}
	}
}