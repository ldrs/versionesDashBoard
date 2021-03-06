package rd.huma.dashboard.servicios.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ServicioGeneracionZipFileFromUrls {

	private static final Logger LOGGER = Logger.getLogger(ServicioGeneracionZipFileFromUrls.class.getName());

	private List<String> urls;
	private String nombre;
	private Consumer<String> handlerFalloUrl;
	private Path carpetaTemporal;
	private boolean query;

	public ServicioGeneracionZipFileFromUrls(String nombre, String... urls) {
		this(nombre, Arrays.asList(urls));
	}

	public ServicioGeneracionZipFileFromUrls(String nombre, List<String> urls) {
		this.urls = urls;
		this.nombre = nombre;
	}

	public ServicioGeneracionZipFileFromUrls setHandlerFalloUrl(Consumer<String> handlerFalloUrl) {
		this.handlerFalloUrl = handlerFalloUrl;
		return this;
	}

	public ServicioGeneracionZipFileFromUrls setQuery(boolean query) {
		this.query = query;
		return this;
	}


	public File generar(){
		try {
			return creaArchivoZip(deArchivos(creaDirectorioTemporal()));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private Path creaDirectorioTemporal() throws IOException{
		this.carpetaTemporal = Files.createDirectories(Paths.get("/home/appadmin/tmp", nombre));
		return carpetaTemporal;
	}

	private File creaArchivoZip(List<Path> files) throws IOException{
		Path path = Files.createTempFile(carpetaTemporal,nombre, ".zip");
		try( ZipOutputStream zipStream = new ZipOutputStream( new FileOutputStream(path.toFile())) ) {
			files.forEach(pathFile -> agregarArchivos(zipStream, pathFile));
		}
		return path.toFile();
	}

	private void agregarArchivos(ZipOutputStream zipStream, Path path) {
		File file = path.toFile();
		String inputFileName =file.getPath();
		try (FileInputStream inputStream = new FileInputStream(inputFileName)) {

			ZipEntry entry = new ZipEntry(file.getName());
			entry.setCreationTime(FileTime.fromMillis(file.lastModified()));

			zipStream.putNextEntry(entry);
			byte[] readBuffer = new byte[2048];
			int amountRead;

			while ((amountRead = inputStream.read(readBuffer)) > 0) {
				zipStream.write(readBuffer, 0, amountRead);
			}
			zipStream.closeEntry();
		}catch(IOException ioException){
			throw new UncheckedIOException(ioException);
		}


	}

	private List<Path> deArchivos(Path carpetaTemporal) throws IOException{
		List<Path> files = new ArrayList<>();
		for (String url : urls) {
			LOGGER.info(String.format("Bajando el url %s para el archivo %s",url,nombre));

			Response resultado = ClientBuilder.newClient().target(url).request().buildGet().invoke();
			if (resultado.getStatus()==200){
				String nombreScript = url.substring(url.lastIndexOf('/'));
				if (query && !nombreScript.endsWith(".sql")){
					nombreScript+=".sql";
				}

				Path archivoCrear = Paths.get(carpetaTemporal.toString(), nombreScript);
				Files.deleteIfExists(archivoCrear);
				Path path = Files.createFile(archivoCrear);

				try (OutputStream out = Files.newOutputStream(path)){
					if (query){
						out.write(resultado.readEntity(String.class).getBytes()) ;
					}else{
						UtilIO.copiar(resultado.readEntity(InputStream.class), out);
					}
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