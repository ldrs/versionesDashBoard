package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionDuenos;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedades;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;

@Servicio
@Stateless
public class ServicioVersion {

	@Inject
	private EntityManager entityManager;

	@Inject
	private @Servicio ServicioPersona servicioPersona;

	@Inject
	private @Servicio ServicioTicketSysaid servicioTicketSysaid;
	
	public List<EntVersion> buscaVersiones(Set<EEstadoVersion> estados){
		return entityManager.createNamedQuery("buscarPorEstado.version",EntVersion.class).setParameter("est", estados).getResultList();
	}

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

	public EntVersion actualizarEstado(EEstadoVersion esperandoFila,		EntVersion versionTmp) {
		EntVersion version = entityManager.find(EntVersion.class, versionTmp.getId());
		version.setEstado(esperandoFila);
		entityManager.persist(version);
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

	public void crearVersionTicketSysAid(String numero, EntVersion version) {
		EntVersionTicket versionTicket = new EntVersionTicket();
		versionTicket.setTicketSysAid(servicioTicketSysaid.encuentraOSalva(numero));
		versionTicket.setVersion(version);
		entityManager.persist(versionTicket);
	}

	public void crearVersionPropiedad(String nombre, String valor, EntVersion version) {
		EntVersionPropiedades versionTicket = new EntVersionPropiedades();
		versionTicket.setPropiedad(nombre);
		versionTicket.setValor(valor);
		versionTicket.setVersion(version);
		entityManager.persist(versionTicket);
	}

	public void crearVersionDueno(String persona, EntVersion version) {
		EntVersionDuenos versionDueno = new EntVersionDuenos();
		versionDueno.setVersion(version);
		versionDueno.setDueno(servicioPersona.buscaOCreaPersona(persona));

		entityManager.persist(versionDueno);
	}


	public List<EntVersion> buscaUltimaVersiones() {
		return entityManager.createNamedQuery("buscar.versionTodas",EntVersion.class).setMaxResults(50).getResultList();
	}

	public List<EntVersionJira> buscaJiras(EntVersion version){
		return entityManager.createNamedQuery("buscar.versionJiraPorVersion",EntVersionJira.class)
				.setParameter("ver", version)
				.getResultList();
	}

	public List<EntVersionTicket> buscaTickets(EntVersion version){
		return entityManager.createNamedQuery("buscar.versionTicketPorVersion",EntVersionTicket.class)
				.setParameter("ver", version)
				.getResultList();
	}

	public List<EntVersionPropiedades> buscaPropiedades(EntVersion version){
		return entityManager.createNamedQuery("buscar.versionPropiedadesPorVersion",EntVersionPropiedades.class)
				.setParameter("ver", version)
				.getResultList();
	}

	public List<EntVersionDuenos> buscaDuenos(EntVersion version){
		return entityManager.createNamedQuery("buscar.versionDuenosPorVersion",EntVersionDuenos.class)
				.setParameter("ver", version)
				.getResultList();
	}


	public List<EntVersionDuenos> buscaDuenos(){
		return entityManager.createNamedQuery("buscar.versionDuenos",EntVersionDuenos.class).getResultList();
	}

	public static ServicioVersion getInstanciaTransaccional(){
		Servicio servicio = ServicioVersion.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioVersion.class, servicio).get();
	}




}