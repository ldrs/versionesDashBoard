package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntBranch;


@Stateless
@Servicio
public class ServicioBranch {

	@Inject
	private EntityManager entityManager;


	public Optional<EntBranch> buscaBranch(String branch){
		return entityManager.createNamedQuery("busca.branch",EntBranch.class).setParameter("branch", branch).getResultList().stream().findFirst();
	}


	public static ServicioBranch getInstanciaTransaccional(){
		Servicio servicio = ServicioBranch.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioBranch.class, servicio).get();
	}


	public EntBranch grabar(EntBranch branch) {
		Optional<EntBranch> posibleRetorno = buscaBranch(branch.getBranch());
		if (posibleRetorno.isPresent()){
			return posibleRetorno.get();
		}else{
			synchronized(branch.getBranch()){
				posibleRetorno  = buscaBranch(branch.getBranch());
				if (posibleRetorno.isPresent()){
					return posibleRetorno.get();
				}else{
					entityManager.persist(branch);
				}
			}
		}
		return branch;
	}
}