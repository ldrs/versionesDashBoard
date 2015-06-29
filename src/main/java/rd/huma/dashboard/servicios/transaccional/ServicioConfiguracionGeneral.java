package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.EntConfiguracionGeneral;

@Stateless
public class ServicioConfiguracionGeneral {

	private static final Integer CACHE = Integer.valueOf(1);
	
	@Inject
	private EntityManager entityManager;

	public static Optional<EntConfiguracionGeneral> getCacheConfiguracionGeneral(){
		CacheManager manager =  Caching.getCachingProvider().getCacheManager();
		Cache<Integer, EntConfiguracionGeneral> cache = manager.getCache("CONFIGURACION_GENERAL", Integer.class, EntConfiguracionGeneral.class);
		EntConfiguracionGeneral entConfiguracionGeneral = cache.get(CACHE);
		if (entConfiguracionGeneral == null){
			synchronized(ServicioConfiguracionGeneral.class){
				ServicioConfiguracionGeneral instancia = CDI.current().select(ServicioConfiguracionGeneral.class,ServicioConfiguracionGeneral.class.getAnnotations()).get();
				Optional<EntConfiguracionGeneral> opcionalConfig = instancia.getConfiguracionGeneral();
				if (opcionalConfig.isPresent()){
					cache.put(CACHE, entConfiguracionGeneral);
				}
				return opcionalConfig;
				
			}
		}
		return Optional.ofNullable(entConfiguracionGeneral);
	}
	
	
	public Optional<EntConfiguracionGeneral> getConfiguracionGeneral(){
		return entityManager.createNamedQuery("configuracionGeneral",EntConfiguracionGeneral.class).getResultList().stream().findFirst();
	}
}