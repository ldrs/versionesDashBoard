package rd.huma.dashboard.servicios.background.watchers;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import rd.huma.dashboard.util.Aplicacion;

public class ProcesadorWatcher extends Thread{

	private Path ruta;

	public ProcesadorWatcher(Path ruta) {
		this.ruta = ruta;
	}


	@Override
	public void run() {
		Thread.currentThread().setName("Procesador Watcher " + getClass().getName());

		try {
			procesar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private void procesar() throws IOException{
		WatchService watcher = FileSystems.getDefault().newWatchService();
		ruta.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
		while(Aplicacion.isAplicacionUp()){
			   WatchKey key;
			    try {
			        key = watcher.take();
			    } catch (InterruptedException ex) {
			        return;
			    }


			    for (WatchEvent<?> event : key.pollEvents()) {
			        WatchEvent.Kind<?> kind = event.kind();

			        WatchEvent<Path> ev = (WatchEvent<Path>) event;
			        Path fileName = ev.context();

			        if(kind == StandardWatchEventKinds.ENTRY_CREATE){
			        	procesarArchivoCreado(fileName);
			        }else{
			        	continue;
			        }
			    }

			    // IMPORTANT: The key must be reset after processed
			    boolean valid = key.reset();
			    if (!valid) {
			        break;
			    }
		}
	}

	protected void procesarArchivoCreado(Path pathArchivoCreado){}
}