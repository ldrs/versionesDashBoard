package rd.huma.dashboard.servicios.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioFilaHistorica;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

@Path("versionConfirma/{id}")
public class WSVersionesConfirmaVersion {


	@Inject
	private @Servicio ServicioJobDespliegueVersion servicioJobDespliegueVersion;


	@Inject
	private @Servicio ServicioFilaHistorica servicioFilaHistorica;

	@Inject
	private @Servicio ServicioVersion servicioVersion;



	@GET
	public String confirmaVersion(@PathParam("id") String id){
		EntJobDespliegueVersion job = servicioJobDespliegueVersion.getJob(id);
		if (job!=null){

			EntVersion version = job.getVersion();
			servicioVersion.actualizarEstado(EEstadoVersion.DESPLIEGE_EXITOSO, version);

			servicioFilaHistorica.moverAHistorico(job.getFilaDespliegue(), version,job);

			return version.getId();

		}
		return "Job no existe";
	}
}