package rd.huma.dashboard.servicios.integracion.svn.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class BuscadorMergeInfoEnComentario {
	
	private static final Pattern PATRON_BRANCH = Pattern.compile("\\/branches\\/\\w*\\/");
	
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
			String found = m.group();
			if (!found.contains(branch)){
				branches.add(found);
			}
		}

		return branches;
	}

}
