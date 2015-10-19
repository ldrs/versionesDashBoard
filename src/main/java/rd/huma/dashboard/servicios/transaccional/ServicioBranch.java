package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
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

	public EntBranch procesarOrigen(SVNOrigenBranch origen, EntAplicacion aplicacion, String branchOrigen){
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
		return branch;
	}


	public EntBranch actualizar(EntBranch branch) {
		return entityManager.merge(branch);
	}

	public EntBranch grabar(EntBranch branch) {
		int index = branch.getBranch().indexOf(" (from");
		if (index!=-1){
			branch.setBranch(branch.getBranch().substring(0, index-1));
		}

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


	public List<EntBranch> buscaBranchesQueDebenTenerMerge() {
		return entityManager.createNamedQuery("buscaBranchesSinMerge.branch",EntBranch.class).getResultList();
	}


	public EntBranchMerge buscarOCrear(EntBranch branch, String mergedBranch) {
		Optional<EntBranchMerge> resultado = entityManager.createNamedQuery("buscaOridenYDestino.branchMerge",EntBranchMerge.class)
		.setParameter("origen", mergedBranch)
		.setParameter("branch", branch.getBranch()).getResultList().stream().findFirst();
		if (resultado.isPresent()){
			return resultado.get();
		}
		Optional<EntBranch> posibleBranch = buscaBranch(mergedBranch);
		EntBranch branchOrigen;
		if (posibleBranch.isPresent()){
			branchOrigen = posibleBranch.get();
		}else{
			branchOrigen = new EntBranch();
			branchOrigen.setAplicacion(branch.getAplicacion());
			branchOrigen.setBranch(mergedBranch);

			branchOrigen = grabar(branchOrigen);
		}

		 EntBranchMerge branchMerge = new EntBranchMerge();
		 branchMerge.setBranchDestino(branch);
		 branchMerge.setBranchOrigen(branchOrigen);
		 grabarMerge(branchMerge);
		 return branchMerge;

	}


	public List<EntBranch> buscaBranchesOrigenNull() {
		return entityManager.createNamedQuery("buscaOrigenNull.branch",EntBranch.class).getResultList();
	}


	public List<EntBranchMerge> buscaOrigenBranch(EntBranch branch) {
		return entityManager.createNamedQuery("buscaPorOrigenObj.branchMerge",EntBranchMerge.class).setParameter("branch", branch).getResultList();
	}

	public List<EntBranchMerge> buscaOrigenBranch(String branch) {
		return entityManager.createNamedQuery("buscaPorOrigen.branchMerge",EntBranchMerge.class).setParameter("branch", branch).getResultList();
	}

	public List<EntBranchMerge> buscaDestinoBranch(EntBranch branch) {
		return entityManager.createNamedQuery("buscaPorDestino.branchMerge",EntBranchMerge.class).setParameter("branch", branch).getResultList();
	}
}