package rd.huma.dashboard.servicios.web;

import java.util.Base64;
import java.util.Optional;

import javax.inject.Inject;
import javax.json.Json;
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
				String email = getValor(atributos,"mail");

				personaOpcional = servicioPersona.buscaPorCorreo(email);
				EntPersona persona;
				if (personaOpcional.isPresent()){
					persona = personaOpcional.get();
				}else{
					persona = servicioPersona.crearPersona(getValor(atributos,"cn"), email, getValor(atributos,"samaccountname").replace('.', '_'));
				}
				return Json.createObjectBuilder().add("inicioSesion", "true")
										  .add("usuario", Json.createObjectBuilder()
												  						.add("id", persona.getId())
												  						.add("nombre", persona.getNombre())
												  						.add("email", persona.getCorreo())
												  						.add("usuarioSVN", persona.getUsuarioSvn())
												  						.add("prioridadAmbientes", prioridadesAmbientes(persona.getId()))
												  						.add("undeployAmbientes", undeployAmbientes(persona.getId()))
												  						.add("scriptAmbientes", scriptAmbientes(persona.getId()))
												  ).build().toString();

			}
		}
		return mensajeInvalidoSesion();

	}

	private String scriptAmbientes(String id) {
		return "[]";
	}

	private String undeployAmbientes(String id) {
		return "[]";
	}

	private String prioridadesAmbientes(String idPersona){
		return "[]";
	}

	private String getValor(Attributes atributos, String atributo){
		String tmp = atributos.get(atributo).toString();
		return tmp.substring(tmp.indexOf(':')+2).trim();
	}

	private String mensajeInvalidoSesion(){
		return "{\"inicioSesion\":\"false\"}";
	}
}