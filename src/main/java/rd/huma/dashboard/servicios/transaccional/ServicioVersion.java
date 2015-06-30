package rd.huma.dashboard.servicios.transaccional;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import rd.huma.dashboard.model.EntJira;
import rd.huma.dashboard.model.EntPersona;
import rd.huma.dashboard.model.EntTicketSysAid;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.model.EntVersionDuenos;
import rd.huma.dashboard.model.EntVersionJira;
import rd.huma.dashboard.model.EntVersionTicket;

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

		crearVersionDueno(autor, version);

		return version;
	}


	public EntVersion actualizarVersion(String idVersion, String comentario){
		EntVersion version = entityManager.find(EntVersion.class, idVersion);
		version.setComentario(comentario);
		entityManager.persist(version);
		return version;
	}



	public void crearVersionJira(EntJira jira, EntVersion version) {
		EntVersionJira versionJira = new EntVersionJira();
		versionJira.setJira(jira);
		versionJira.setVersion(version);
		entityManager.persist(versionJira);
	}

	public void crearVersionJira(EntTicketSysAid ticketSysaid, EntVersion version) {
		EntVersionTicket versionTicket = new EntVersionTicket();
		versionTicket.setTicketSysAid(ticketSysaid);
		versionTicket.setVersion(version);
		entityManager.persist(versionTicket);
	}

	public void crearVersionDueno(String persona, EntVersion version) {
		EntVersionDuenos versionDueno = new EntVersionDuenos();
		versionDueno.setVersion(version);
		versionDueno.setDueno(servicioPersona.buscaOCreaPersona(persona));

		entityManager.persist(versionDueno);
	}



	public static ServicioVersion getInstanciaTransaccional(){

		return CDI.current().select(ServicioVersion.class, ServicioVersion.class.getAnnotations()).get();
	}

}