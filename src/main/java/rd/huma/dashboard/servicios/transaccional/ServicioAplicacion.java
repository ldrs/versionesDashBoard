package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.EntAplicacion;

@Stateless
public class ServicioAplicacion {
	
	@Inject
	private EntityManager entityManager;
	
	public static Optional<EntAplicacion> getCacheAplicacion(String nombre){
		CacheManager manager =  Caching.getCachingProvider().getCacheManager();
		Cache<String, EntAplicacion> cache = manager.getCache("APLICACIONES", String.class, EntAplicacion.class);
		EntAplicacion aplicacion = cache.get(nombre);
		if (aplicacion == null){
			synchronized(ServicioAplicacion.class){
				Stateless anotacion = ServicioConfiguracionGeneral.class.getAnnotation(Stateless.class);
				ServicioAplicacion instancia = CDI.current().select(ServicioAplicacion.class,anotacion).get();
				Optional<EntAplicacion> opcionalConfig = instancia.getAplicacion(nombre);
				if (opcionalConfig.isPresent()){
					cache.put(nombre, aplicacion);
				}
				return opcionalConfig;
				
			}
		}
		return Optional.ofNullable(aplicacion);
	}

	public Optional<EntAplicacion> getAplicacion(String nombre) {
		return entityManager.createNamedQuery("aplicacion.buscar", EntAplicacion.class).setParameter("nomApp", nombre).getResultList().stream().findFirst();
	}
}
