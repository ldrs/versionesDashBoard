package rd.huma.dashboard.model.transaccional;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="APLICACION_CATALOGO_CAMBIO")
@NamedQueries({
	@NamedQuery(name="busca.catalogoCambio",query="SELECT E from EntAplicacionCatalogoCambio E where E.aplicacion = :app")
})
public class EntAplicacionCatalogoCambio extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 1148477234208274007L;

	@JoinColumn
	@ManyToOne
 	private EntAplicacion aplicacion;

	@JoinColumn
	@ManyToOne
	private EntAplicacionCatalogoCambio padre;

	private boolean agrupador;

	private String valor;

	private boolean otro;

	private String nombre;

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setOtro(boolean otro) {
		this.otro = otro;
	}

	public boolean isOtro() {
		return otro;
	}

	public boolean isAgrupador() {
		return agrupador;
	}

	public void setAgrupador(boolean agrupador) {
		this.agrupador = agrupador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}