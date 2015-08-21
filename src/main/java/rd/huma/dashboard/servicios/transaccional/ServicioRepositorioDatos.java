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

	public List<EntRepositorioDatosScriptEjecutados> getScriptEjecutados(EntRepositorioDatos repositorioDatos, String url){
		return entityManager.createNamedQuery("buscarPorUrl.scriptEjecutados",EntRepositorioDatosScriptEjecutados.class).setParameter("rep", repositorioDatos).setParameter("scr", url).getResultList();
	}

	public List<EntRepositorioDatosScriptEjecutados> getScriptEjecutadosPorJob(String idJob){
		return entityManager.createNamedQuery("buscarPorJob.scriptEjecutados",EntRepositorioDatosScriptEjecutados.class).setParameter("id", idJob).getResultList();
	}

	public List<EntRepositorioDatosScriptEjecutados> getScriptEjecutadosPorVersion(String version){
		return entityManager.createNamedQuery("buscarPorVersion.scriptEjecutados",EntRepositorioDatosScriptEjecutados.class).setParameter("ver", version).getResultList();
	}

	private EntRepositorioDatos actualizar(EntRepositorioDatos repositorioDatos){
		repositorioDatos.setUltimaActualizacion(LocalDateTime.now());
		entityManager.merge(repositorioDatos);
		return repositorioDatos;
	}

	public EntRepositorioDatosScriptEjecutados crearScript(EntRepositorioDatosScriptEjecutados script) {
		entityManager.persist(script);
		return script;
	}

	public EntRepositorioDatosScriptEjecutados actualizarScript(EntRepositorioDatosScriptEjecutados script) {
		return entityManager.merge(script);
	}

}