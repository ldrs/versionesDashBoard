package rd.huma.dashboard.model.transaccional;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="APLICACION_CATALOGO_CAMBIO")
public class EntAplicacionCatalogoCambio extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 1148477234208274007L;

	@JoinColumn
	@ManyToOne
 	private EntAplicacion aplicacion;


	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

}