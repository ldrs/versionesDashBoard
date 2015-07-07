package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VERSION_DUENOS")
@NamedQueries(
		{
			@NamedQuery(name="buscar.versionDuenosPorVersion", query="select E from EntVersionDuenos E where E.version = :ver"),
			@NamedQuery(name="buscar.versionDuenos", query="select E from EntVersionDuenos E order by E.version.momentoCreacion desc")

		}

		)
public class EntVersionDuenos extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -1463334352869305962L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;


	@JoinColumn
	@ManyToOne
	private EntPersona dueno;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
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
		if (!(obj instanceof EntVersionDuenos)) {
			return false;
		}
		EntVersionDuenos other = (EntVersionDuenos) obj;
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