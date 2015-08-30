package rd.huma.dashboard.servicios.web;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntConfiguracionNotificacion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionNotificacion;

@Path("configuracionNotificacion")
public class WSConfiguracionNotificacion {

	@Inject @Servicio ServicioConfiguracionNotificacion servicioConfiguracionNotificacion;
	
	
	@GET
	@Path("grupos/{ambiente}/{tipo}")
	public String getGruposPorAmbienteTiposConfiguracion(@PathParam("ambiente") String ambiente,@PathParam("tipo") String tipo){
		List<EntConfiguracionNotificacion> grupos = servicioConfiguracionNotificacion.buscarGrupos(ambiente, ETipoAlertaVersion.valueOf(tipo));
		JsonArrayBuilder arreglo = Json.createArrayBuilder(); 
		for (EntConfiguracionNotificacion config : grupos) {
			arreglo.add(Json.createObjectBuilder().add("id", config.getId())
												   .add("grupoId", config.getGrupoPersona().getId())
												   .add("grupoNombre", config.getGrupoPersona().getGrupo())
					);
		}
		
		return arreglo.build().toString();
	}
	
	
	
	@GET
	@Path("todosTipos")
	public String getTiposConfiguracion(){
		JsonArrayBuilder arreglo = Json.createArrayBuilder(); 
		for (ETipoAlertaVersion alerta : ETipoAlertaVersion.values()){
			arreglo.add(alerta.name());
		}
		return arreglo.build().toString();
	}
	
	@GET
	@Path("adicionar/{tipo}/{ambiente}/{grupoPersona}")
	public String adicionar(@PathParam("tipo") String tipo,@PathParam("ambiente") String ambiente,@PathParam("grupoPersona")  String grupoPersona){
		return servicioConfiguracionNotificacion.nuevo(tipo, ambiente, grupoPersona).getId();
	}
	
	
	@GET
	@Path("eliminar/{tipo}/{ambiente}/{grupoPersona}")
	public boolean eliminar(@PathParam("tipo") String tipo,@PathParam("ambiente") String ambiente,@PathParam("grupoPersona")  String grupoPersona){
		return servicioConfiguracionNotificacion.eliminar(tipo, ambiente, grupoPersona);
	}
}
