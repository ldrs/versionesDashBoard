package rd.huma.dashboard.model.transaccional;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoCambioTabla;

@Entity
@Table(name="VERSION_CAMBIO_OBJECTO_SQL")
public class EntVersionCambioObjectoSql extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -5638323333904827133L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	@JoinColumn
	@ManyToOne
	private EntVersionScript script;

	private String objecto;

	@Enumerated
	private ETipoCambioTabla tipo;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	private String columnas;

	private int cantidad;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public String getObjecto() {
		return objecto;
	}

	public void setObjecto(String objecto) {
		this.objecto = objecto;
	}

	public ETipoCambioTabla getTipo() {
		return tipo;
	}

	public void setTipo(ETipoCambioTabla tipo) {
		this.tipo = tipo;
	}

	public EntVersionScript getScript() {
		return script;
	}

	public void setScript(EntVersionScript script) {
		this.script = script;
	}

	public String getColumnas() {
		return columnas;
	}

	public void setColumnas(String columnas) {
		this.columnas = columnas;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}