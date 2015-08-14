package rd.huma.dashboard.servicios.background.ejecutores.aplicacion.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionAlerta;
import rd.huma.dashboard.model.transaccional.EntVersionLog;
import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;
import rd.huma.dashboard.servicios.background.AEjecutor;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class EjecutorAlertaLogDisponible extends AEjecutor {

	private Path pathArchivoCreado;
	private ServicioVersion servicioVersion;

	public EjecutorAlertaLogDisponible(Path path) {
		this.pathArchivoCreado = path;
	}

	@Override
	public void ejecutar() {
		this.servicioVersion = ServicioVersion.getInstanciaTransaccional();
		 Path path = creaArchivoZip();
		 crearVersionLog(path);
		 borrarArchivoLogOriginal();
	}

	private void crearVersionLog(Path path) {
		String numeroVersion = pathArchivoCreado.getParent().getFileName().toString();
		Optional<EntVersion> posibleVersion = servicioVersion.buscaPorNumero(numeroVersion).stream().findFirst();
		if (posibleVersion.isPresent()){
			EntConfiguracionGeneral configuracionGeneral = ServicioConfiguracionGeneral.getInstanciaTransaccional().getConfiguracionGeneral().get();

			EntVersion version = posibleVersion.get();
			EntVersionLog log = new EntVersionLog();
			log.setPath(path.toString());
			log.setVersion(version);
			servicioVersion.crearVersionLog(log);

			EntVersionAlerta alerta = new EntVersionAlerta();
			alerta.setVersion(version);
			alerta.setAlerta(ETipoAlertaVersion.VERSION_NO_SUBIO);
			alerta.setMensaje(new StringBuilder(150)
							 .append("La version : ").append(version.getNumero())
							 .append("\n No logro hacer el despliegue")
							 .append(" Favor bajar el <a href=\"")
							 .append(configuracionGeneral.getRutaDashBoard()).append("api/versionLog/").append(log.getId())
							 .append("\">")
							 .append("log").append("</a>")
							 .append(" para saber la razon fallo la subida.")
							 .append("\n").append(" este log expirara, del servidor para la fecha ").append(log.getExpiracion())

			.toString()
			);
			servicioVersion.crearAlerta(alerta);
		}
	}

	private void borrarArchivoLogOriginal(){
		 File file = pathArchivoCreado.toFile();
		 if (file.exists()){
			 file.delete();
		 }
	}

	private Path creaArchivoZip(){
		try{
			Path file =  Files.createFile(Paths.get(pathArchivoCreado.toString(),".zip"));

			try( ZipOutputStream zipStream = new ZipOutputStream( new FileOutputStream(file.toFile())) ) {
				File fileName = pathArchivoCreado.toFile();
				try (FileInputStream inputStream = new FileInputStream(fileName)) {

					ZipEntry entry = new ZipEntry(fileName.getName());
					entry.setCreationTime(FileTime.fromMillis(fileName.lastModified()));


					byte[] readBuffer = new byte[2048];
					int amountRead;

					while ((amountRead = inputStream.read(readBuffer)) > 0) {
						zipStream.write(readBuffer, 0, amountRead);
					}

				}catch(IOException ioException){
					throw new UncheckedIOException(ioException);
				}
			}
			return file;
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}