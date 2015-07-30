package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedad;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion.EjecutorSeleccionFila;

@Servicio
@Stateless
public class ServicioVersion {

	@Inject
	private EntityManager entityManager;

	@Inject
	private @Servicio ServicioPersona servicioPersona;

	@Inject
	private @Servicio ServicioTicketSysaid servicioTicketSysaid;

	@Inject
	private MonitorEjecutor monitorEjecutor;

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

	public EntVersion merge(EntVersion versionTmp){
		return entityManager.merge(versionTmp);
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
		EntVersionPropiedad versionTicket = new EntVersionPropiedad();
		versionTicket.setPropiedad(nombre);
		versionTicket.setValor(valor);
		versionTicket.setVersion(version);
		entityManager.persist(versionTicket);
	}

	public void crearVersionModulo(EntVersionModulo versionModulo) {
		entityManager.persist(versionModulo);
	}

	public void crearVersionDueno(String persona, EntVersion version) {
		EntVersionParticipante versionDueno = new EntVersionParticipante();
		versionDueno.setVersion(version);
		versionDueno.setParticipante(servicioPersona.buscaOCreaPersona(persona));

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

	public List<EntVersionPropiedad> buscaPropiedades(EntVersion version){
		return entityManager.createNamedQuery("buscar.propiedadesPorVersion",EntVersionPropiedad.class)
				.setParameter("ver", version)
				.getResultList();
	}

	public List<EntVersionParticipante> buscaParticipantes(EntVersion version){
		return entityManager.createNamedQuery("buscar.versionParticipantesPorVersion",EntVersionParticipante.class)
				.setParameter("ver", version)
				.getResultList();
	}


	public List<EntVersionParticipante> buscaDuenos(){
		return entityManager.createNamedQuery("buscar.versionParticipantes",EntVersionParticipante.class).getResultList();
	}

	public static ServicioVersion getInstanciaTransaccional(){
		Servicio servicio = ServicioVersion.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioVersion.class, servicio).get();
	}

	public List<EntVersionModulo> buscaModulos(EntVersion version) {
		return entityManager.createNamedQuery("buscar.versionModulo",EntVersionModulo.class).setParameter("ver", version).getResultList();

	}

	public void crearAlerta(EntVersionAlerta alerta) {
		if (alerta == null){
			return;
		}
		entityManager.persist(alerta);
	}

	public void gestionarFila(EntVersion version) {
		monitorEjecutor.ejecutarAsync(new EjecutorSeleccionFila(version));

	}
}