package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="APLICACION_CONF_SUB_MODULO")
public class EntAplicacionConfiguracionSubModulo extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 3518968114724122467L;

	@JoinColumn
	@ManyToOne
 	private EntAplicacion aplicacion;

	private String antes;

	public String getAntes() {
		return antes;
	}

	public void setAntes(String antes) {
		this.antes = antes;
	}

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

}