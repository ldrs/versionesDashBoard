package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

@Stateless
@Servicio
public class ServicioConfiguracionGeneral {
	@Inject
	private EntityManager entityManager;

	private static volatile EntConfiguracionGeneral cache;

	public static ServicioConfiguracionGeneral getInstanciaTransaccional(){
		Servicio servicio = ServicioConfiguracionGeneral.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioConfiguracionGeneral.class, servicio).get();
	}

	public static Optional<EntConfiguracionGeneral> getCacheConfiguracionGeneral(){
		if (cache == null){
			synchronized(ServicioConfiguracionGeneral.class){
				if (cache == null){
					Servicio s = ServicioConfiguracionGeneral.class.getAnnotation(Servicio.class);
					ServicioConfiguracionGeneral instancia = CDI.current().select(ServicioConfiguracionGeneral.class,s).get();
					Optional<EntConfiguracionGeneral> opcionalConfig = instancia.getConfiguracionGeneral();
					if (opcionalConfig.isPresent()){
						cache = opcionalConfig.get();
					}
				}
			}
		}
		return Optional.ofNullable(cache);
	}


	public Optional<EntConfiguracionGeneral> getConfiguracionGeneral(){
		return entityManager.createNamedQuery("configuracionGeneral",EntConfiguracionGeneral.class).getResultList().stream().findFirst();
	}

	public EntConfiguracionGeneral nuevaConfiguracionDefecto(){
		 EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();
		 entityManager.persist(configuracionGeneral);
		 return configuracionGeneral;
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
		 cache = null;

		 return configuracionGeneral;
	}
}