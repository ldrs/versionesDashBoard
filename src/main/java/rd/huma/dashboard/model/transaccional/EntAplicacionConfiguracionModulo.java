package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="APLICACION_CONF_MODULO")
public class EntAplicacionConfiguracionModulo extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 3518968114724122467L;

	@JoinColumn
	@ManyToOne
 	private EntAplicacion aplicacion;

	private String token;

}