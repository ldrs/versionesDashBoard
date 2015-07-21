package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatos;
import rd.huma.dashboard.model.transaccional.EntServidor;


@Stateless
@Servicio
public class ServicioServidor {

	@Inject
	private EntityManager entityManager;

	public static ServicioServidor getInstanciaTransaccional(){
		Servicio servicio = ServicioServidor.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioServidor.class, servicio).get();
	}

	public EntServidor nuevoServidor(EntAmbienteAplicacion ambiente, EntRepositorioDatos baseDatos, String nombre, String rutaEntrada){
		EntServidor servidor = new EntServidor();
		servidor.setAmbiente(ambiente);
		servidor.setBaseDatos(baseDatos);
		servidor.setNombre(nombre);
		servidor.setRutaEntrada(rutaEntrada);
		entityManager.persist(servidor);
		return servidor;
	}

	public List<EntServidor> getServidoresAmbiente(String id) {
		return entityManager.createNamedQuery("buscar.servidor",EntServidor.class).setParameter("amb", id).getResultList();
	}

}