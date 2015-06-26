package rd.huma.dashboard.servicios.background;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitorEjecutor {

	private ScheduledExecutorService scheduler;

	@PostConstruct
	public void inicializar(){
		scheduler = Executors.newScheduledThreadPool(50);
	}

	@PreDestroy
	 public void cleanup() {
	   scheduler.shutdown();
	 }

	public void ejecutarAsync(Ejecutor ejecutor){
		scheduler.schedule(ejecutor, 500, TimeUnit.MILLISECONDS);
	}
}