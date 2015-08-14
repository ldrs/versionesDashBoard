package rd.huma.dashboard.util;

public class UtilString {

	private UtilString(){
	}

	public static final String miniscula(String valor){
		if (valor == null){
			return "";
		}
		return valor.toLowerCase();
	}

}