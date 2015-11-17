package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="APLICACION_MODULO")
@NamedQueries({
	@NamedQuery(name="buscar.modulo",query="SELECT E FROM EntAplicacionModulo E where E.aplicacion = :app and E.nombre = :nombre")
})
public class EntAplicacionModulo extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -1068260798068832949L;

	@JoinColumn
	@ManyToOne
	private EntAplicacion aplicacion;

	private String nombre;

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}