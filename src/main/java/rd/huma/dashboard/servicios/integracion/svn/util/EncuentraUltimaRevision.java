package rd.huma.dashboard.servicios.integracion.svn.util;

import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;

public class EncuentraUltimaRevision{

//	private static final Logger LOGGER =  Logger.getLogger(EncuentraUltimaRevision.class.getSimpleName());
	private String llaveFind;
	private ServicioSVN servicio;
	private String branch;
	private long revision;
	private String branchEncontrar;

	public EncuentraUltimaRevision(ServicioSVN servicio, String branchToFind, String branch, long ultimaRevision) {
		llaveFind = "from /branches/"+branchToFind+"/";
		this.branchEncontrar=branchToFind;
		this.servicio = servicio;
		this.branch = branch;
		this.revision = ultimaRevision;
	}

	public long revision(){
		String comentarioOriginal = servicio.buscaBranchPorRevision(branch, revision);
		String comentario = comentarioOriginal;
		long ultimaRevisionRetorno = 0;
		int index;
		while ((index = comentario.indexOf(llaveFind))!=-1){
			comentario = comentario.substring(index+llaveFind.length());
			int indexDos = comentario.indexOf(':');
			int futuro = comentario.indexOf(llaveFind);
			if (indexDos<futuro){
				String tmp = comentario.substring(indexDos+1);
				int indexFind = tmp.indexOf(')');
				if (indexFind!=-1){
					tmp = tmp.substring(0,indexFind);
				}
				 try{
					 long encontrada = Long.parseLong(tmp);
					 if (encontrada>ultimaRevisionRetorno){
						 ultimaRevisionRetorno= encontrada;
					 }

				 }catch(Exception e){}

			}
		}
		if (ultimaRevisionRetorno == 0){
			return buscaHaciendoSplit(comentarioOriginal);
		}

		return ultimaRevisionRetorno;
	}

	private long buscaHaciendoSplit(String comentarioOriginal) {
		long ultimaRevisionRetorno = 0;
		String[] commits = comentarioOriginal.split("------------------------------------------------------------------------");
	//	LOGGER.info(String.format("Cantidad de commits (%s) para el branch (%s) ",commits.length,branch));

		for (String commit : commits) {
			if (commit.contains(branchEncontrar)){
				int index = commit.indexOf('r')+1;
				String revTmp = commit.substring(index);
				revTmp = revTmp.substring(0, revTmp.indexOf(' '));
				 try{
					 long encontrada = Long.parseLong(revTmp);
					 if (encontrada>ultimaRevisionRetorno){
						 ultimaRevisionRetorno= encontrada;
					 }

				 }catch(Exception e){}
			}
		}

		return ultimaRevisionRetorno;
	}
}