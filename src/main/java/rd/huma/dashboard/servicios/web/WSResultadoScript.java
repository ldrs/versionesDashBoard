package rd.huma.dashboard.servicios.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioRepositorioDatos;
import rd.huma.dashboard.util.UtilString;

@Path("resultadoScript/{idJob}")
public class WSResultadoScript {

	@Inject
	private @Servicio ServicioRepositorioDatos servicioRepositorioDatos;

	@GET
	@Produces("application/text")
	public StreamingOutput resultado(@PathParam("idJob") String idJob){
		List<EntRepositorioDatosScriptEjecutados> scripts = servicioRepositorioDatos.getScriptEjecutadosPorJob(idJob);
		if (scripts.isEmpty()){
			return null;
		}
		if (scripts.size() == 1){
			return new StreamingOutput() {

				@Override
				public void write(OutputStream output) throws IOException,	WebApplicationException {
					output.write(UtilString.defecto(scripts.get(0).getResultado()).getBytes());
					output.flush();
					output.close();
				}
			};


		}else{
			return null;
		}
	}
}