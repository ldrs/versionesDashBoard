package rd.huma.dashboard.servicios.transaccional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAplicacion;

@Stateless
@Servicio
public class ServicioAplicacion {

	@Inject
	private EntityManager entityManager;

	private static final Map<String, EntAplicacion> CACHE = new ConcurrentHashMap<>();

	public static Optional<EntAplicacion> getCacheAplicacion(String nombre){
		EntAplicacion aplicacion = CACHE.get(nombre);
		if (aplicacion == null){
			synchronized(nombre){


				Servicio anotacion = ServicioConfiguracionGeneral.class.getAnnotation(Servicio.class);
				ServicioAplicacion instancia = CDI.current().select(ServicioAplicacion.class,anotacion).get();
				Optional<EntAplicacion> opcionalConfig = instancia.getAplicacion(nombre);
				if (opcionalConfig.isPresent()){
					CACHE.putIfAbsent(nombre, opcionalConfig.get());
				}
			}
			return Optional.ofNullable(CACHE.get(nombre));
		}
		return Optional.of(aplicacion);
	}

	public Optional<EntAplicacion> getAplicacion(String nombre) {
		return entityManager.createNamedQuery("aplicacion.buscar", EntAplicacion.class).setParameter("nomApp", nombre).getResultList().stream().findFirst();
	}

	public EntAplicacion configurarCrearAplicacion(String nombre, String jiraKey, String svnPath, int orden, String nombrePropiedadesPom){
		return configurarCrearAplicacion(nombre, jiraKey, svnPath, orden, nombrePropiedadesPom,null,null, null,null);
	}

	public EntAplicacion configurarCrearAplicacion(String nombre, String jiraKey, String svnPath, int orden, String nombrePropiedadesPom, String jenkinsDeployNombreJob, String jenkinsOracleNombreJob, String jenkinsSQLNombreJob, String ambienteParaHacerDeployDefecto){
		Optional<EntAplicacion> optional = getAplicacion(nombre);
		EntAplicacion aplicacion;
		if (optional.isPresent()){
			aplicacion = entityManager.find(EntAplicacion.class, optional.get().getId());
		}else{
			aplicacion = new EntAplicacion();
		}
		aplicacion.setJiraKey(jiraKey);
		aplicacion.setNombre(nombre);
		aplicacion.setSvnPath(svnPath);
		aplicacion.setOrden(orden);
		aplicacion.setNombrePropiedadesPom(nombrePropiedadesPom);
		aplicacion.setNombreJobDeployJenkins(jenkinsDeployNombreJob);
		aplicacion.setNombreJobOracleReportJenkins(jenkinsOracleNombreJob);
		aplicacion.setNombreJobSQLJenkins(jenkinsSQLNombreJob);
		aplicacion.setRutaSvnAmbiente(ambienteParaHacerDeployDefecto);
		entityManager.persist(aplicacion);
		CACHE.remove(nombre);

		return aplicacion;
	}


	public static ServicioAplicacion getInstanciaTransaccional(){
		Servicio servicio = ServicioAplicacion.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioAplicacion.class, servicio).get();
	}

	public List<EntAplicacion> getAplicaciones() {
		return entityManager.createNamedQuery("aplicacion.todos",EntAplicacion.class).getResultList();
	}
}