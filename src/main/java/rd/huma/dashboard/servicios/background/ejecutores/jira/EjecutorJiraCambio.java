package rd.huma.dashboard.servicios.background.ejecutores.jira;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion.EjecutorSeleccionFila;
import rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion.ServicioSeleccionFila;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioJira;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorJiraCambio extends AEjecutor {

	private static final Logger LOGGER = Logger.getLogger(EjecutorJiraCambio.class.getSimpleName());

	private ServicioJira servicioJira;
	private ServicioServidor servicioServidor;
	private ServicioFila servicioFila;
	private String numeroJira;

	public EjecutorJiraCambio(String numeroJira) {
		this.numeroJira = numeroJira;
	}

	@Override
	public void ejecutar() {
		LOGGER.info("Buscando Informacion de Jira " + numeroJira);

		this.servicioJira = ServicioJira.getInstanciaTransaccional();
		EntConfiguracionGeneral configuracion = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();

		new BuscadorJiraRestApi(new JiraQuery(configuracion, ETipoQueryJira.KEY, numeroJira)).getIssues().stream().findFirst().ifPresent(this::encontrarJira);
	}

	private void encontrarJira(Issues issues){
		Optional<EntJira> posibleJira = servicioJira.encuentra(numeroJira);
		String estado = issues.getFields().getStatus().getStatusCategory().getName();
		if (posibleJira.isPresent()){
			jiraExiste(issues, posibleJira.get(),estado);
		}else{
			servicioJira.encuentraOSalva(numeroJira, estado);
		}
	}

	private Optional<EntVersionJira> encontraVersion(EntJira jira){
		return ServicioVersion.getInstanciaTransaccional().buscaJiras(jira).stream().findFirst();
	}

	private void jiraExiste(Issues issues, EntJira jira, String nuevoEstado){
		String estadoPresente = jira.getEstado();
		if (!estadoPresente.equals(nuevoEstado)){
			jira.setEstado(nuevoEstado);
			servicioJira.salva(jira);

			Optional<EntVersionJira> posibleVersionJira = encontraVersion(jira);
			if (posibleVersionJira.isPresent()){
				EntVersion version = posibleVersionJira.get().getVersion();
				Set<EntFilaDespliegue> filas =  new HashSet<>(new ServicioSeleccionFila(version).filasParaVersion());
				new RevisaVersionJira(jira, version, issues.getFields()).ejecutar();
				this.servicioFila = ServicioFila.getInstanciaTransaccional();
				if (!filas.isEmpty()){
					List<EntFilaDespliegueVersion> filasVersiones = servicioFila.getFilas(version);
					Set<EntFilaDespliegue> filasExistentes = filasVersiones.stream().map(EntFilaDespliegueVersion::getFila).collect(Collectors.toSet());


					Set<EntFilaDespliegue> filasIgnorar = filasExistentes.stream().filter(fila -> filas.contains(fila)).collect(Collectors.toSet());

					ServicioVersion.getInstanciaTransaccional().gestionarFila(new EjecutorSeleccionFila(version, filasIgnorar));
				}
				this.servicioServidor = ServicioServidor.getInstanciaTransaccional();
				List<EntServidor>  servidors = servicioServidor.getServidoresPorBranch(version.getBranchOrigen());
				if (!servidors.isEmpty()){
					servidors.forEach(servidor-> sacarLaVersionSiTieneQueHacerlo(servidor, nuevoEstado));
				}
			}
		}
	}

	private void sacarLaVersionSiTieneQueHacerlo(EntServidor servidor, String nuevoEstado){
		Optional<EntFilaDespliegue> fila =  servicioFila.getFilasDeploment().stream().filter( f -> f.getAmbiente().equals(servidor.getAmbiente())).findFirst();
		if (fila.isPresent()){
			EntFilaDespliegue filaDespliegue = fila.get();
			if (Arrays.stream(filaDespliegue.getEstadosJiras().split(",")).filter(s-> nuevoEstado.equals(s)).count()==0){
				LOGGER.info(String.format("Retirando la version del servidor %s ya que el jira(%s) cambio al estado %s",servidor.getNombre(),numeroJira, nuevoEstado));
				servicioServidor.cambiaVersionServidor(servidor, null);
			}
		}
	}
}