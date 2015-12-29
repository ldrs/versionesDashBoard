package rd.huma.dashboard.servicios.background.ejecutores.nexus;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntTicketSysAidEstado;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.version.BuscadorModulos;
import rd.huma.dashboard.servicios.integracion.nexus.ServicioNexus;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.transaccional.ServicioAplicacion;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioTicketSysaid;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorBorrarVersionesNexus extends AEjecutor {

	private static final Logger LOGGER = Logger.getLogger(EjecutorBorrarVersionesNexus.class.getSimpleName());

	private ServicioVersion servicioVersion;
	private ServicioNexus servicioNexus;

	@Override
	public void ejecutar() {
		 this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
		 servicioNexus = ServicioNexus.nuevo();
		 borrarVersionesNexusLuego2Meses();
		 eliminaDuplicaciones();
		 borrarVersionesNoExistenTag();
		 borrarVersionesExpiraronProduccion();
	}

	private void borrarVersionesExpiraronProduccion() {
		List<Integer> estados = ServicioTicketSysaid.getInstanciaTransaccional().estadosSysAid().stream().filter(e -> e.isBorrableNexus()).map(EntTicketSysAidEstado::getCodigo).collect(Collectors.toList());
		List<EntVersion> versiones = servicioVersion.buscaVersionesPorEstadoSysAid(estados);
		LOGGER.info(String.format("Cantidad de Versiones por eliminar %s para el estado(primero) %s",versiones.size(), estados.stream().findFirst().orElse(-1)));
		for (EntVersion version : versiones) {
			eliminaModulos(version);
		}

	}

	private void borrarVersionesNoExistenTag(){
		List<EntAplicacion> aplicaciones = ServicioAplicacion.getInstanciaTransaccional().getAplicaciones();

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

	private void borrarVersionesNexusLuego2Meses(){
		servicioVersion.buscarVersionActivasAntesDeLaFecha(getEstadosActivos(), Instant.now().minus(70, ChronoUnit.DAYS)).forEach(this::eliminaModulos);
	}

	private void eliminaDuplicaciones(){
		servicioVersion.buscaBranchVersionesDuplicadas(getEstadosActivos()).forEach(this::eliminaVersionesViejasDuplicadaBranch);
	}

	private void eliminaVersionesViejasDuplicadaBranch(String branch){
		List<EntVersion> versiones = servicioVersion.buscaPorBranch(branch).stream().filter(b -> b.getFechaRegistro().isAfter(Instant.now().minus(3, ChronoUnit.DAYS))).collect(Collectors.toList());
		versiones.sort( (c1, c2) -> c1.getFechaRegistro().compareTo(c2.getFechaRegistro()));
		if (versiones.size()>2){
			versiones.subList(0, versiones.size()-2).forEach(this::eliminaModulos);
		}
	}

	private void eliminaModulos(EntVersion version){
		servicioVersion.buscaModulos(version).forEach(this::eliminaModulo);
		servicioVersion.actualizarEstado(EEstadoVersion.NEXUS_ELIMINADO, version);
		servicioVersion.eliminaModulos(version);
	}

	private void eliminaModulo(EntVersionModulo modulo){
		Artefacto artefacto = modulo.getArtefacto();
		servicioNexus.eliminarModulo(artefacto.getGrupo(),artefacto.getArtefacto(),modulo.getVersion().getNumero());
	}

	private Set<EEstadoVersion> getEstadosActivos(){
		return Arrays.asList(EEstadoVersion.values()).stream().filter(EEstadoVersion::activo).collect(Collectors.toSet());
	}
}