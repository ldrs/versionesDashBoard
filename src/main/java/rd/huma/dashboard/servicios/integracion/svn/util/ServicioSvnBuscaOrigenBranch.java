package rd.huma.dashboard.servicios.integracion.svn.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraEnComentario;
import rd.huma.dashboard.util.IOUtil;

public class ServicioSvnBuscaOrigenBranch {

	private static final String PATRON_ORIGEN_SVN = "( from ";

	private String url;
	private String llaveJira;

	private String origen;

	private String revision;

	public ServicioSvnBuscaOrigenBranch(String url,String llaveJira) {
		this.url = url;
		this.llaveJira = llaveJira;
	}

	public SVNOrigenBranch getOrigen(){
		SVNOrigenBranch svnOrigen = new SVNOrigenBranch();
		String comentario =  buscaComentarioSVNDesdeInicioBranch();
		interpretacionOrigen(comentario);
		svnOrigen.setJiraEncontrados(BuscadorJiraEnComentario.of(comentario, llaveJira).encuentraJira());
		svnOrigen.setRevision(Long.valueOf(revision));
		svnOrigen.setOrigenBranch(origen);

		return svnOrigen;
	}

	private void interpretacionOrigen(String log){
		int index = log.indexOf(PATRON_ORIGEN_SVN);
		if (index!=-1){
			log = log.substring(index+PATRON_ORIGEN_SVN.length());
			int indexDosPuntos = log.indexOf(":");
			this.origen = log.substring(0, indexDosPuntos);
			this.revision = log.substring(indexDosPuntos+1);
			revision = revision.substring(0, revision.indexOf(')'));
		}

	}

	private String  buscaComentarioSVNDesdeInicioBranch(){
		try {
			Process proceso = Runtime.getRuntime().exec("svn log --stop-on-copy --verbose "+ url);
			proceso.waitFor(10, TimeUnit.SECONDS);

			return IOUtil.toString(proceso.getInputStream());
		} catch (IOException | InterruptedException e) {
			throw new IllegalStateException("No pudo ser encontrado el log.");
		}
	}
}