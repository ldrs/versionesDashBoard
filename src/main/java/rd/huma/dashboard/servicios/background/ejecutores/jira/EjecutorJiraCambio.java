package rd.huma.dashboard.servicios.background.ejecutores.jira;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion.EjecutorSeleccionFila;
import rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion.ServicioSeleccionFila;
import rd.huma.dashboard.servicios.background.ejecutores.version.ColectorInformacionFieldsJira;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioJira;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorJiraCambio extends AEjecutor {

	private ServicioJira servicioJira;

	private String numeroJira;

	public EjecutorJiraCambio(String numeroJira) {
		this.numeroJira = numeroJira;
	}



	@Override
	public void ejecutar() {
		this.servicioJira = ServicioJira.getInstanciaTransaccional();
		EntConfiguracionGeneral configuracion = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();

		new BuscadorJiraRestApi(new JiraQuery(configuracion, ETipoQueryJira.KEY, numeroJira)).getIssues().stream().findFirst().ifPresent(this::encontrarJira);
	}

	private void encontrarJira(Issues issues){
		Optional<EntJira> posibleJira = servicioJira.encuentra(numeroJira);
		if (posibleJira.isPresent()){
			jiraExiste(issues, posibleJira.get());
		}else{

		}
	}

	private Optional<EntVersionJira> encontraVersion(EntJira jira){
		return ServicioVersion.getInstanciaTransaccional().buscaJiras(jira).stream().findFirst();
	}

	private void jiraExiste(Issues issues, EntJira jira){
		String estado = issues.getFields().getStatus().getStatusCategory().getName();
		if (!jira.getEstado().equals(estado)){
			jira.setEstado(estado);
			servicioJira.salva(jira);

			Optional<EntVersionJira> posibleVersionJira = encontraVersion(jira);
			if (posibleVersionJira.isPresent()){
				EntVersion version = posibleVersionJira.get().getVersion();
				Set<EntFilaDespliegue> filas =  new HashSet<>(new ServicioSeleccionFila(version).filasParaVersion());
				new RevisaVersionJira(jira, version, issues.getFields()).ejecutar();
				if (!filas.isEmpty()){
					List<EntFilaDespliegueVersion> filasVersiones = ServicioFila.getInstanciaTransaccional().getFilas(version);
					Set<EntFilaDespliegue> filasExistentes = filasVersiones.stream().map(EntFilaDespliegueVersion::getFila).collect(Collectors.toSet());


					Set<EntFilaDespliegue> filasIgnorar = filasExistentes.stream().filter(fila -> filas.contains(fila)).collect(Collectors.toSet());

					ServicioVersion.getInstanciaTransaccional().gestionarFila(new EjecutorSeleccionFila(version, filasIgnorar));
				}
			}
		}
	}

}