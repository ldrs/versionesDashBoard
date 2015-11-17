package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="APLICACION_SUB_MODULO")
@NamedQueries({
	@NamedQuery(name="buscarSinPadre.subModulo",query="SELECT E FROM EntAplicacionSubModulo E where E.modulo = :mod and E.nombre = :nom"),
	@NamedQuery(name="buscarConPadre.subModulo",query="SELECT E FROM EntAplicacionSubModulo E where E.modulo = :mod and E.nombre = :nom and E.subModuloPadre = :pde")

})
public class EntAplicacionSubModulo extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = -5603589968157191418L;


	@JoinColumn
	@ManyToOne
	private EntAplicacionModulo modulo;

	@JoinColumn
	@ManyToOne
	private EntAplicacionSubModulo subModuloPadre;

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setModulo(EntAplicacionModulo modulo) {
		this.modulo = modulo;
	}

	public EntAplicacionModulo getModulo() {
		return modulo;
	}


	public void setSubModuloPadre(EntAplicacionSubModulo subModuloPadre) {
		this.subModuloPadre = subModuloPadre;
	}

	public EntAplicacionSubModulo getSubModuloPadre() {
		return subModuloPadre;
	}
}