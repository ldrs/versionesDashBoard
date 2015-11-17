package rd.huma.dashboard.servicios.transaccional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.AEntModelo;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacionCatalogoCambio;
import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionSubModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionSubModulo;
import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntBranchMerge;
import rd.huma.dashboard.model.transaccional.EntBranchRevision;
import rd.huma.dashboard.model.transaccional.EntBranchRevisionJira;
import rd.huma.dashboard.model.transaccional.EntBranchRevisionMerge;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.dato.BranchUltimaRevision;
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

	public List<EntAplicacionModulo> buscaModulo(EntAplicacion aplicacion, String nombre){
		return entityManager.createNamedQuery("buscar.modulo",EntAplicacionModulo.class)
				.setParameter("app", aplicacion)
				.setParameter("nombre", nombre).getResultList();
	}


	public <T extends AEntModelo> T actualizar(T branch) {
		return entityManager.merge(branch);
	}

	public EntBranch actualizar(EntBranch branch) {
		return entityManager.merge(branch);
	}

	public <T extends AEntModelo> T grabar(T revision){
			entityManager.persist(revision);
			return revision;
	}

	public EntBranch grabar(EntBranch branch) {
		int index = branch.getBranch().indexOf(" (from");
		if (index!=-1){
			branch.setBranch(branch.getBranch().substring(0, index-1));
		}

		Optional<EntBranch> posibleRetorno = buscaBranch(branch.getBranch());
		if (posibleRetorno.isPresent()){
			return posibleRetorno.get();
		}

		synchronized(branch.getBranch()){
			posibleRetorno  = buscaBranch(branch.getBranch());
			if (posibleRetorno.isPresent()){
				return posibleRetorno.get();
			}else{
				entityManager.persist(branch);
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

	public List<EntBranchRevision> buscarRevision(long revision){
		return entityManager.createNamedQuery("buscarRevision.branchRevision",EntBranchRevision.class).setParameter("rev", revision).getResultList();
	}

	public List<EntAplicacionSubModulo> getSubModulos(EntAplicacionModulo modulo, String nombre){
		return entityManager.createNamedQuery("buscarSinPadre.subModulo",EntAplicacionSubModulo.class)
				.setParameter("mod", modulo)
				.setParameter("nom", nombre).getResultList();
	}

	public List<EntAplicacionSubModulo> getSubModulos(EntAplicacionModulo modulo,EntAplicacionSubModulo padre, String nombre){
		if (padre == null){
			return getSubModulos(modulo, nombre);
		}

		return entityManager.createNamedQuery("buscarConPadre.subModulo",EntAplicacionSubModulo.class)
				.setParameter("mod", modulo)
				.setParameter("pde", padre)
				.setParameter("nom", nombre).getResultList();
	}

	public List<EntAplicacionConfiguracionModulo> buscaModulos(EntAplicacion aplicacion){
		return entityManager.createNamedQuery("busca.configModulo",EntAplicacionConfiguracionModulo.class).setParameter("app", aplicacion).getResultList();
	}

	public List<EntAplicacionConfiguracionSubModulo> buscaSubModulos(EntAplicacion aplicacion){
		return entityManager.createNamedQuery("busca.configSubModulo",EntAplicacionConfiguracionSubModulo.class).setParameter("app", aplicacion).getResultList();
	}

	public List<EntAplicacionCatalogoCambio> buscaCatalogos(EntAplicacion aplicacion){
		return entityManager.createNamedQuery("busca.catalogoCambio",EntAplicacionCatalogoCambio.class).setParameter("app", aplicacion).getResultList();
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

	public List<EntBranchRevisionMerge> buscaMerge(EntBranchRevision ori, EntBranchRevision des){
		return entityManager.createNamedQuery("buscarOrigenDestino.revisionMerge", EntBranchRevisionMerge.class)
				.setParameter("ori", ori)
				.setParameter("des", des)
				.getResultList();
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

	public List<EntBranchRevisionJira> buscarRevisionJira(EntBranchRevision rev, EntJira jira){
		return entityManager.createNamedQuery("buscarRevision.branchRevJira",EntBranchRevisionJira.class)
				.setParameter("rev", rev)
				.setParameter("jira", jira)
				.getResultList();
	}

	public List<BranchUltimaRevision> buscaBranchAunFaltanRevision(EntAplicacion aplicacion){
		@SuppressWarnings("unchecked")
		List<Object[]> branches =  entityManager.createNativeQuery("select B.branch,B.revisionUltima from Branch B where B.revisionOrigen>0 and B.aplicacion_id = :app and not exists (select 1 from BRANCH_REVISION R where R.branch = B.branch and R.revision<=B.revisionUltima)")
				.setParameter("app", aplicacion.getId())
				.getResultList();

		List<BranchUltimaRevision> retorno = new ArrayList<>();
		for (Object[] dato : branches) {
			retorno.add(new BranchUltimaRevision(Long.valueOf(dato[1].toString()),dato[0].toString()));
		}
		return retorno;

	}

}