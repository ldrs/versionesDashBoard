package rd.huma.dashboard.servicios.background.ejecutores.estado.branch;

import static rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral.getCacheConfiguracionGeneral;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import rd.huma.dashboard.model.EEstadoVersion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.servicios.background.Ejecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorBranchActivo extends Ejecutor {

	private EntConfiguracionGeneral configuracionGeneral;
	private ServicioVersion servicioVersion;


	@Override
	public void ejecutar() {
		init();
		if (configuracionGeneral == null){
			return;
		}
		 getStream().forEach(this::procesar);
		
	}
	
	private void procesar(EntVersion version){
		Response get = ClientBuilder.newClient().target(getRutaSvn(version)).request().get();
		if (get.getStatus()==404){//no existe version
			servicioVersion.actualizarEstado(EEstadoVersion.BRANCH_ELIMINADO, version);
		}
	}
	
	private String getRutaSvn(EntVersion version){
		return configuracionGeneral.getRutaSvn() + version.getSvnOrigen() + "/branches/" + version.getBranchOrigen() + "/pom.xml";
	}
	

	private Stream<EntVersion> getStream(){
		return ServicioVersion.getInstanciaTransaccional().buscaVersiones(getEstadosActivos()).stream();
	}
	
	
	private Set<EEstadoVersion> getEstadosActivos(){
		return Arrays.asList(EEstadoVersion.values()).stream().filter(EEstadoVersion::activo).collect(Collectors.toSet());
	}
	
	private void init(){
		Optional<EntConfiguracionGeneral> opcional  = getCacheConfiguracionGeneral();
		if (opcional.isPresent()){
			configuracionGeneral = opcional.get();
		}
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
	}

}