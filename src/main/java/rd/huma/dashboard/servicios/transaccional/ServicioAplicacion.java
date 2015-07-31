package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Optional;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAplicacion;

@Stateless
@Servicio
public class ServicioAplicacion {

	@Inject
	private EntityManager entityManager;

	public static Optional<EntAplicacion> getCacheAplicacion(String nombre){
		Cache<String, EntAplicacion> cache = getCache();
		EntAplicacion aplicacion = cache.get(nombre);
		if (aplicacion == null){
			synchronized(ServicioAplicacion.class){
				Servicio anotacion = ServicioConfiguracionGeneral.class.getAnnotation(Servicio.class);
				ServicioAplicacion instancia = CDI.current().select(ServicioAplicacion.class,anotacion).get();
				Optional<EntAplicacion> opcionalConfig = instancia.getAplicacion(nombre);
				if (opcionalConfig.isPresent()){
					cache.put(nombre, opcionalConfig.get());
				}
				return opcionalConfig;

			}
		}
		return Optional.ofNullable(aplicacion);
	}


	private static Cache<String, EntAplicacion> getCache(){
		CacheManager manager =  Caching.getCachingProvider().getCacheManager();
		Cache<String, EntAplicacion> cache = manager.getCache("APLICACIONES", String.class, EntAplicacion.class);
		if (cache == null){
			cache = manager.createCache("APLICACIONES", new MutableConfiguration<String, EntAplicacion>().setStoreByValue(false).setManagementEnabled(true).setTypes(String.class, EntAplicacion.class));
		}
		return cache;
	}

	public Optional<EntAplicacion> getAplicacion(String nombre) {
		return entityManager.createNamedQuery("aplicacion.buscar", EntAplicacion.class).setParameter("nomApp", nombre).getResultList().stream().findFirst();
	}

	public EntAplicacion configurarCrearAplicacion(String nombre, String jiraKey, String svnPath, int orden, String nombrePropiedadesPom){
		return configurarCrearAplicacion(nombre, jiraKey, svnPath, orden, nombrePropiedadesPom, null,null);
	}

	public EntAplicacion configurarCrearAplicacion(String nombre, String jiraKey, String svnPath, int orden, String nombrePropiedadesPom, String jenkinsNombreJob, String ambienteParaHacerDeployDefecto){
		Optional<EntAplicacion> optional = getAplicacion(nombre);
		EntAplicacion aplicacion;
		if (optional.isPresent()){
			aplicacion = entityManager.find(EntAplicacion.class, optional.get().getId());
		}else{
			aplicacion = new EntAplicacion();
		}
		aplicacion.setJiraKey(jiraKey);
		aplicacion.setNombre(nombre);
		aplicacion.setSvnPath(svnPath);
		aplicacion.setOrden(orden);
		aplicacion.setNombrePropiedadesPom(nombrePropiedadesPom);
		aplicacion.setNombreJobJenkins(jenkinsNombreJob);
		aplicacion.setRutaSvnAmbiente(ambienteParaHacerDeployDefecto);
		entityManager.persist(aplicacion);
		Cache<String, EntAplicacion> cache = getCache();
		if (cache.containsKey(nombre)){
			cache.put(nombre, aplicacion);
		}

		return aplicacion;
	}


	public static ServicioAplicacion getInstanciaTransaccional(){
		Servicio servicio = ServicioAplicacion.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioAplicacion.class, servicio).get();
	}

	public List<EntAplicacion> getAplicaciones() {
		return entityManager.createNamedQuery("aplicacion.todos",EntAplicacion.class).getResultList();
	}
}