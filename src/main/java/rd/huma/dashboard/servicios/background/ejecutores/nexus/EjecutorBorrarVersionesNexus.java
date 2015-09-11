package rd.huma.dashboard.servicios.background.ejecutores.nexus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.version.BuscadorModulos;
import rd.huma.dashboard.servicios.integracion.nexus.ServicioNexus;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorBorrarVersionesNexus extends AEjecutor {

	private ServicioVersion servicioVersion;
	private ServicioNexus servicioNexus;

	@Override
	public void ejecutar() {
		 this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
		 eliminaDuplicaciones();
		 borrarVersionesNoExistenTag();
	}

	private void borrarVersionesNoExistenTag(){
		List<EntAplicacion> aplicaciones = ServicioAplicacion.getInstanciaTransaccional().getAplicaciones();
		ServicioNexus servicioNexus = ServicioNexus.nuevo();
		for (EntAplicacion aplicacion : aplicaciones) {
			List<EntVersionModulo> modulos = new BuscadorModulos(ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get(), aplicacion, new EntVersion()).buscar("/trunk/sigef/");
			ServicioSVN servicioSVN = ServicioSVN.para(aplicacion);
			Set<String> tags = servicioSVN.tags();
			Artefacto artifact = new Artefacto();
			artifact.setArtefacto(aplicacion.getNombreArtefactoBusquedaNexus());
			artifact.setGrupo(aplicacion.getNombreGrupoBusquedaNexus());
			List<String> versiones = servicioNexus.getVersions(artifact);
			for (String version : versiones) {
				if (!tags.contains(version)){
					Optional<EntVersion> posibleVersion = servicioVersion.buscaPorNumero(version).stream().findFirst();
					if (posibleVersion.isPresent()){
						eliminaModulos(posibleVersion.get());
					}else{
						for (EntVersionModulo moduloFalso : modulos){
							moduloFalso.getVersion().setNumero(version);
							eliminaModulo(moduloFalso);
						}
					}
				}
			}
		}
	}

	private void eliminaDuplicaciones(){
		  servicioVersion.buscaBranchVersionesDuplicadas(getEstadosActivos()).forEach(this::eliminaVersionesViejasDuplicadaBranch);
	}

	private void eliminaVersionesViejasDuplicadaBranch(String branch){
		List<EntVersion> versiones = servicioVersion.buscaPorBranch(branch);
		versiones.sort( (c1, c2) -> c1.getFechaRegistro().compareTo(c2.getFechaRegistro()));
		versiones.subList(0, versiones.size()-2).forEach(this::eliminaModulos);
	}

	private void eliminaModulos(EntVersion version){
		servicioVersion.buscaModulos(version).forEach(this::eliminaModulo);
		servicioVersion.actualizarEstado(EEstadoVersion.NEXUS_ELIMINADO, version);
	}

	private void eliminaModulo(EntVersionModulo modulo){
		Artefacto artefacto = modulo.getArtefacto();
		servicioNexus.eliminarModulo(artefacto.getGrupo(),artefacto.getArtefacto(),modulo.getVersion().getNumero());
	}

	private Set<EEstadoVersion> getEstadosActivos(){
		return Arrays.asList(EEstadoVersion.values()).stream().filter(EEstadoVersion::activo).collect(Collectors.toSet());
	}
}