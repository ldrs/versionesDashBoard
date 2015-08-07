package rd.huma.dashboard.servicios.utilitarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.zip.ZipOutputStream;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ServicioGeneracionZipFileFromUrls {

	private List<String> urls;
	private String nombre;
	private Consumer<String> handlerFalloUrl;

	public ServicioGeneracionZipFileFromUrls(List<String> urls, String nombre) {
		this.urls = urls;
		this.nombre = nombre;
	}

	public ServicioGeneracionZipFileFromUrls setHandlerFalloUrl(Consumer<String> handlerFalloUrl) {
		this.handlerFalloUrl = handlerFalloUrl;
		return this;
	}


	public File generar() throws IOException{
		return creaArchivoZip(deArchivos());

	}

	private File creaArchivoZip(List<Path> files) throws IOException{
		Path path = Files.createTempFile(nombre, ".zip");
	     try( ZipOutputStream zipStream = new ZipOutputStream( new FileOutputStream(path.toFile())) ) {

	     }
	     return path.toFile();
	}

	private List<Path> deArchivos() throws IOException{
		List<Path> files = new ArrayList<>();
		for (String url : urls) {
			Response resultado = ClientBuilder.newClient().target(url).request().buildGet().invoke();
			if (resultado.getStatus()==200){
				Path path = Files.createTempFile(nombre, url.substring(url.lastIndexOf('/')));
				try (OutputStream out = Files.newOutputStream(path)){
					out.write(resultado.readEntity(String.class).getBytes()) ;
					out.flush();
				}
				files.add(path);
			}else{
				notificaFallo(url);

			}
		}
		return files;
	}

	private void notificaFallo(String url){
		if (handlerFalloUrl!=null){
			handlerFalloUrl.accept(url);
		}
	}

}