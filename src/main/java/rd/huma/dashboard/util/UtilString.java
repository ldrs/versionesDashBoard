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

	public static String defecto(CharSequence valor){
		if (valor == null){
			return "";
		}
		return valor.toString();
	}



	public static String defecto(String valor){
		if (valor == null){
			return "";
		}
		return valor;
	}

	public static String subStringDespues(String valor, String llaveBuscar){
		if (valor == null){
			return null;
		}
		if (llaveBuscar == null){
			return valor;
		}
		int index = valor.indexOf(llaveBuscar);
		if (index==-1){
			return valor;
		}
		return valor.substring(index+llaveBuscar.length());
	}

	public static boolean contiene(String valor, String buscar){
		if (valor == null){
			return false;
		}
		if (buscar == null){
			return false;
		}
		return valor.contains(valor);
	}

	public static boolean noContiene(String valor, String buscar){
		if (valor == null){
			return true;
		}
		if (buscar == null){
			return false;
		}
		return !valor.contains(buscar);
	}
}