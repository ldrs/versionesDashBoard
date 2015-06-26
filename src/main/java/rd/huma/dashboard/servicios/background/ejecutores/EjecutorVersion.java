package rd.huma.dashboard.servicios.background.ejecutores;

import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;

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


	private void  encuentraComentario(){
		try {
			SVNURL svnUrl = SVNURL.parseURIEncoded(configuracionGeneral.getRutaSvn() + version.getSvnOrigen() + "/branches/" + version.getBranchOrigen());

			SVNClientManager clientManager = SVNClientManager.newInstance();
			SVNRevision revicion =  SVNRevision.create(Long.valueOf(version.getRevisionSVN())) ;
			SVNLogClient logClient=clientManager.getLogClient();
			logClient.doLog(svnUrl,new String[]{"."},revicion,revicion,SVNRevision.create(0),true,true,10,new ISVNLogEntryHandler(){
				@Override 
				public void handleLogEntry(      SVNLogEntry logEntry) {
					comentario = logEntry.getMessage();
				}
			}
					);
		} catch (SVNException e) {}
	}
}
