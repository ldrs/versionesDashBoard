package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="REPORTE_SCRIPT_PARAMETROS")
@NamedQuery(name ="buscaPorPadre.reporteScriptParametros", query = "SELECT E from EntReporteScriptParametros E where E.reporteScript = :rep")
public class EntReporteScriptParametros extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 6345729907422853476L;

	@JoinColumn
	@ManyToOne
	private EntReporteScript reporteScript;

	private String nombre;

	private String valor;

	public EntReporteScript getReporteScript() {
		return reporteScript;
	}

	public void setReporteScript(EntReporteScript reporteScript) {
		this.reporteScript = reporteScript;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((reporteScript == null) ? 0 : reporteScript.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof EntReporteScriptParametros)) {
			return false;
		}
		EntReporteScriptParametros other = (EntReporteScriptParametros) obj;
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (reporteScript == null) {
			if (other.reporteScript != null) {
				return false;
			}
		} else if (!reporteScript.equals(other.reporteScript)) {
			return false;
		}
		if (valor == null) {
			if (other.valor != null) {
				return false;
			}
		} else if (!valor.equals(other.valor)) {
			return false;
		}
		return true;
	}




}