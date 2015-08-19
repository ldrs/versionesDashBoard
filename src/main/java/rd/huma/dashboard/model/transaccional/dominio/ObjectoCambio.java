package rd.huma.dashboard.model.transaccional.dominio;

import java.util.List;
import java.util.ArrayList;

public class ObjectoCambio {

	private String nombre;

	private List<String> columnas = new ArrayList<>();

	private ETipoCambioTabla cambioTabla;

	ObjectoCambio(ETipoCambioTabla cambioTabla ,String nombre, List<String> columnas) {
		this.nombre = nombre;
		this.columnas = columnas;
		this.cambioTabla = cambioTabla;
	}

	public String getNombre() {
		return nombre;
	}

	public List<String> getColumnas() {
		return columnas;
	}

	public String getColumnastoString(){
		if (columnas == null || columnas.isEmpty()){
			return "";
		}
		StringBuilder texto = new StringBuilder(150);
		for (String columna : columnas) {
			texto.append(columna).append(',');
		}
		texto.deleteCharAt(texto.length()-1);
		return texto.toString();
	}

	public ETipoCambioTabla getCambioTabla() {
		return cambioTabla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cambioTabla == null) ? 0 : cambioTabla.hashCode());
		result = prime * result
				+ ((columnas == null) ? 0 : columnas.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ObjectoCambio)) {
			return false;
		}
		ObjectoCambio other = (ObjectoCambio) obj;
		if (cambioTabla != other.cambioTabla) {
			return false;
		}
		if (columnas == null) {
			if (other.columnas != null) {
				return false;
			}
		} else if (!columnas.equals(other.columnas)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		return true;
	}


}