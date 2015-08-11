package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DESPLIEGE_VERSION_DUENO")
@NamedQueries({
	@NamedQuery(name="buscarPorVersion.duenoFilaVersion", query="SELECT E FROM EntFilaDespliegueVersionDueno E Join E.despliegueVersion D where D.version = :ver")
}
)

public class EntFilaDespliegueVersionDueno extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 5239345670233534356L;


	@JoinColumn
	@ManyToOne
	private EntFilaDespliegueVersion despliegueVersion;

	@JoinColumn
	@ManyToOne
	private EntPersona dueno;

	public EntFilaDespliegueVersion getDespliegueVersion() {
		return despliegueVersion;
	}

	public void setDespliegueVersion(EntFilaDespliegueVersion version) {
		this.despliegueVersion = version;
	}

	public EntPersona getDueno() {
		return dueno;
	}

	public void setDueno(EntPersona dueno) {
		this.dueno = dueno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dueno == null) ? 0 : dueno.hashCode());
		result = prime * result + ((despliegueVersion == null) ? 0 : despliegueVersion.hashCode());
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
		if (!(obj instanceof EntFilaDespliegueVersionDueno)) {
			return false;
		}
		EntFilaDespliegueVersionDueno other = (EntFilaDespliegueVersionDueno) obj;
		if (dueno == null) {
			if (other.dueno != null) {
				return false;
			}
		} else if (!dueno.equals(other.dueno)) {
			return false;
		}
		if (despliegueVersion == null) {
			if (other.despliegueVersion != null) {
				return false;
			}
		} else if (!despliegueVersion.equals(other.despliegueVersion)) {
			return false;
		}
		return true;
	}
}