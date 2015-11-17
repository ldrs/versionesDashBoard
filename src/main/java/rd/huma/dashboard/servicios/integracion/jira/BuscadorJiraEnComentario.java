package rd.huma.dashboard.servicios.integracion.jira;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rd.huma.dashboard.model.transaccional.EntJira;

public class BuscadorJiraEnComentario {

	private static final String NUMERICO = "[0-9]+";

	private String comentario;
	private String llaveJira;
	private boolean agregaKey;

	private BuscadorJiraEnComentario(String comentario, String llaveJira) {
		this.comentario = comentario;
		this.llaveJira = llaveJira+"-";
	}

	public List<EntJira> encuentraJira(){
		return encuentraJira(comentario);
	}


	public List<EntJira> encuentraJira(String comentario){
		if (comentario==null || comentario.isEmpty()){
			return Collections.emptyList();
		}
		List<EntJira> lst = new ArrayList<>();
		int indice;

		String restanteComentario = comentario;
		while ( (indice=restanteComentario.indexOf(llaveJira))!=-1 ){
			restanteComentario = restanteComentario.substring(indice+llaveJira.length());
			int posicionNumero = -1;
			for (int c=0; c<restanteComentario.length();c++){
				if (restanteComentario.substring(c, c+1) .matches(NUMERICO)){
					if (posicionNumero==-1){
						posicionNumero = c;
					}
				}else{
					if (posicionNumero!=-1){
						EntJira jira = new EntJira();
						String numero = restanteComentario.substring(posicionNumero, c);
						jira.setNumero(agregaKey? llaveJira+numero : numero );
						lst.add(jira);
						break;
					}
				}
			}
		}

		return lst;
	}


	public static BuscadorJiraEnComentario of(String comentario, String llaveJira){
		return new BuscadorJiraEnComentario(comentario, llaveJira);
	}

	public static BuscadorJiraEnComentario of(String llaveJira){
		BuscadorJiraEnComentario tmp = new BuscadorJiraEnComentario(null, llaveJira);
		tmp.agregaKey = true;
		return tmp;
	}

}