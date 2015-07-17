package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

import javax.inject.Inject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntGrupoPersona;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioGrupo;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;

@Path("grupos")
public class WSGrupos {

	@Inject
	private @Servicio ServicioPersona  servicioPersona;

	@Inject
	private @Servicio ServicioGrupo  servicioGrupo;

	@Path("todos")
	@GET
	public String getGrupos(){
		JsonArrayBuilder grupos = createArrayBuilder();
		servicioGrupo.grupos().forEach(g -> grupos.add(grupoJson(g)));
		return grupos.build().toString();
	}

	@Path("agregaGrupo/{nombre}")
	@GET
	public String agregarGrupo(@PathParam("nombre") String nombre){
		return new StringBuilder(150).append("{ \"id\" : \"").append(servicioGrupo.nuevoGrupo(nombre).getId()).append("\"}").toString();
	}

	@Path("eliminaGrupo/{idGrupo}")
	@GET
	public String eliminaGrupo(@PathParam("idGrupo") String idGrupo){
		servicioGrupo.borrarGrupo(idGrupo);

		return "{ \"borrado\" : true}";
	}

	@Path("agregaPersona/{idGrupo}/{idPersona}")
	@GET
	public String agregaPersona(@PathParam("idGrupo") String idGrupo, @PathParam("idPersona") String idPersona){
		return new StringBuilder(150).append("{ \"id\" : \"").append(servicioGrupo.agregarDetalle(idGrupo, idPersona).getId()).append("\"}").toString();
	}


	@Path("eliminaPersona/{idPersonaDetalle}")
	@GET
	public String eliminaPersona(@PathParam("idPersonaDetalle") String idPersonaDetalle){
		servicioGrupo.borrarDetalle(idPersonaDetalle);
		return "{ \"borrado\" : true}";
	}

	private JsonObjectBuilder grupoJson(EntGrupoPersona grupo){
		JsonObjectBuilder jsonGrupo = createObjectBuilder();
		jsonGrupo.add("nombre", grupo.getGrupo())
				 .add("id", grupo.getId())
				 .add("personas", personas(grupo));
		return jsonGrupo;
	}

	private JsonArrayBuilder personas(EntGrupoPersona grupo){
		JsonArrayBuilder jsonPersonas = createArrayBuilder();

		servicioGrupo.buscarDetallePorGrupo(grupo.getId()).forEach(p ->

			jsonPersonas.add(createObjectBuilder()
					.add("nombre"		,  p.getPersona().getNombreNullSafe())
					.add("correo"		, p.getPersona().getCorreo())
					.add("usuarioSvn"	, p.getPersona().getUsuarioSvn())
					.add("id", p.getId())
				));
		return jsonPersonas;
	}


}
