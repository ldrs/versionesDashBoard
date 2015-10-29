package rd.huma.dashboard.servicios.transaccional;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import rd.huma.dashboard.model.transaccional.EntReporteScript;
import rd.huma.dashboard.model.transaccional.EntReporteScriptParametros;

@Stateless
@Servicio
public class ServicioReporte {

	@Inject
	private EntityManager entityManager;

	private @Inject @Servicio ServicioGrupo servicioGrupo;

	public static ServicioReporte getInstanciaTransaccional(){
		Servicio servicio = ServicioReporte.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioReporte.class, servicio).get();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getResultadoReporte(String idReporte){
		if (idReporte == null){
			return Collections.emptyList();
		}

		EntReporteScript reporte = entityManager.find(EntReporteScript.class, idReporte);

		if (reporte == null){
			return Collections.emptyList();
		}

		Query query = entityManager.createQuery(reporte.getQuery());

		List<EntReporteScriptParametros> parametros =  entityManager.createNamedQuery("buscaPorPadre.reporteScriptParametros",EntReporteScriptParametros.class)
		.setParameter("rep", reporte).getResultList();
		for (EntReporteScriptParametros parametro : parametros) {
			query.setParameter(parametro.getNombre(), parametro.getValor());
		}
		return query.getResultList();
	}

	public List<EntReporteScript> getReportes() {
		return entityManager.createNamedQuery("buscarActivos.reporte",EntReporteScript.class).getResultList();
	}
}