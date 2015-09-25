package rd.huma.dashboard.servicios.transaccional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.fila.FilaBranch;
import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersionDueno;
import rd.huma.dashboard.model.transaccional.EntGrupoPersona;
import rd.huma.dashboard.model.transaccional.EntGrupoPersonaDetalle;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.util.UtilFecha;

@Servicio
@Stateless
public class ServicioFila {

	private @Servicio @Inject ServicioGrupo servicioGrupo;

	private @Servicio @Inject ServicioVersion servicioVersion;

	private @Servicio @Inject ServicioJobDespliegueVersion servicioJobDespliegueVersion;

	Function<Object[], FilaBranch> toFilaBranch = new Function<Object[], FilaBranch>() {
	    public FilaBranch apply(Object[] t) {
	        return new FilaBranch((EntFilaDespliegue) t[0], (String)t[1]);
	    }
	};

	@Inject
	private EntityManager entityManager;

	public List<EntFilaDespliegueVersion> getFilas(EntFilaDespliegue fila){
		return entityManager.createNamedQuery("buscarPorFila.fila",EntFilaDespliegueVersion.class).setParameter("fil", fila) .getResultList();
	}

	public List<EntFilaDespliegueVersion> getFilas(EntVersion version){
		return entityManager.createNamedQuery("buscarPorVersion.fila",EntFilaDespliegueVersion.class).setParameter("ver", version.getId()) .getResultList();
	}


	public List<EntFilaDespliegueVersion> getFilas(FilaBranch filaBranch){
		return entityManager.createNamedQuery("buscarPorFilaBranch.fila",EntFilaDespliegueVersion.class)
						.setParameter("fil", filaBranch.getFila())
						.setParameter("bra", filaBranch.getBranch())
						.getResultList();
	}

	public EntFilaDespliegue getFilaPorAmbienteAplicacion(String id){
		return entityManager.createNamedQuery("buscar.filaDeploment",EntFilaDespliegue.class).setParameter("amb", id).getSingleResult();
	}

	public List<EntFilaDespliegueVersion> getFilasPorEstadoVersion(Set<EEstadoVersion> estados){
		return entityManager.createNamedQuery("buscarPorVersionEstado.fila",EntFilaDespliegueVersion.class).setParameter("est", estados).getResultList();
	}

	public List<EntFilaDespliegue> getFilasDeploment(){
		return entityManager.createNamedQuery("todos.filaDeploment",EntFilaDespliegue.class).getResultList();
	}

	public List<EntFilaDespliegueVersion> getFilasPorAmbienteAplicacion(String id){
		return entityManager.createNamedQuery("buscarPorAmbiente.fila",EntFilaDespliegueVersion.class).setParameter("amb", id).getResultList();
	}

	public static ServicioFila getInstanciaTransaccional(){
		Servicio servicio = ServicioFila.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioFila.class, servicio).get();
	}

	public void salirFila(EntFilaDespliegueVersion filaVersion) {
		entityManager.remove(filaVersion);
	}

	public List<FilaBranch> getFilasPorBranchDuplicado(Set<EEstadoVersion> estados) {
		List<FilaBranch> filas = new ArrayList<FilaBranch>();
		entityManager.createNamedQuery("buscarPorDuplicacion.fila",Object[].class).setParameter("est", estados).getResultList()

		.stream().forEach(o-> filas.add(toFilaBranch.apply(o)));
		return filas;
	}

	public void ordenarFila(EntFilaDespliegue filaHeader){
		AtomicInteger entero = new AtomicInteger(1);
		getFilas(filaHeader).forEach(f -> procesarOrden(f, entero.getAndIncrement()));
	}

	private void procesarOrden(EntFilaDespliegueVersion fila, int prioridad){
		EntFilaDespliegueVersion f = entityManager.getReference(EntFilaDespliegueVersion.class, fila.getId());
		f.setPrioridad(prioridad);
		entityManager.persist(f);
	}

	public void crearVersionFila(EntVersion version,EntFilaDespliegue fila) {
		synchronized (fila.getId()) {
			Integer prioridad = entityManager.createNamedQuery("maxVersion.fila", Integer.class).setParameter("fil", fila).getSingleResult();
			if (prioridad == null){
				prioridad = 1;
			}

			EntFilaDespliegueVersion deployementVersion = new EntFilaDespliegueVersion();
			deployementVersion.setVersion(version);
			deployementVersion.setFila(fila);
			deployementVersion.setPrioridad(prioridad);

			entityManager.persist(deployementVersion);

			servicioVersion.actualizarEstado(EEstadoVersion.ESPERANDO_FILA, version);

			if (fila.getGrupoDuenos()!=null){
				Set<String> grupos = new HashSet<>(Arrays.asList(fila.getGrupoDuenos().split(",")));
				Set<String> grupoFiltrados =  servicioGrupo.grupos().stream().filter(g -> grupos.contains(g.getGrupo())).map(EntGrupoPersona::getId).collect(Collectors.toSet());
				Set<EntPersona> personasPosibleAUsuar = new HashSet<>();
				grupoFiltrados.forEach(g -> personasPosibleAUsuar.addAll(servicioGrupo.buscarDetallePorGrupo(g).stream().map(EntGrupoPersonaDetalle::getPersona).collect(Collectors.toSet()) ));

				servicioVersion.buscaParticipantes(version).stream().map(EntVersionParticipante::getParticipante).filter(p -> personasPosibleAUsuar.contains(p)).forEach(p -> nuevaPersonaFilaDueno(deployementVersion, p));
			}
		}
		notificarVersionNueva(version, fila);
	}

	private void notificarVersionNueva(EntVersion version, EntFilaDespliegue fila){
		StringBuilder mensaje = new StringBuilder(150).append("Se ha agrega la versión ").append(version.getNumero()). append(" a la fila de ").append(fila.getAmbiente().getAmbiente().getNombre()).append(" en la fecha ").append( UtilFecha.getFechaFormateada(UtilFecha.getFechaJenkins(version.getInicioJob()))).append(" para el branch " ).append(version.getBranchOrigen()).append(" de los tickets (");
		List<EntVersionTicket> tickets = servicioVersion.buscaTickets(version);
		for (EntVersionTicket ticket : tickets) {
			mensaje.append(ticket.getTicketSysAid().getNumero()).append(',');
		}
		mensaje.deleteCharAt(mensaje.length()-1);
		mensaje.append(')');


		EntVersionAlerta versionAlerta = new EntVersionAlerta();
		versionAlerta.setAlerta(ETipoAlertaVersion.VERSION_CREADA);
		versionAlerta.setAmbiente(fila.getAmbiente().getAmbiente());
		versionAlerta.setVersion(version);
		versionAlerta.setMensaje(mensaje.toString()) ;

		servicioVersion.crearAlerta(versionAlerta);
	}

	public void eliminarFilaVersion(String id){
		entityManager.remove(entityManager.find(EntFilaDespliegueVersion.class, id));
	}

	public EntFilaDespliegue nuevaFila(EntAmbienteAplicacion ambiente, String estadosJiras, String estadosSysAid){
		EntFilaDespliegue fila = new EntFilaDespliegue();
		fila.setAmbiente(ambiente);
		fila.setEstadosJiras(estadosJiras);
		fila.setEstadosSysAid(estadosSysAid);
		entityManager.persist(fila);
		return fila;
	}

	public EntFilaDespliegue actualizarEntidad(EntFilaDespliegue fila){
		return entityManager.merge(fila);
	}

	private void nuevaPersonaFilaDueno(EntFilaDespliegueVersion version, EntPersona persona){
		EntFilaDespliegueVersionDueno dueno = new EntFilaDespliegueVersionDueno();
		dueno.setDueno(persona);
		dueno.setDespliegueVersion(version);
		entityManager.persist(dueno);
	}

	public void subePrioridad(String id) {
		EntFilaDespliegueVersion versionFila = entityManager.find(EntFilaDespliegueVersion.class, id);
		entityManager.createNamedQuery("buscarPorFilaMenorPrioridad.fila", EntFilaDespliegueVersion.class)
		.setParameter("fil", versionFila.getFila())
		.setParameter("prd", versionFila.getPrioridad()).getResultList().stream().findFirst().ifPresent( o -> cambiaPrioridades(versionFila, o));
	}

	public void bajaPrioridad(String id) {
		EntFilaDespliegueVersion versionFila = entityManager.find(EntFilaDespliegueVersion.class, id);
		entityManager.createNamedQuery("buscarPorFilaMayorPrioridad.fila", EntFilaDespliegueVersion.class)
		.setParameter("fil", versionFila.getFila())
		.setParameter("prd", versionFila.getPrioridad()).getResultList().stream().findFirst().ifPresent( o -> cambiaPrioridades(versionFila, o));
	}

	private void cambiaPrioridades(EntFilaDespliegueVersion versionFila,EntFilaDespliegueVersion versionFilaOther){
		int prioridad = versionFilaOther.getPrioridad();
		versionFilaOther.setPrioridad(versionFila.getPrioridad());
		versionFila.setPrioridad(prioridad);

		entityManager.merge(versionFila);
		entityManager.merge(versionFilaOther);
	}

	public List<EntFilaDespliegueVersionDueno> getDuenosVersion(EntVersion version) {
		return  entityManager.createNamedQuery("buscarPorVersion.duenoFilaVersion",EntFilaDespliegueVersionDueno.class)
				.setParameter("ver", version).getResultList();
	}

	public void deploy(EntServidor servidor,EntFilaDespliegueVersion versionFila) {
		versionFila.setProcesandoDeploy(true);
		versionFila = entityManager.merge(versionFila);

		servicioJobDespliegueVersion.nuevoDeploy(servidor, versionFila);
	}

	public List<EntFilaDespliegueVersion> getFilas(EntFilaDespliegue filaDespliegue, EntVersion version) {
		return entityManager.createNamedQuery("buscarPorFilaVersion.fila",EntFilaDespliegueVersion.class).setParameter("ver", version.getId()).setParameter("fer", filaDespliegue).getResultList();
	}
}