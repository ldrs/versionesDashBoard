package rd.huma.dashboard.servicios.background.ejecutores.fila.deploy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.transaccional.EntFilaDespliegue;
import rd.huma.dashboard.model.transaccional.EntFilaDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoServidor;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class ProcesaFilaDeployAutomatico {

	private ServicioFila servicioFila;
	private EntFilaDespliegue fila;
	private ServicioServidor servicioServidor;

	public ProcesaFilaDeployAutomatico(ServicioFila servicioFila,ServicioServidor servicioServidor, EntFilaDespliegue fila) {
		this.servicioFila = servicioFila;
		this.fila = fila;
		this.servicioServidor = servicioServidor;
	}

	public void procesar(){
		List<EntFilaDespliegueVersion> filas = servicioFila.getFilasPorAmbienteAplicacion(fila.getAmbiente().getId());
		filas.stream().filter(v -> !v.isProcesandoDeploy()).findFirst().ifPresent(this::intentaDeploy);
	}

	private void intentaDeploy(EntFilaDespliegueVersion versionFila){
		List<EntServidor> servidores = servicioServidor.getServidoresAmbiente(fila.getAmbiente().getId()).stream()
				.filter(servidor -> servidor.getEstadoServidor() != EEstadoServidor.NO_DISPONIBLE)
				.filter(servidor -> servidor.getEstadoServidor() != EEstadoServidor.NO_DISPONIBLE_MANUAL)
				.collect(Collectors.toList());
		Optional<EntServidor> optional = buscaSiYaTieneEsteBranchActivo(versionFila, servidores);
		if (optional.isPresent()){
			EntServidor servidor = optional.get();
			if (servidor.getVersionActual()!=null){
				ServicioVersion.getInstanciaTransaccional().actualizarEstado(EEstadoVersion.REMPLAZADA, servidor.getVersionActual());
			}
			deploy(servidor, versionFila);
			return;
		}

		Optional<EntJobDespliegueVersion> ultimoJob = ServicioJobDespliegueVersion.getInstanciaTransaccional().buscaPorBranch(versionFila.getVersion().getBranchOrigen()).stream().findFirst();
		if (ultimoJob.isPresent()){
			EntServidor servidorTT = ultimoJob.get().getServidor();
			optional = servidores.stream()	.filter(servidor -> servidor.getEstadoServidor()== EEstadoServidor.LIBRE)
								.filter(servidor -> servidorTT.equals(servidor)).findFirst();
			if (optional.isPresent()){
				deploy(optional.get(), versionFila);
				return;
			}

		}

		servidores.stream().filter(	servidor -> servidor.getEstadoServidor() == EEstadoServidor.LIBRE).findFirst().ifPresent(servidor -> deploy(servidor,versionFila));
	}


	private Optional<EntServidor> buscaSiYaTieneEsteBranchActivo(EntFilaDespliegueVersion versionFila,List<EntServidor> servidores){
		String branch = versionFila.getVersion() == null? "" : versionFila.getVersion().getBranchOrigen();

		return servidores.stream()	.filter(servidor -> servidor.getEstadoServidor() != EEstadoServidor.LIBRE)
							.filter(servidor -> servidor.getVersionActual()!=null)
							.filter(servidor ->  branch.equals(servidor.getVersionActual().getBranchOrigen()))
							.findFirst();
	}

	private void deploy(EntServidor servidor, EntFilaDespliegueVersion versionFila){
		servicioFila.deploy(servidor,versionFila);
	}
}