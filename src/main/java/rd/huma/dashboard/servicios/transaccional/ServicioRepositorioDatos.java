package rd.huma.dashboard.servicios.transaccional;

import java.time.LocalDate;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntRepositorioDatos;

@Stateless
@Servicio
public class ServicioRepositorioDatos {

	
	@Inject
	private EntityManager entityManager;

	public String actualizar(String nombreServicio, String schema) {
		Optional<EntRepositorioDatos> opcional = entityManager.createNamedQuery("buscar.repositorioDatos",EntRepositorioDatos.class)
				.setParameter("sc", schema)
				.setParameter("serv", nombreServicio)
				.getResultList().stream().findFirst();
		if (opcional.isPresent()){
			return actualizar(opcional.get()).getId();
		}else{
			return nuevoRepositorio(nombreServicio, schema).getId();
		}
	}
	
	private EntRepositorioDatos nuevoRepositorio(String nombreServicio,String schema) {
		EntRepositorioDatos repositorioDatos = new EntRepositorioDatos();
		repositorioDatos.setSchema(schema);
		repositorioDatos.setServicio(nombreServicio);
		entityManager.persist(repositorioDatos);
		return repositorioDatos;
	}

	private EntRepositorioDatos actualizar(EntRepositorioDatos repositorioDatos){
		repositorioDatos.setUltimaActualizacion(LocalDate.now());
		entityManager.merge(repositorioDatos);
		return repositorioDatos;
	}

}