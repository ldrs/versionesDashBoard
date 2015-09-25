package rd.huma.dashboard.servicios.integracion.svn.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraEnComentario;
import rd.huma.dashboard.util.IOUtil;

public class ServicioSvnBuscaOrigenBranch {

	private static final String PATRON_ORIGEN_SVN = "(from ";

	private String url;
	private String llaveJira;

	private String origen;

	private String revision;

	private String branch;

	public ServicioSvnBuscaOrigenBranch(String url,String llaveJira, String branch) {
		this.url = url;
		this.llaveJira = llaveJira;
		this.branch = branch;
	}

	public SVNOrigenBranch getOrigen(){
		SVNOrigenBranch svnOrigen = new SVNOrigenBranch();
		String comentario =  buscaComentarioSVNDesdeInicioBranch();
		interpretacionOrigen(comentario);
		svnOrigen.setJiraEncontrados(BuscadorJiraEnComentario.of(comentario, llaveJira).encuentraJira());
		svnOrigen.setRevision(Long.valueOf(revision));
		svnOrigen.setOrigenBranch(origen);

		for (EntJira jira : svnOrigen.getJiras()){
			if (!jira.getNumero().contains(llaveJira)){
				jira.setNumero(llaveJira+"-"+jira.getNumero());
			}
		}


		return svnOrigen;
	}

	private void interpretacionOrigen(String log){
		String patron = "/branches/"+branch+" "+PATRON_ORIGEN_SVN;
		int index = log.indexOf(patron);
		if (index!=-1){
			log = log.substring(index+patron.length());
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