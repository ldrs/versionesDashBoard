package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="APLICACION_SUB_MODULO")
public class EntAplicacionSubModulo extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = -5603589968157191418L;


	@JoinColumn
	@ManyToOne
	private EntAplicacionModulo modulo;


	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
