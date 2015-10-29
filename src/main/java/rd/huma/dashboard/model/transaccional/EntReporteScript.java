package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoReporteScript;

@Entity
@Table(name="REPORTE_SCRIPT")
@NamedQuery(name="buscarActivos.reporte",query = "SELECT E FROM EntReporteScript E where E.habilitado = true order by orden")
public class EntReporteScript extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -3108725159196189432L;


	private String query;
	private String nombre;

	@Enumerated(EnumType.STRING)
	private ETipoReporteScript tipo;


	private boolean habilitado = true;
	private int orden;

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ETipoReporteScript getTipo() {
		return tipo;
	}

	public void setTipo(ETipoReporteScript tipo) {
		this.tipo = tipo;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
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
		if (!(obj instanceof EntReporteScript)) {
			return false;
		}
		EntReporteScript other = (EntReporteScript) obj;
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (query == null) {
			if (other.query != null) {
				return false;
			}
		} else if (!query.equals(other.query)) {
			return false;
		}
		return true;
	}
}