package rd.huma.dashboard.servicios;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;

import rd.huma.dashboard.model.jira.Histories;
import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;

public class PruebaString {
	@Test  @Ignore
	public void bla(){
		long i = Arrays.stream("Abierto,Creado,En curso,Nuevo".split(",")).filter(s-> "Verificado".equals(s)).count();
		 System.out.println(i);
	}

	@Test  @Ignore
	public void jira(){

		Optional<Issues> jira = new BuscadorJiraRestApi(new JiraQuery(new EntConfiguracionGeneral(), ETipoQueryJira.KEY, "SGF-2210")).getIssues().stream().findFirst();
		if (jira.isPresent()){
			System.out.println(jira.get().getFields().getStatus().getDescription());
		}
	}


	@Test @Ignore
	public void probar(){
		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();

		Issues issues = new BuscadorJiraRestApi(new JiraQuery(configuracionGeneral, ETipoQueryJira.KEY, "SGF-1649")).getIssues()
		.stream().findFirst().get();
		String reporte = issues.getFields().getReportes();
		String[] reportes = reporte.split(".rdf");
		for (String string : reportes) {
			System.out.println(string);
		}
	}


	@Test @Ignore
	public void probarCambios(){
		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();

		List<Histories> issues = new BuscadorJiraRestApi(new JiraQuery(configuracionGeneral, ETipoQueryJira.KEY_CAMBIOS, "SGF-1649")).getHistories();
		for (Histories histories : issues) {
			System.out.println(histories.getCreated() + histories.getAuthor().getName());
		}
	}


}
