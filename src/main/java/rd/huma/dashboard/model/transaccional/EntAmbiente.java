package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="AMBIENTE")
public class EntAmbiente extends AEntModelo implements Comparable<EntAmbiente>{

	/**
	 *
	 */
	private static final long serialVersionUID = -1024459181922006312L;


	private String nombre;

	private int orden;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + orden;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof EntAmbiente)) {
			return false;
		}
		EntAmbiente other = (EntAmbiente) obj;
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (orden != other.orden) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(EntAmbiente o2) {
		return Integer.compare(orden, o2.orden) ;
	}
}