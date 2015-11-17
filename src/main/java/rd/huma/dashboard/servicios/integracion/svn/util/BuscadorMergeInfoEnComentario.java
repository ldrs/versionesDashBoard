package rd.huma.dashboard.servicios.integracion.svn.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BuscadorMergeInfoEnComentario {

	private static final Pattern PATRON_BRANCH = Pattern.compile("\\/branches\\/\\w.*\\/");

	private String comentario;
	private String branch;

	private BuscadorMergeInfoEnComentario(){}

	public static BuscadorMergeInfoEnComentario of(String comentario, String branch) {
		BuscadorMergeInfoEnComentario b = new BuscadorMergeInfoEnComentario();
		b.comentario = comentario;
		b.branch = branch;
		return b;
	}

	public Set<String> encuentra() {
		Matcher m = PATRON_BRANCH.matcher(comentario);
		Set<String> branches = new HashSet<>();
		while(m.find()){
			String found = tratarNombreBranch(m.group());
			if (!found.contains(branch)){
				branches.add(found);
			}
		}

		return branches;
	}

	private String tratarNombreBranch(String temporal){
		int index =  temporal.indexOf("/branches/");
		if (index==-1){
			index =  temporal.indexOf("/tags/");
			if (index == -1){
				return temporal;
			}
			temporal = temporal.substring(index+6);
			return temporal.substring(0,temporal.indexOf('/'));

		}else{
			temporal = temporal.substring(index+10);
			return temporal.substring(0,temporal.indexOf('/'));
		}
	}
}