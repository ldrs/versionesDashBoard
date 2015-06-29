package rd.huma.dashboard.servicios.transaccional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.model.EntVersionDuenos;

@Transactional
@Stateless
public class ServicioVersion {

	@Inject
	private EntityManager entityManager;

	@Inject
	private ServicioPersona servicioPersona;



	public EntVersion crearVersion(String numeroVersion, String autor, String svnOrigen, String branchOrigen, String revisionSVN) {
		EntVersion version = new EntVersion();
		version.setNumero(numeroVersion);
		version.setAutor(autor);
		version.setBranchOrigen(branchOrigen);
		version.setRevisionSVN(revisionSVN);
		version.setSvnOrigen(svnOrigen);
		entityManager.persist(version);

		EntVersionDuenos versionDueno = new EntVersionDuenos();
		versionDueno.setVersion(version);
		versionDueno.setDueno(servicioPersona.buscaOCreaPersona(autor));

		entityManager.persist(versionDueno);


		return version;
	}
	
	
	public EntVersion actualizarVersion(String idVersion, String comentario){
		EntVersion version = entityManager.find(EntVersion.class, idVersion);
		version.setComentario(comentario);
		entityManager.persist(version);
		return version;
	}
	
	public static ServicioVersion getInstanciaTransaccional(){
		
		return CDI.current().select(ServicioVersion.class, ServicioVersion.class.getAnnotations()).get();
	}
}