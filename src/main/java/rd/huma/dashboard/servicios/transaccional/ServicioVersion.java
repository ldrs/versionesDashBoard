package rd.huma.dashboard.servicios.transaccional;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionAlertaHistorica;
import rd.huma.dashboard.model.transaccional.EntVersionCambioObjectoSql;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionLog;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.model.transaccional.EntVersionPropiedad;
import rd.huma.dashboard.model.transaccional.EntVersionReporte;
import rd.huma.dashboard.model.transaccional.EntVersionReporteJira;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.model.transaccional.EntVersionScriptAdvertencia;
import rd.huma.dashboard.model.transaccional.EntVersionScriptJira;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.fila.seleccion.EjecutorSeleccionFila;
import rd.huma.dashboard.servicios.integracion.svn.util.ServicioUltimaRevisionSVN;
import rd.huma.dashboard.servicios.integracion.svn.util.UltimaRevision;

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
	private @Servicio ServicioJira servicioJira;

	@Inject
	private MonitorEjecutor monitorEjecutor;

	public static Predicate<EEstadoVersion> estadosNegativos(){
		return e -> !e.activo() && e!=EEstadoVersion.NEXUS_ELIMINADO && e!=EEstadoVersion.REMPLAZADA;
	}

	public Stream<EntVersion> versionesConError(){
		return buscaVersionesRecientes(Arrays.stream(EEstadoVersion.values()).filter(estadosNegativos())
				.collect(Collectors.toSet())).stream().filter(v -> v.getFechaRegistro().isAfter(Instant.now().minus(Duration.ofDays(3))) );
	}

	public List<EntVersion> buscaVersiones(Set<EEstadoVersion> estados){
		return entityManager.createNamedQuery("buscarPorEstado.version",EntVersion.class).setParameter("est", estados).getResultList();
	}

	public List<EntVersion> buscaVersionesRecientes(Set<EEstadoVersion> estados){
		return entityManager.createNamedQuery("buscarPorEstadoReciente.version",EntVersion.class)
				.setParameter("est", estados)
				.setParameter("fecha",Timestamp.from(Instant.now().minus(3L, ChronoUnit.DAYS)))
				.getResultList();
	}

	public List<EntVersion> buscaVersionesPorEstadoSysAid(List<Integer> estadosSysaid){
		return entityManager.createNamedQuery("buscarPorEstadoSysAid.version",EntVersion.class)
				.setParameter("est", estadosSysaid)
				.setParameter("estVersion", EEstadoVersion.NEXUS_ELIMINADO)
				.setParameter("fechaExpira",Timestamp.from(Instant.now().minus(5L, ChronoUnit.DAYS)))
				.setParameter("fchReciente",Timestamp.from(Instant.now().minus(2L, ChronoUnit.DAYS)))

				.getResultList();
	}


	public List<String> buscaBranchVersionesDuplicadas(Set<EEstadoVersion> estados){
		return entityManager.createNamedQuery("buscarPorBranchDuplicado.version",String.class).setParameter("est", estados).getResultList();
	}


	public EntVersion crearVersion(String numeroVersion, String autorSVN, String svnOrigen, String branchOrigen, String revisionSVN, String inicioJob) {
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
			version.setInicioJob(inicioJob);
			entityManager.persist(version);

			crearVersionParticipante(autorSVN, version);

			return version;
		}

	}

	public EntVersion merge(EntVersion versionTmp){
		return entityManager.merge(versionTmp);
	}

	public EntVersion actualizarEstado(EEstadoVersion estado, EntVersion version) {
		EntVersion versionInt = entityManager.find(EntVersion.class, version.getId());
		versionInt.setEstado(estado);
		entityManager.persist(versionInt);
		return versionInt;
	}

	public EntVersion actualizarVersion(String idVersion, String comentario){
		EntVersion version = entityManager.find(EntVersion.class, idVersion);
		version.setComentario(comentario);
		entityManager.persist(version);
		return version;
	}

	public EntVersionJira crearVersionJira(EntJira jira, EntVersion version) {
		EntVersionJira versionJira = new EntVersionJira();
		versionJira.setJira(jira);
		versionJira.setVersion(version);
		entityManager.persist(versionJira);
		return versionJira;
	}

	public boolean crearVersionTicketSysAid(long numero, EntVersion version) {
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
		synchronized(version.getNumero()){

			if (entityManager.createNamedQuery("buscarVersionParticipante.versionParticipantes",EntVersionParticipante.class)
					.setParameter("ver", version)
					.setParameter("par", persona).getResultList().isEmpty()){

				EntVersionParticipante versionDueno = new EntVersionParticipante();
				versionDueno.setVersion(version);
				versionDueno.setParticipante(persona);

				entityManager.persist(versionDueno);
			}
		}


	}

	public EntVersionScript crearVersionScript(EntVersion version, String script, ETipoScript tipoScript){
		EntVersionScript versionScript = new EntVersionScript();
		versionScript.setTipoScript(tipoScript);
		versionScript.setUrlScript(script);
		versionScript.setVersion(version);
		if (script!=null){
			versionScript.setNombre(script.substring(script.lastIndexOf('/')+1));
		}
		entityManager.persist(versionScript);
		return versionScript;
	}

	public void crearVersionLog(EntVersionLog versionLog){
		entityManager.persist(versionLog);
	}

	public EntVersionScript actualizarVersionScript(EntVersionScript versionScript){
		return entityManager.merge(versionScript);
	}

	public List<EntVersion> buscaPorNumero(String numero){
		return entityManager.createNamedQuery("buscarPorNumero.version",EntVersion.class).setParameter("num", numero).getResultList();
	}


	public List<EntVersion> buscaUltimaVersiones() {
		return entityManager.createNamedQuery("buscar.versionTodas",EntVersion.class).setMaxResults(50).getResultList();
	}

	public List<EntVersionJira> buscaJiras(EntVersion version){
		return entityManager.createNamedQuery("buscarPorVersion.versionJira",EntVersionJira.class)
				.setParameter("ver", version)
				.getResultList();
	}


	public List<EntVersionJira> buscaJiras(EntJira jira){
		return entityManager.createNamedQuery("buscarPorJira.versionJira",EntVersionJira.class)
				.setParameter("jira", jira)
				.setParameter("est" , Arrays.stream(EEstadoVersion.values()).filter(e -> e.activo()).collect(Collectors.toSet()))
				.getResultList();
	}


	public List<EntVersionTicket> buscaTickets(EntVersion version){
		return entityManager.createNamedQuery("buscar.versionTicketPorVersion",EntVersionTicket.class)
				.setParameter("ver", version)
				.getResultList();
	}

	public List<Long> buscaNumeroTicketsSegunBranch(String branch){
		return entityManager.createNamedQuery("buscarPorBranch.versionTicket",Long.class).setParameter("branch", branch).getResultList();
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

	public List<EntVersionReporte> buscaReportesVersion(EntVersion version) {
		return entityManager.createNamedQuery("buscar.versionReportes",EntVersionReporte.class).setParameter("ver", version).getResultList();
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

	public void gestionarFila(EntFilaDespliegueVersion fila) {
		monitorEjecutor.ejecutarAsync(new EjecutorSeleccionFila(fila));
	}

	public void gestionarFila(EjecutorSeleccionFila  ejecutorSeleccionFila) {
		monitorEjecutor.ejecutarAsync(ejecutorSeleccionFila);
	}

	public void ejecutarJob(AEjecutor ejecutor){
		monitorEjecutor.ejecutarAsync(ejecutor);
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

	public List<EntVersionScript> buscaScript(EntVersion version) {
		return entityManager.createNamedQuery("buscar.versionScripts",EntVersionScript.class).setParameter("ver", version).getResultList();
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


	public List<EntVersion> getVersionesQueContienenAlertas() {
		return entityManager.createNamedQuery("versiones.alerta",EntVersion.class).getResultList();
	}

	public List<EntVersionAlerta> buscaAlerta(EntVersion version) {
		return entityManager.createNamedQuery("buscaPorVersion.alerta",EntVersionAlerta.class).setParameter("ver", version).getResultList();
	}

	public List<EntPersona> buscaPersonasPorAlertar(EntVersionAlerta alerta){
		return entityManager.createNamedQuery("buscaPorPersonasPorTipo.alerta",EntPersona.class)
				.setParameter("alt", alerta).getResultList();
	}

	public List<EntVersionAlertaHistorica> moverHistorico(List<EntVersionAlerta> alertas){
		List<EntVersionAlertaHistorica> historia = new ArrayList<>();
		for (EntVersionAlerta alerta : alertas) {
			historia.add(moverHistorico(alerta));
		}
		return historia;
	}

	public EntVersionAlertaHistorica moverHistorico(EntVersionAlerta alerta) {
		EntVersionAlertaHistorica h = new EntVersionAlertaHistorica();
		h.setAlerta(alerta.getAlerta());
		h.setMensaje(alerta.getMensaje());
		h.setPathFile(alerta.getPathFile());
		h.setVersion(alerta.getVersion());
		h.setAmbiente(alerta.getAmbiente());
		entityManager.persist(h);
		entityManager.remove(entityManager.find(EntVersionAlerta.class, alerta.getId()));
		return h;
	}

	public void eliminarVersion(EntVersionReporte versionReporte) {
		entityManager.createQuery("DELETE FROM EntVersionReporteJira E where E.reporte = :rep").setParameter("rep", versionReporte).executeUpdate();

		entityManager.remove(entityManager.find(EntVersionReporte.class, versionReporte.getId()));
	}

	public void eliminarScript(EntVersionScript versionScript) {
		entityManager.remove(entityManager.find(EntVersionScript.class, versionScript.getId()));
	}

	public void crearCambioObjectoSQL(	EntVersionCambioObjectoSql cambioObjectoSql) {
		entityManager.persist(  cambioObjectoSql);
	}

	public int eliminarCambiosObjectoCambio(EntVersion version) {
		return entityManager.createQuery("DELETE FROM EntVersionCambioObjectoSql E where E.version = :ver").setParameter("ver", version).executeUpdate();
	}

	public int eliminarCambiosObjectoCambio(EntVersionScript versionScript) {
		return entityManager.createQuery("DELETE FROM EntVersionCambioObjectoSql E where E.script = :src").setParameter("src", versionScript).executeUpdate();
	}

	public EntVersion buscaPorId(String id) {
		return entityManager.find(EntVersion.class, id);
	}

	public List<EntVersionCambioObjectoSql> buscaCambioModelos(String id) {
		return entityManager.createNamedQuery("buscarPorVersionId.cambioSQL",EntVersionCambioObjectoSql.class).setParameter("id", id).getResultList();
	}

	public List<EntVersion> buscaPorBranch(String branch) {
		return entityManager.createNamedQuery("buscarPorBranch.version",EntVersion.class).setParameter("branch", branch).getResultList();
	}

	public EntVersionReporteJira crearReporteJira(EntVersionReporteJira reporteJira) {
		entityManager.persist(reporteJira);
		return reporteJira;
	}

	public List<EntVersionReporteJira> buscaJiraReporte(EntVersionReporte versionReporte) {
		return entityManager.createNamedQuery("buscaPorReporte",EntVersionReporteJira.class).setParameter("rep", versionReporte).getResultList();
	}

	public List<EntVersionScriptJira> buscaJiraScript(EntVersionScript versionScript) {
		return entityManager.createNamedQuery("buscaPorReporte.versionScriptJira",EntVersionScriptJira.class).setParameter("src", versionScript).getResultList();
	}

	public EntVersionScriptJira crearScriptJira(EntVersionScriptJira jiraScript) {
		entityManager.persist(jiraScript);
		return jiraScript;
	}


	public EntVersionReporte crearVersionReporteYJiras(String urlReporte, EntVersion version, List<EntVersionReporteJira> reportesJira){
		UltimaRevision ultimaRevision = new ServicioUltimaRevisionSVN(urlReporte).revision();
		EntVersionReporte versionReporte = new EntVersionReporte();
		versionReporte.setReporte(urlReporte);
		versionReporte.setVersion(version);
		versionReporte.setAutor(servicioPersona.buscaOCreaPersona(ultimaRevision.getUsuarioSVN()));
		versionReporte.setRevision(ultimaRevision.getNumeroRevision());

		crearVersionReporte(versionReporte);

		for (EntVersionReporteJira reporteJira: reportesJira){
			reporteJira.setReporte(versionReporte);
			reporteJira.setJira(servicioJira.encuentra(reporteJira.getJira().getNumero()).get());
			crearReporteJira(reporteJira);
		}
		return versionReporte;
	}

	public List<EntVersion> buscarVersionPorNumeroObranchOTicket(String query) {
		return entityManager.createNamedQuery("buscar.versionParecida",EntVersion.class).setParameter("query", "%"+query +"%").getResultList();
	}

	public Set<EntTicketSysAid> actualizaEstadosSysaid(EntVersion version) {
		Set<EntTicketSysAid> tickets = new HashSet<>();
		for (EntTicketSysAid entTicketSysAid : buscaTickets(version).stream().map(EntVersionTicket::getTicketSysAid).collect(Collectors.toSet())) {
			 tickets.add(servicioTicketSysaid.refrescarEstado(entTicketSysAid));
		}
		return tickets;
	}

	public void eliminaModulos(EntVersion version) {
		buscaModulos(version).stream().forEach(e-> entityManager.remove(entityManager.find(e.getClass(), e.getId())));
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getVersionesPorMes() {
		return entityManager.createNamedQuery("metricaAgrupadaYear.version").getResultList();
	}

	public void crearAdvertencia(EntVersionScriptAdvertencia advertencia) {
		entityManager.persist(advertencia);
	}
}