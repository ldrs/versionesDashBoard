package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DEPLOYEMENT_VERSION_DUENO")
@NamedQueries({
	@NamedQuery(name="buscarPorVersion.duenoFilaVersion", query="SELECT E FROM EntFilaDeployementVersionDueno E Join E.version V where V.version = :ver")
}
)

public class EntFilaDeployementVersionDueno extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 5239345670233534356L;


	@JoinColumn
	@ManyToOne
	private EntFilaDeployementVersion version;

	@JoinColumn
	@ManyToOne
	private EntPersona dueno;

	public EntFilaDeployementVersion getVersion() {
		return version;
	}

	public void setVersion(EntFilaDeployementVersion version) {
		this.version = version;
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
		if (!(obj instanceof EntFilaDeployementVersionDueno)) {
			return false;
		}
		EntFilaDeployementVersionDueno other = (EntFilaDeployementVersionDueno) obj;
		if (dueno == null) {
			if (other.dueno != null) {
				return false;
			}
		} else if (!dueno.equals(other.dueno)) {
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