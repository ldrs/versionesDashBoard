package rd.huma.dashboard.servicios.web;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.integracion.activedirectory.ServicioActiveDirectory;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;

@Path("seguridad")
public class WSSeguridad {

	private @Inject @Servicio ServicioPersona servicioPersona;


	@Path("iniciasesion/{usuario}/{credenciales}")
	public String iniciaSesion(@PathParam("usuario") String usuario,@PathParam("credenciales") String crendenciales){
		Optional<EntConfiguracionGeneral> configOptional = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral();
		if (configOptional.isPresent()){
			ServicioActiveDirectory servicioActiveDirectory = new ServicioActiveDirectory(configOptional.get(), usuario, crendenciales);
			if (servicioActiveDirectory.isValido()){
				return "{inicioSesion:true}";
			}
		}
		return mensajeInvalidoSesion();

	}

	private String mensajeInvalidoSesion(){
		return "{inicioSesion:false}";
	}
}