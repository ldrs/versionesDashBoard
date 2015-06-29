package rd.huma.dashboard.servicios.background.ejecutores.version;

import static rd.huma.dashboard.servicios.transaccional.ServicioAplicacion.getCacheAplicacion;
import static rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral.getCacheConfiguracionGeneral;
import static rd.huma.dashboard.servicios.transaccional.ServicioVersion.getInstanciaTransaccional;

import java.util.ArrayList;
import java.util.List;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntJira;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.servicios.background.Ejecutor;

public class EjecutorVersion  extends Ejecutor{

	private EntVersion version;
	private EntConfiguracionGeneral configuracionGeneral;

	
	public EjecutorVersion(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		configuracionGeneral = getCacheConfiguracionGeneral().orElseThrow(
																			() -> new IllegalStateException("Configuracion General No esta")
																		);

		version =	getInstanciaTransaccional().actualizarVersion(
																	version.getId(),
																	new BuscadorComentario(version, configuracionGeneral).encuentraComentario()
																	);
		EntAplicacion aplicacion = getCacheAplicacion(version.getSvnOrigen()).orElseThrow(() -> new IllegalStateException("aplicacion  No esta"));
		
		procesarTodosJiras(aplicacion);

	}
	
	private void procesarTodosJiras(EntAplicacion aplicacion){
		List<EntJira> jirasEncontradoComentarios = BuscadorJiraEnComentario.of(version.getComentario(), aplicacion.getJiraKey()).encuentraJira();

		List<EntJira> jirasEncontradosBranches = new BuscadorJiraPorQueryBranch(configuracionGeneral,aplicacion,version.getBranchOrigen()).encuentra();
		List<EntJira> todos = new ArrayList<>();
		todos.addAll(jirasEncontradoComentarios);
		todos.addAll(jirasEncontradosBranches);
		todos.stream().distinct().forEach(this::procesarJira);
	}
	
	private void procesarJira(EntJira jira){
		
	}
	
	
}
