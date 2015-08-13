package rd.huma.dashboard.servicios.integracion.svn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.concurrent.TimeUnit;

import rd.huma.dashboard.util.IOUtil;

public class ServicioUltimaRevisionSVN {

	private String urlDestino;

	public ServicioUltimaRevisionSVN(String urlDestino) {
		this.urlDestino = urlDestino;
	}

	public UltimaRevision revision(){
		return interpetacion(buscaComentarioSVN());
	}

	private UltimaRevision interpetacion(String comentario) {
		BufferedReader bufReader = new BufferedReader(new StringReader(comentario));
		String fistLineFound = null;
		String line;
		try {
			while( (line=bufReader.readLine()) != null ){

				if (line!=null && line.indexOf('|')!=-1 && line.split("[r]*[|]").length==4){
					fistLineFound = line;
					break;
				}
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		if (fistLineFound!=null){
			String[] data = fistLineFound.split("[r]*[|]");
			long  revision =  Long.valueOf(data[0].substring(1).trim());
			return new UltimaRevision(revision, data[1].trim(), data[2].trim());
		}

		return null;
	}

	private String  buscaComentarioSVN(){
		try {
			Process proceso = Runtime.getRuntime().exec("svn log "+ urlDestino);
			proceso.waitFor(10, TimeUnit.SECONDS);

			return IOUtil.toString(proceso.getInputStream());
		} catch (IOException | InterruptedException e) {
			throw new IllegalStateException("No pudo ser encontrado el log.");
		}
	}


}