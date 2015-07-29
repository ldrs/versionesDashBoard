package rd.huma.dashboard.servicios.background.ejecutores.fila.deploy;

import java.util.List;

import rd.huma.dashboard.model.transaccional.EntFilaDeployement;
import rd.huma.dashboard.model.transaccional.EntFilaDeployementVersion;
import rd.huma.dashboard.model.transaccional.EntServidor;
import rd.huma.dashboard.model.transaccional.dominio.EEstadoServidor;
import rd.huma.dashboard.servicios.background.MonitorEjecutor;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.EjecutorDeployVersionJenkins;
import rd.huma.dashboard.servicios.transaccional.ServicioFila;
import rd.huma.dashboard.servicios.transaccional.ServicioServidor;

public class ProcesaFilaDeployAutomatico {

	private ServicioFila servicioFila;
	private EntFilaDeployement fila;
	private ServicioServidor servicioServidor;
	
	public ProcesaFilaDeployAutomatico(ServicioFila servicioFila,ServicioServidor servicioServidor, EntFilaDeployement fila) {
		this.servicioFila = servicioFila;
		this.fila = fila;
		this.servicioServidor = servicioServidor;
	}
	
	public void procesar(){
		List<EntFilaDeployementVersion> filas = servicioFila.getFilasPorAmbienteAplicacion(fila.getAmbiente().getId());
		filas.stream().findFirst().ifPresent(this::intentaDeploy);
	}
	
	private void intentaDeploy(EntFilaDeployementVersion versionFila){
		List<EntServidor> servidores = servicioServidor.getServidoresAmbiente(fila.getAmbiente().getId());
		servidores.stream().filter(	servidor -> servidor.getEstadoServidor() == EEstadoServidor.LIBRE
									||
									versionFila.getVersion().getBranchOrigen().equals( servidor.getVersionActual().getBranchOrigen())
									).findFirst().ifPresent(servidor -> deploy(servidor,versionFila));
	}
	
	private void deploy(EntServidor servidor, EntFilaDeployementVersion versionFila){
		// EjecutorDeployVersionJenkins
	}
	
}