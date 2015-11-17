package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.dato.BranchUltimaRevision;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraEnComentario;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;

public class EjecutorSVNRevisiones extends AEjecutor {


	@Override
	public void ejecutar() {
		EntAplicacion aplicacion = ServicioAplicacion.getCacheAplicacion("sigef").get();
		ServicioBranch serviciobranch = ServicioBranch.getInstanciaTransaccional();

		List<BranchUltimaRevision> branches = serviciobranch.buscaBranchAunFaltanRevision(aplicacion);

		Set<BuscadorJiraEnComentario> buscadores = new HashSet<>();
		ServicioAplicacion.getInstanciaTransaccional().getAplicaciones().stream().map(EntAplicacion::getJiraKey).forEach(jira -> buscadores.add(BuscadorJiraEnComentario.of(jira)));


		new ProcesarSVNReviciones().ejecutar(ServicioSVN.para(aplicacion),
											serviciobranch.buscaModulos(aplicacion),
											serviciobranch.buscaSubModulos(aplicacion),
											serviciobranch.buscaCatalogos(aplicacion),
											branches,
											buscadores);
	}
}