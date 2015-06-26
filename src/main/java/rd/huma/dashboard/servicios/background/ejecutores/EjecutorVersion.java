package rd.huma.dashboard.servicios.background.ejecutores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.servicios.background.Ejecutor;

public class EjecutorVersion  extends Ejecutor{

	private EntVersion version;
	private EntConfiguracionGeneral configuracionGeneral;

	private String comentario;

	public EjecutorVersion(EntVersion version) {
		this.version = version;
	}

	@Override
	public void ejecutar() {
		encuentraComentario();

	}

	private String getRutaSvn(){
		return configuracionGeneral.getRutaSvn() + version.getSvnOrigen() + "/branches/" + version.getBranchOrigen();
	}


	private void  encuentraComentario(){
		try {
			Process proceso = Runtime.getRuntime().exec("svn log -r"+version.getRevisionSVN()+" "+getRutaSvn());
			comentario = toString(proceso.getInputStream());
			System.out.println(comentario);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String toString(InputStream inputStream) throws IOException{
		  StringBuilder textBuilder = new StringBuilder();
	    try (Reader reader = new BufferedReader(new InputStreamReader
	    	      (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
	    	        int c = 0;
	    	        while ((c = reader.read()) != -1) {
	    	            textBuilder.append((char) c);
	    	        }
	    	    }
	    return textBuilder.toString();

	}
}
