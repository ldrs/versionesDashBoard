package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

@Stateless
@Servicio
public class ServicioConfiguracionGeneral {

	private static final Integer CACHE = Integer.valueOf(1);

	@Inject
	private EntityManager entityManager;

	public static Optional<EntConfiguracionGeneral> getCacheConfiguracionGeneral(){
		Cache<Integer, EntConfiguracionGeneral> cache = getCache();
		EntConfiguracionGeneral entConfiguracionGeneral = cache.get(CACHE);
		if (entConfiguracionGeneral == null){
			synchronized(ServicioConfiguracionGeneral.class){
				Servicio s = ServicioConfiguracionGeneral.class.getAnnotation(Servicio.class);
				ServicioConfiguracionGeneral instancia = CDI.current().select(ServicioConfiguracionGeneral.class,s).get();
				Optional<EntConfiguracionGeneral> opcionalConfig = instancia.getConfiguracionGeneral();
				if (opcionalConfig.isPresent()){
					cache.put(CACHE, opcionalConfig.get());
				}
				return opcionalConfig;

			}
		}
		return Optional.ofNullable(entConfiguracionGeneral);
	}

	private static Cache<Integer, EntConfiguracionGeneral> getCache(){
		CacheManager manager =  Caching.getCachingProvider().getCacheManager();
		Cache<Integer, EntConfiguracionGeneral> cache = manager.getCache("CONFIGURACION_GENERAL", Integer.class, EntConfiguracionGeneral.class);
		if (cache == null){
			cache = manager.createCache("CONFIGURACION_GENERAL", new MutableConfiguration<Integer, EntConfiguracionGeneral>().setStoreByValue(false).setManagementEnabled(true).setTypes(Integer.class, EntConfiguracionGeneral.class));
		}
		return cache;
	}


	public Optional<EntConfiguracionGeneral> getConfiguracionGeneral(){
		return entityManager.createNamedQuery("configuracionGeneral",EntConfiguracionGeneral.class).getResultList().stream().findFirst();
	}

	public EntConfiguracionGeneral configurar(String rutaSvn, String usrSvn, String pwdSvn, String rutaJira, String usrJira, String pwdJira, String rutaJenkins, String usrJenkins, String pwdJenkins,String rutaSysAid, String usrSysaid, String pwdSysAid){
		 Optional<EntConfiguracionGeneral> opcional = getConfiguracionGeneral();
		 EntConfiguracionGeneral configuracionGeneral;
		 if (opcional.isPresent()){
			 configuracionGeneral = entityManager.find(EntConfiguracionGeneral.class, opcional.get().getId());
		 }else{
			 configuracionGeneral = new EntConfiguracionGeneral();
		 }
		 configuracionGeneral.setPwdJenkins(pwdJenkins);
		 configuracionGeneral.setPwdJira(pwdJira);
		 configuracionGeneral.setPwdSvn(pwdSvn);
		 configuracionGeneral.setPwdSysaid(pwdSysAid);
		 configuracionGeneral.setUsrJenkins(usrJenkins);
		 configuracionGeneral.setUsrJira(usrJira);
		 configuracionGeneral.setUsrSvn(usrSvn);
		 configuracionGeneral.setUsrSysaid(usrSysaid);
		 configuracionGeneral.setRutaJenkins(rutaJenkins);
		 configuracionGeneral.setRutaJira(rutaJira);
		 configuracionGeneral.setRutaSvn(rutaSvn);
		 configuracionGeneral.setRutaSysaid(rutaSysAid);


		 entityManager.persist(configuracionGeneral);
		 if (getCache().get(CACHE)!=null){
			 getCache().put(CACHE, configuracionGeneral);
		 }

		 return configuracionGeneral;
	}
}