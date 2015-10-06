package rd.huma.dashboard.servicios.transaccional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntRepositorioDatos;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosActualizacion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatosScriptEjecutados;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoRepositorioActualizacionDatos;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoServidor;
import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorScriptTodos;

@Stateless
@Servicio
public class ServicioRepositorioDatos {

	private @Inject @Servicio ServicioServidor servicioServidor;

	private @Inject @Servicio ServicioVersion servicioVersion;

	@Inject
	private MonitorEjecutor monitorEjecutor;

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

	public Optional<EntRepositorioDatos> buscaRepositorio(String host, String squema, String servicio){
		return entityManager.createNamedQuery("buscarPorAll.repositorioDatos",EntRepositorioDatos.class).setParameter("host", host)
				.setParameter("esquema", squema)
				.setParameter("serv", servicio).getResultList().stream().findFirst();
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

	public String inicioActualizar(String host,String nombreServicio, String schema,String puerto) {
		Optional<EntRepositorioDatos> posibleRepositorio = buscaRepositorio(host, schema, nombreServicio);
		EntRepositorioDatos repositorioDatos;
		if (posibleRepositorio.isPresent()){
			repositorioDatos = posibleRepositorio.get();
		}else{
			repositorioDatos =  nuevoRepositorio(nombreServicio, schema, host);
		}
		Optional<EntRepositorioDatosActualizacion> existeCambio = entityManager.createNamedQuery("buscar.repositorioDatosActualizacion",EntRepositorioDatosActualizacion.class).setParameter("repo", repositorioDatos).getResultList().stream().findFirst();
		if (existeCambio.isPresent()){
			EntRepositorioDatosActualizacion actualizacion = existeCambio.get();
			actualizacion.asignarFechaFin();
			actualizacion.setEstado(EEstadoRepositorioActualizacionDatos.CANCELADO);
			entityManager.merge(actualizacion);
		}
		EntRepositorioDatosActualizacion repositorioDatosActualizacion = new EntRepositorioDatosActualizacion();
		repositorioDatosActualizacion.setRepositorioDatos(repositorioDatos);
		entityManager.persist(repositorioDatosActualizacion);

		List<EntServidor> servidores = entityManager.createNamedQuery("buscarPorRepositorio.servidor",EntServidor.class).setParameter("bsd", repositorioDatos) .getResultList();
		for(EntServidor servidor : servidores){
			servidor.setEstadoServidor(EEstadoServidor.NO_DISPONIBLE_ACTUALIZACION_DB);
			servicioServidor.actualizarServidor(servidor);
		}


		return repositorioDatosActualizacion.getId();
	}

	public String finalizarActualizar(String host, String nombreServicio,	String schema, String puerto) {
		Optional<EntRepositorioDatos> posibleRepositorio = buscaRepositorio(host, schema, nombreServicio);
		EntRepositorioDatos repositorioDatos;
		if (posibleRepositorio.isPresent()){
			repositorioDatos = posibleRepositorio.get();
		}else{
			repositorioDatos =  nuevoRepositorio(nombreServicio, schema, host);
		}
		Optional<EntRepositorioDatosActualizacion> existeCambio = entityManager.createNamedQuery("buscar.repositorioDatosActualizacion",EntRepositorioDatosActualizacion.class).setParameter("repo", repositorioDatos).getResultList().stream().findFirst();
		if (existeCambio.isPresent()){
			EntRepositorioDatosActualizacion actualizacion = existeCambio.get();
			actualizacion.asignarFechaFin();
			actualizacion.setEstado(EEstadoRepositorioActualizacionDatos.FINALIZADO);
			actualizacion = entityManager.merge(actualizacion);

			List<EntServidor> servidores = entityManager.createNamedQuery("buscarPorRepositorio.servidor",EntServidor.class).setParameter("bsd", repositorioDatos) .getResultList();

			repositorioDatos.setUltimaActualizacion(LocalDateTime.now());
			entityManager.merge(repositorioDatos);

			for(EntServidor servidor : servidores){
				servidor.setEstadoServidor(servidor.getVersionActual() == null? EEstadoServidor.LIBRE : EEstadoServidor.OCUPADO);
				servicioServidor.actualizarServidor(servidor);
				if (servidor.getVersionActual()!=null){
					List<EntVersionScript> scripts = servicioVersion.buscaScript(servidor.getVersionActual());
					if (!scripts.isEmpty()){
						monitorEjecutor.ejecutarAsync(new EjecutorScriptTodos(servidor.getVersionActual()));
					}
				}
			}

			return actualizacion.getId();
		}

		return "not found";
	}

}