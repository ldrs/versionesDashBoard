package rd.huma.dashboard.servicios.integracion.svn.util;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.servicios.background.ejecutores.svn.EjecutorRevisaOrigenBranch;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraEnComentario;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class ServicioSvnBuscaOrigenBranch {

	private static final String PATRON_ORIGEN_SVN = "(from ";


	private String llaveJira;

	private String origen;

	private String revision;

	private String branch;

	private EntAplicacion aplicacion;

	public ServicioSvnBuscaOrigenBranch(String branch, EntAplicacion aplicacion) {
		this.llaveJira = aplicacion.getJiraKey();
		this.branch = branch;
		this.aplicacion = aplicacion;
	}


	public SVNOrigenBranch getOrigen(String comentario){
		SVNOrigenBranch svnOrigen = new SVNOrigenBranch();
		interpretacionOrigen(comentario);
		svnOrigen.setJiraEncontrados(BuscadorJiraEnComentario.of(comentario, llaveJira).encuentraJira());
		svnOrigen.setRevision(Long.valueOf(revision));
		svnOrigen.setOrigenBranch(origen);

		for (EntJira jira : svnOrigen.getJiras()){
			if (!jira.getNumero().contains(llaveJira)){
				jira.setNumero(llaveJira+"-"+jira.getNumero());
			}
		}
		if (origen.contains("branches")){
			ServicioVersion.getInstanciaTransaccional().ejecutarJob(new EjecutorRevisaOrigenBranch(svnOrigen, aplicacion));
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
}