package rd.huma.dashboard.servicios.integracion.svn.util;

import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;

public class EncuentraUltimaRevision{
	private String llaveFind;
	private ServicioSVN servicio;
	private String branch;
	private long revision;

	public EncuentraUltimaRevision(ServicioSVN servicio, String branchToFind, String branch, long ultimaRevision) {
		llaveFind = "from /branches/"+branchToFind+"/";
		this.servicio = servicio;
		this.branch = branch;
		this.revision = ultimaRevision;
	}

	public long revision(){
		String comentario = servicio.buscaBranchPorRevision(branch, revision);
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
		return ultimaRevisionRetorno;
	}
}