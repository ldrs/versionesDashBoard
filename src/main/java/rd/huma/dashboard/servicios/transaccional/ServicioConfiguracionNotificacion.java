package rd.huma.dashboard.servicios.transaccional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAmbiente;
import rd.huma.dashboard.model.transaccional.EntConfiguracionNotificacion;
import rd.huma.dashboard.model.transaccional.EntGrupoPersona;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;

@Servicio
@Stateless
public class ServicioConfiguracionNotificacion {

	private @Servicio @Inject ServicioAmbiente servicioAmbiente;

	@Inject
	private EntityManager entityManager;


	public static ServicioConfiguracionNotificacion getInstanciaTransaccional(){
		Servicio servicio = ServicioConfiguracionNotificacion.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioConfiguracionNotificacion.class, servicio).get();
	}

	public boolean eliminar(String alerta, String ambiente, String grupoPersona){
		EntAmbiente eAmbiente = entityManager.find(EntAmbiente.class, ambiente);
		EntGrupoPersona eGrupoPersona = entityManager.find(EntGrupoPersona.class, ambiente);

		Optional<EntConfiguracionNotificacion> eliminar =  buscar(ETipoAlertaVersion.valueOf(alerta), eAmbiente, eGrupoPersona).stream().findFirst();
		if (eliminar.isPresent()){
			entityManager.remove(entityManager.find(EntConfiguracionNotificacion.class, eliminar.get().getId()));
			return true;
		}

		return false;
	}

	public EntConfiguracionNotificacion nuevo(String alerta, String ambiente, String grupoPersona){
		EntAmbiente eAmbiente = entityManager.find(EntAmbiente.class, ambiente);
		EntGrupoPersona eGrupoPersona = entityManager.find(EntGrupoPersona.class, ambiente);
		return nuevo(ETipoAlertaVersion.valueOf(alerta), eAmbiente, eGrupoPersona);
	}

	public EntConfiguracionNotificacion nuevo(ETipoAlertaVersion alerta, EntAmbiente ambiente, EntGrupoPersona grupoPersona){
		List<EntConfiguracionNotificacion> list = buscar(alerta, ambiente, grupoPersona);

		if (list.isEmpty()){
			EntConfiguracionNotificacion configuracionNotificacion = new EntConfiguracionNotificacion();
			configuracionNotificacion.setAlerta(alerta);
			configuracionNotificacion.setAmbiente(ambiente);
			configuracionNotificacion.setGrupoPersona(grupoPersona);
			entityManager.persist(configuracionNotificacion);
			return configuracionNotificacion;
		}else{
			return list.get(0);
		}


	}

	private List<EntConfiguracionNotificacion> buscar(ETipoAlertaVersion alerta, EntAmbiente ambiente, EntGrupoPersona grupoPersona){
		 return entityManager.createNamedQuery("buscaUK.configNotificacion",EntConfiguracionNotificacion.class)
				.setParameter("amb", ambiente)
				.setParameter("tipo", alerta)
				.setParameter("grp", grupoPersona).getResultList();
	}

	public List<EntConfiguracionNotificacion> buscarGrupos(String ambiente, ETipoAlertaVersion tipo) {
		Optional<EntAmbiente> posibleAmbiente = servicioAmbiente.getAmbiente(ambiente);
		if (posibleAmbiente.isPresent()){
			return entityManager.createNamedQuery("buscaGrupo.configNotificacion",EntConfiguracionNotificacion.class)
					.setParameter("amb", posibleAmbiente.get())
					.setParameter("tipo", tipo)
					.getResultList();
		}
		return Collections.emptyList();
	}
}
