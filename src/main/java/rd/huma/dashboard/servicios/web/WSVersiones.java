package rd.huma.dashboard.servicios.web;

import java.util.EnumSet;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntMetricaCompilacionFallida;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;

@Path("version")
public class WSVersiones {

	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@Inject
	private @Servicio ServicioAplicacion servicioAplicacion;

	@Inject
	private @Servicio ServicioPersona servicioPersona;

	@Path("nueva")
	@GET
	public void nuevaVersion(){

	}

	@Path("falloCompilacion/{autor}/{aplicacion}/{branch}/{revision}")
	@GET
	public String metricasFallaCompilacion(@PathParam("autor") String autor,@PathParam("aplicacion") String aplicacion,@PathParam("branch") String branch, @PathParam("revision") long revision){
		EntMetricaCompilacionFallida metricaCompilacionFallida = new EntMetricaCompilacionFallida();

		metricaCompilacionFallida.setAplicacion(servicioAplicacion.getAplicacion(aplicacion).get());
		metricaCompilacionFallida.setAutor(servicioPersona.buscaOCreaPersona(autor));
		metricaCompilacionFallida.setRevision(revision);
		metricaCompilacionFallida.setBranch(branch);

		servicioVersion.crear(metricaCompilacionFallida);
		return "{metrica:ejecutada}";
	}


	@Path("recientes")
	@GET
	public String recientes(){
		JsonArrayBuilder builder =  Json.createArrayBuilder();
		for(EntVersion version : servicioVersion.buscaVersionesRecientes(EnumSet.allOf(EEstadoVersion.class))){
			builder.add(
			Json.createObjectBuilder()	.add("numero", version.getNumero())
										.add("id", version.getId())
										.add("fecha", UtilFecha.getFechaFormateada(version.getFechaRegistro()))
										.add("branch", version.getBranchOrigen() )
										.add("autor", Json.createObjectBuilder().add("nombre", version.getAutor().getNombreNullSafe())
																				.add("correo", version.getAutor().getCorreo())
											)
						);

		}
		return builder.build().toString();
	}
}