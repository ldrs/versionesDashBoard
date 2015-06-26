package rd.huma.dashboard.servicios.transaccional;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;

import rd.huma.dashboard.model.EntConfiguracionGeneral;

public class ServicioConfiguracionGeneral {

	private static final Integer CACHE = Integer.valueOf(1);

	public static EntConfiguracionGeneral getCacheConfiguracionGeneral(){
		CacheManager manager =  Caching.getCachingProvider().getCacheManager();
		Cache<Integer, EntConfiguracionGeneral> cache = manager.getCache("CONFIGURACION_GENERAL", Integer.class, EntConfiguracionGeneral.class);
		EntConfiguracionGeneral entConfiguracionGeneral = cache.get(CACHE);
		if (entConfiguracionGeneral == null){
		}
		return null;

	}
}