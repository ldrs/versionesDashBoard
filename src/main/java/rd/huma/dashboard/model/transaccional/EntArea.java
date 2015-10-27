package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="AREA")
public class EntArea extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -8318896757196544742L;

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
}