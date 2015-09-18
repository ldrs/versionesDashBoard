package rd.huma.dashboard.servicios.transaccional;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rd.huma.dashboard.model.transaccional.EntAmbienteAplicacion;
import rd.huma.dashboard.model.transaccional.EntHistoricoUndeploy;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntRepositorioDatos;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoServidor;
import rd.huma.dashboard.model.transaccional.dominio.ETipoUndeploy;


@Stateless
@Servicio
public class ServicioServidor {

	@Inject
	private EntityManager entityManager;

	public static ServicioServidor getInstanciaTransaccional(){
		Servicio servicio = ServicioServidor.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioServidor.class, servicio).get();
	}

	public EntServidor nuevoServidor(EntAmbienteAplicacion ambiente, EntRepositorioDatos baseDatos, String nombre, String rutaEntrada, String nombreJenkins){
		EntServidor servidor = new EntServidor();
		servidor.setAmbiente(ambiente);
		servidor.setBaseDatos(baseDatos);
		servidor.setNombre(nombre);
		servidor.setRutaEntrada(rutaEntrada);
		servidor.setNombreServidorJenkins(nombreJenkins);
		entityManager.persist(servidor);
		return servidor;
	}

	public List<EntServidor> getServidoresAmbiente(String id) {
		return entityManager.createNamedQuery("buscar.servidor",EntServidor.class).setParameter("amb", id).getResultList();
	}

	public List<EntServidor> getServidoresPorBranch(String  branch){
		return entityManager.createNamedQuery("buscarPorBranch.servidor",EntServidor.class).setParameter("branch", branch).getResultList();
	}

	public void cambiaVersionServidor(EntServidor servidor, EntVersion version, EntPersona persona, ETipoUndeploy tipoUndeploy) {
		EntServidor servidorDatos = entityManager.find(EntServidor.class, servidor.getId());
		if (version == null && servidor.getVersionActual()!=null){
			EntHistoricoUndeploy undeploy = new EntHistoricoUndeploy();
			undeploy.setAutor(persona);
			undeploy.setServidor(servidorDatos);
			undeploy.setVersion(servidor.getVersionActual());
			undeploy.setTipoUndeploy(tipoUndeploy);
			entityManager.persist(undeploy);
		}
		servidorDatos.setVersionActual(version);
		servidorDatos.setEstadoServidor(version == null ? EEstadoServidor.LIBRE : EEstadoServidor.OCUPADO_DESPLIEGE_EN_PROCESO);
		entityManager.persist(servidorDatos);

	}

	public List<EntServidor> getServidoresPorVersion(String id) {
		return entityManager.createNamedQuery("buscarPorVersion.servidor",EntServidor.class).setParameter("id", id).getResultList();
	}

	public List<EntHistoricoUndeploy> getHistoricoUndeploy(String id){
		return entityManager.createNamedQuery("buscarPorVersion.historicoUndeploy",EntHistoricoUndeploy.class).setParameter("ver", id).getResultList();
	}

	public EntServidor getServidorPorId(String idServidor) {
		return entityManager.find(EntServidor.class, idServidor);
	}

	public EntServidor actualizarServidor(EntServidor servidor) {
		return entityManager.merge(servidor);
	}
}