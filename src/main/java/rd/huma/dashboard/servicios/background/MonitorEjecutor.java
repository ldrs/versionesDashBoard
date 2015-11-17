package rd.huma.dashboard.servicios.background;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import rd.huma.dashboard.servicios.background.ejecutores.alertas.EjecutorEnvioAlertasCorreo;
import rd.huma.dashboard.servicios.background.ejecutores.estado.branch.EjecutorBranchActivo;
import rd.huma.dashboard.servicios.background.ejecutores.fila.deploy.EjecutorDeployVersionAutomatico;
import rd.huma.dashboard.servicios.background.ejecutores.fila.eliminacion.EjecutorEliminadorFilas;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.monitoreo.EjecutorMonitoreoJobEstadoPendiente;
import rd.huma.dashboard.servicios.background.ejecutores.nexus.EjecutorBorrarVersionesNexus;
import rd.huma.dashboard.servicios.background.ejecutores.svn.ambiente.EjecutorModulosAmbienteSVN;
import rd.huma.dashboard.servicios.background.ejecutores.svn.branch.EjecutorSVNBuscaMergeBranches;
import rd.huma.dashboard.servicios.background.ejecutores.svn.branch.EjecutorSVNBuscaOrigenNull;
import rd.huma.dashboard.servicios.background.ejecutores.svn.revision.EjecutorSVNRevisiones;
import rd.huma.dashboard.servicios.background.ejecutores.sysaid.EjecutorSysAidBuscaActualizacion;

@ApplicationScoped
public class MonitorEjecutor {

	private ScheduledExecutorService scheduler;

	@PostConstruct
	public void inicializar(){
		scheduler = Executors.newScheduledThreadPool(80);
		scheduler.scheduleAtFixedRate(new EjecutorBranchActivo(), 5, 30, TimeUnit.MINUTES);
		scheduler.scheduleAtFixedRate(new EjecutorEliminadorFilas(), 5, 1, TimeUnit.MINUTES);
		scheduler.scheduleAtFixedRate(new EjecutorModulosAmbienteSVN(), 1, 24, TimeUnit.HOURS);
		scheduler.scheduleAtFixedRate(new EjecutorDeployVersionAutomatico(), 1, 1, TimeUnit.MINUTES);

		scheduler.scheduleAtFixedRate(new EjecutorEnvioAlertasCorreo(), 5, 2, TimeUnit.MINUTES);
		scheduler.scheduleAtFixedRate(new EjecutorBorrarVersionesNexus(), 1, 30, TimeUnit.MINUTES);

		scheduler.scheduleAtFixedRate(new EjecutorMonitoreoJobEstadoPendiente(), 1, 5, TimeUnit.MINUTES);

		//scheduler.scheduleAtFixedRate(new EjecutorSVNBuscaMergeBranches(), 1, 5, TimeUnit.MINUTES);
		//scheduler.scheduleAtFixedRate(new EjecutorSVNBuscaOrigenNull(), 1, 5, TimeUnit.MINUTES);

		scheduler.scheduleAtFixedRate(new EjecutorSysAidBuscaActualizacion(), 1, 10, TimeUnit.MINUTES);

		scheduler.scheduleAtFixedRate(new EjecutorSVNRevisiones(), 1, 800, TimeUnit.MINUTES);

	}

	@PreDestroy
	 public void cleanup() {
	   scheduler.shutdown();
	 }

	public void ejecutarAsync(AEjecutor ejecutor){
		scheduler.schedule(ejecutor, 500, TimeUnit.MILLISECONDS);
	}
}