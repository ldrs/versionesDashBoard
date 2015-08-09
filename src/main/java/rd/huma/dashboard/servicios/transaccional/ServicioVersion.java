package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedad;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
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

	public EntVersion crearVersion(String numeroVersion, String autorSVN, String svnOrigen, String branchOrigen, String revisionSVN) {
		synchronized(svnOrigen+numeroVersion){
			Optional<EntVersion> opcional = entityManager.createNamedQuery("buscarPorNumeroOrigen.version",EntVersion.class).setParameter("num", numeroVersion).setParameter("sOri", svnOrigen).getResultList().stream().findFirst();
			if (opcional.isPresent()){
				return null;
			}
			EntPersona autor = servicioPersona.buscaOCreaPersona(autorSVN);

			EntVersion version = new EntVersion();
			version.setNumero(numeroVersion);
			version.setAutor(autor);
			version.setBranchOrigen(branchOrigen);
			version.setRevisionSVN(revisionSVN);
			version.setSvnOrigen(svnOrigen);
			entityManager.persist(version);

			crearVersionParticipante(autorSVN, version);

			return version;
		}

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

	public boolean crearVersionTicketSysAid(String numero, EntVersion version) {
		EntTicketSysAid ticketSysAid = servicioTicketSysaid.encuentraOSalva(numero);
		if (ticketSysAid == null){
			return false;
		}

		EntVersionTicket versionTicket = new EntVersionTicket();
		versionTicket.setTicketSysAid(ticketSysAid);
		versionTicket.setVersion(version);
		entityManager.persist(versionTicket);
		return true;
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

	public void crearVersionParticipante(String persona, EntVersion version) {
		crearVersionParticipante(servicioPersona.buscaOCreaPersona(persona), version);
	}

	public void crearVersionParticipante(EntPersona persona, EntVersion version) {
		EntVersionParticipante versionDueno = new EntVersionParticipante();
		versionDueno.setVersion(version);
		versionDueno.setParticipante(persona);

		entityManager.persist(versionDueno);
	}

	public void crearVersionScript(EntVersion version, EntJira jira, String script, ETipoScript tipoScript){
		EntVersionScript versionScript = new EntVersionScript();
		versionScript.setJira(jira);
		versionScript.setTipoScript(tipoScript);
		versionScript.setUrlScript(script);
		versionScript.setVersion(version);
		entityManager.persist(versionScript);
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

	public long contarScriptVersion(EntVersion version){
		return entityManager.createNamedQuery("contar.versionScripts",Long.class).setParameter("ver", version).getSingleResult();
	}

	public void crearVersionReporte(EntVersionReporte versionReporte) {
		entityManager.persist(versionReporte);
	}

	public long contarReporteVersion(EntVersion version){
		return entityManager.createNamedQuery("contar.versionReportes",Long.class).setParameter("ver", version).getSingleResult();
	}
	
	public List<EntVersionScript> getScriptAntesDespuesEjecucion(EntVersion version, ETipoScript tipo) {
		return entityManager.createNamedQuery("buscarAntesDespues.versionScripts",EntVersionScript.class).setParameter("ver", version).setParameter("tipo", tipo).getResultList();
	}

	public List<EntVersionScript> getScriptAntesEjecucion(EntVersion version) {
		return entityManager.createNamedQuery("buscarAntesDespues.versionScripts",EntVersionScript.class).setParameter("ver", version).setParameter("tipo", ETipoScript.ANTES_SUBIDA).getResultList();
	}

	public List<EntVersionScript> getScriptDespuesEjecucion(EntVersion version) {
		return entityManager.createNamedQuery("buscarAntesDespues.versionScripts",EntVersionScript.class).setParameter("ver", version).setParameter("tipo", ETipoScript.DESPUES_SUBIDA).getResultList();
	}

	public List<EntVersionReporte> getReportesVersion(EntVersion version) {
		return entityManager.createNamedQuery("buscar.versionReportes",EntVersionReporte.class).setParameter("ver", version).getResultList();
	}
}