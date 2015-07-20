package rd.huma.dashboard.servicios.web;

import java.util.Base64;
import java.util.Optional;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.servicios.integracion.activedirectory.ServicioActiveDirectory;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;

@Path("seguridad")
public class WSSeguridad {

	private @Inject @Servicio ServicioPersona servicioPersona;


	@Path("iniciasesion/{usuario}/{credenciales}")
	@GET
	public String iniciaSesion(@PathParam("usuario") String usuario,@PathParam("credenciales") String crendenciales){

		usuario = new String(Base64.getDecoder().decode(usuario));
		crendenciales = new String( Base64.getDecoder().decode(crendenciales));

		Optional<EntConfiguracionGeneral> configOptional = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral();
		if (configOptional.isPresent()){
			ServicioActiveDirectory servicioActiveDirectory = new ServicioActiveDirectory(configOptional.get(), usuario, crendenciales);
			if (servicioActiveDirectory.isValido()){
				SearchResult resultado = servicioActiveDirectory.getResultado();
				Attributes atributos = resultado.getAttributes();

				Optional<EntPersona> personaOpcional;
				try {
					personaOpcional = servicioPersona.buscaPorCorreo(atributos.get("mail").get().toString());
					if (personaOpcional.isPresent()){
						EntPersona persona = personaOpcional.get();
						if (persona.getNombre() == null){
							persona.setNombre(atributos.get("givenname").toString());
							servicioPersona.actualiza(persona);
							return  new StringBuilder(150).append("{\"inicioSesion\":true , ").append("\"id':\"").append(persona.getId()).append("\"}").toString() ;
						}
					}
				} catch (NamingException e) {}

			}
		}
		return mensajeInvalidoSesion();

	}

	private String mensajeInvalidoSesion(){
		return "{\"inicioSesion\":\"false\"}";
	}
}