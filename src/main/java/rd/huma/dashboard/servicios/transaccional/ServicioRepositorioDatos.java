package rd.huma.dashboard.servicios.transaccional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntRepositorioDatos;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntVersionScript;

@Stateless
@Servicio
public class ServicioRepositorioDatos {


	@Inject
	private EntityManager entityManager;

	public static ServicioRepositorioDatos getInstanciaTransaccional(){
		Servicio servicio = ServicioRepositorioDatos.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioRepositorioDatos.class, servicio).get();
	}

	public String actualizar(String nombreServicio, String schema) {
		return actualizar(nombreServicio, schema,null);
	}

	public String actualizar(String nombreServicio, String schema, String  host) {
		Optional<EntRepositorioDatos> opcional = entityManager.createNamedQuery("buscar.repositorioDatos",EntRepositorioDatos.class)
				.setParameter("sc", schema)
				.setParameter("serv", nombreServicio)
				.getResultList().stream().findFirst();
		if (opcional.isPresent()){
			return actualizar(opcional.get()).getId();
		}else{
			return nuevoRepositorio(nombreServicio, schema,host).getId();
		}
	}

	public EntRepositorioDatos nuevoRepositorio(String nombreServicio,String schema,String  host) {
		EntRepositorioDatos repositorioDatos = new EntRepositorioDatos();
		repositorioDatos.setSchema(schema);
		repositorioDatos.setServicio(nombreServicio);
		repositorioDatos.setHost(host);
		entityManager.persist(repositorioDatos);
		return repositorioDatos;
	}

	public void creaNuevoEjecucionScripts(List<EntVersionScript> scripts, EntRepositorioDatos datos){
		for (EntVersionScript entVersionScript : scripts) {

		}

		EntRepositorioDatosScriptEjecutados scriptEjecutados = new EntRepositorioDatosScriptEjecutados();
	}

	private EntRepositorioDatos actualizar(EntRepositorioDatos repositorioDatos){
		repositorioDatos.setUltimaActualizacion(LocalDateTime.now());
		entityManager.merge(repositorioDatos);
		return repositorioDatos;
	}

}