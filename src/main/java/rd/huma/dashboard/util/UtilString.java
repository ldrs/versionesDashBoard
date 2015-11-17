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
		return valor.contains(buscar);
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

	public static boolean sonIguales(String valor, String valor2){
		if (valor2 == valor){
			return true;
		}
		if (valor == null && valor2!=null){
			return false;
		}
		return valor.equals(valor2);
	}

	public static boolean sonDiferentes(String valor, String valor2){
		if (valor2 != valor){
			return true;
		}
		if (valor == null && valor2!=null){
			return true;
		}
		if (valor == null && valor2==null){
			return false;
		}
		return !valor.equals(valor2);
	}
}