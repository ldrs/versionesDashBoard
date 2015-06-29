package rd.huma.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_PROPIEDADES")
public class EntVersionPropiedades extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 3872254020389170735L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	private String propiedad;

	private String valor;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public String getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
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
		result = prime * result
				+ ((propiedad == null) ? 0 : propiedad.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		if (!(obj instanceof EntVersionPropiedades)) {
			return false;
		}
		EntVersionPropiedades other = (EntVersionPropiedades) obj;
		if (propiedad == null) {
			if (other.propiedad != null) {
				return false;
			}
		} else if (!propiedad.equals(other.propiedad)) {
			return false;
		}
		if (valor == null) {
			if (other.valor != null) {
				return false;
			}
		} else if (!valor.equals(other.valor)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}
}