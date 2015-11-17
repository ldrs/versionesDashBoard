package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="APLICACION_CONF_MODULO")
@NamedQueries({
	@NamedQuery(name="busca.configModulo",query="SELECT E from EntAplicacionConfiguracionModulo E where E.aplicacion = :app")
})
public class EntAplicacionConfiguracionModulo extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 3518968114724122467L;

	@JoinColumn
	@ManyToOne
 	private EntAplicacion aplicacion;

	private String antesObjectivo;

	private String despuesObjectivo;

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getAntesObjectivo() {
		return antesObjectivo;
	}

	public void setAntesObjectivo(String antesObjectivo) {
		this.antesObjectivo = antesObjectivo;
	}

	public String getDespuesObjectivo() {
		return despuesObjectivo;
	}

	public void setDespuesObjectivo(String despuesObjectivo) {
		this.despuesObjectivo = despuesObjectivo;
	}
}