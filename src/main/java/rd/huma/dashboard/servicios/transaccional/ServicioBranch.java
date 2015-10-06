package rd.huma.dashboard.servicios.transaccional;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntBranchMerge;
import rd.huma.dashboard.servicios.integracion.svn.util.SVNOrigenBranch;


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

	public void procesarOrigen(SVNOrigenBranch origen, EntAplicacion aplicacion, String branchOrigen){
		Optional<EntBranch> original = buscaBranch(branchOrigen);
		EntBranch branch;
		if (original.isPresent()){
			branch=original.get();
		}else{
			branch = new EntBranch();
			branch.setAplicacion(aplicacion);
			branch.setBranch(branchOrigen);
			branch.setOrigen(origen.getOrigenBranch());
			branch.setRevisionOrigen(origen.getRevision());
			branch = grabar(branch);
		}


		for (String branchesOrigen : origen.getMergeBranches()){
			 Optional<EntBranch> encontrado = buscaBranch(branchesOrigen);
			 EntBranch branchEncontrado;
			 if (encontrado.isPresent()){
				 branchEncontrado = encontrado.get();

			 }else{
				 branchEncontrado = new EntBranch();
				 branchEncontrado.setAplicacion(aplicacion);
				 branchEncontrado.setBranch(branchesOrigen);
				 branchEncontrado = grabar(branchEncontrado);
			 }
			 EntBranchMerge branchMerge = new EntBranchMerge();
			 branchMerge.setBranchDestino(branch);
			 branchMerge.setBranchOrigen(branchEncontrado);
			 grabarMerge(branchMerge);
		}
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


	public void grabarMerge(EntBranchMerge branchMerge) {
		entityManager.persist(branchMerge);
	}
}