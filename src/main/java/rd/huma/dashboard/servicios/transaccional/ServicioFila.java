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
import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersionDueno;
import rd.huma.dashboard.model.transaccional.EntGrupoPersona;
import rd.huma.dashboard.model.transaccional.EntGrupoPersonaDetalle;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionParticipante;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;

@Servicio
@Stateless
public class ServicioFila {

	private @Servicio @Inject ServicioGrupo servicioGrupo;

	private @Servicio @Inject ServicioVersion servicioVersion;

	Function<Object[], FilaBranch> toFilaBranch = new Function<Object[], FilaBranch>() {
	    public FilaBranch apply(Object[] t) {
	        return new FilaBranch((EntFilaDeployement) t[0], (String)t[1]);
	    }
	};

	@Inject
	private EntityManager entityManager;

	public List<EntFilaDeployementVersion> getFilas(FilaBranch filaBranch){
		return entityManager.createNamedQuery("buscarPorFilaBranch.fila",EntFilaDeployementVersion.class)
						.setParameter("fil", filaBranch.getFila())
						.setParameter("bra", filaBranch.getBranch())
						.getResultList();
	}

	public EntFilaDeployement getFila(String id){
		return entityManager.createNamedQuery("buscar.filaDeploment",EntFilaDeployement.class).setParameter("amb", id).getSingleResult();
	}

	public List<EntFilaDeployementVersion> getFilasPorEstadoVersion(Set<EEstadoVersion> estados){
		return entityManager.createNamedQuery("buscarPorVersionEstado.fila",EntFilaDeployementVersion.class).setParameter("est", estados).getResultList();
	}

	public List<EntFilaDeployement> getFilasDeploment(){
		return entityManager.createNamedQuery("todos.filaDeploment",EntFilaDeployement.class).getResultList();
	}

	public List<EntFilaDeployementVersion> getFilasPorAmbienteAplicacion(String id){
		return entityManager.createNamedQuery("buscarPorAmbiente.fila",EntFilaDeployementVersion.class).setParameter("amb", id).getResultList();
	}

	public static ServicioFila getInstanciaTransaccional(){
		Servicio servicio = ServicioFila.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioFila.class, servicio).get();
	}

	public void salirFila(EntFilaDeployementVersion filaVersion) {
		entityManager.remove(filaVersion);
	}

	public List<FilaBranch> getFilasPorBranchDuplicado(Set<EEstadoVersion> estados) {
		List<FilaBranch> filas = new ArrayList<FilaBranch>();
		entityManager.createNamedQuery("buscarPorDuplicacion.fila",Object[].class).setParameter("est", estados).getResultList()

		.stream().forEach(o-> filas.add(toFilaBranch.apply(o)));
		return filas;
	}

	public void ordenarFila(EntFilaDeployement filaHeader){
		AtomicInteger entero = new AtomicInteger(1);
		entityManager.createNamedQuery("buscarPorFila.fila",EntFilaDeployementVersion.class).setParameter("fil", filaHeader)
		.getResultList().forEach(f -> procesarOrden(f, entero.getAndIncrement()));
	}

	private void procesarOrden(EntFilaDeployementVersion fila, int prioridad){
		EntFilaDeployementVersion f = entityManager.getReference(EntFilaDeployementVersion.class, fila.getId());
		f.setPrioridad(prioridad);
		entityManager.persist(f);
	}

	public synchronized void crearVersionFila(EntVersion version,EntFilaDeployement fila) {
		int prioridad = entityManager.createNamedQuery("maxVersion.fila", Integer.class).setParameter("fil", fila).getSingleResult();

		EntFilaDeployementVersion deployementVersion = new EntFilaDeployementVersion();
		deployementVersion.setVersion(version);
		deployementVersion.setFila(fila);
		deployementVersion.setPrioridad(prioridad);

		entityManager.persist(deployementVersion);

		if (fila.getGrupoDuenos()!=null){
			Set<String> grupos = new HashSet<>(Arrays.asList(fila.getGrupoDuenos().split(",")));
			Set<String> grupoFiltrados =  servicioGrupo.grupos().stream().filter(g -> grupos.contains(g.getGrupo())).map(EntGrupoPersona::getId).collect(Collectors.toSet());
			Set<EntPersona> personasPosibleAUsuar = new HashSet<>();
			grupoFiltrados.forEach(g -> personasPosibleAUsuar.addAll(servicioGrupo.buscarDetallePorGrupo(g).stream().map(EntGrupoPersonaDetalle::getPersona).collect(Collectors.toSet()) ));

			servicioVersion.buscaParticipantes(version).stream().map(EntVersionParticipante::getParticipante).filter(p -> personasPosibleAUsuar.contains(p)).forEach(p -> nuevaPersonaFilaDueno(deployementVersion, p));
		}


	}

	public void eliminarFilaVersion(String id){
		entityManager.remove(entityManager.find(EntFilaDeployementVersion.class, id));
	}

	public EntFilaDeployement nuevaFila(EntAmbienteAplicacion ambiente, String estadosJiras){
		EntFilaDeployement fila = new EntFilaDeployement();
		fila.setAmbiente(ambiente);
		fila.setEstadosJiras(estadosJiras);
		entityManager.persist(fila);
		return fila;
	}

	private void nuevaPersonaFilaDueno(EntFilaDeployementVersion version, EntPersona persona){
		EntFilaDeployementVersionDueno dueno = new EntFilaDeployementVersionDueno();
		dueno.setDueno(persona);
		dueno.setVersion(version);
		entityManager.persist(dueno);
	}

	public void subePrioridad(String id) {
		EntFilaDeployementVersion versionFila = entityManager.find(EntFilaDeployementVersion.class, id);
		entityManager.createNamedQuery("buscarPorFilaMenorPrioridad.fila", EntFilaDeployementVersion.class)
		.setParameter("fil", versionFila.getFila())
		.setParameter("prd", versionFila.getPrioridad()).getResultList().stream().findFirst().ifPresent( o -> cambiaPrioridades(versionFila, o));
	}

	public void bajaPrioridad(String id) {
		EntFilaDeployementVersion versionFila = entityManager.find(EntFilaDeployementVersion.class, id);
		entityManager.createNamedQuery("buscarPorFilaMayorPrioridad.fila", EntFilaDeployementVersion.class)
		.setParameter("fil", versionFila.getFila())
		.setParameter("prd", versionFila.getPrioridad()).getResultList().stream().findFirst().ifPresent( o -> cambiaPrioridades(versionFila, o));
	}

	private void cambiaPrioridades(EntFilaDeployementVersion versionFila,EntFilaDeployementVersion versionFilaOther){
		int prioridad = versionFilaOther.getPrioridad();
		versionFilaOther.setPrioridad(versionFila.getPrioridad());
		versionFila.setPrioridad(prioridad);

		entityManager.merge(versionFila);
		entityManager.merge(versionFilaOther);
	}

	public List<EntFilaDeployementVersionDueno> getDuenosVersion(EntVersion version) {
		return  entityManager.createNamedQuery("buscarPorVersion.duenoFilaVersion",EntFilaDeployementVersionDueno.class)
				.setParameter("ver", version).getResultList();
	}
}