package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VERSION_PARTICIPANTES")
@NamedQueries(
		{
			@NamedQuery(name="buscar.versionParticipantesPorVersion", query="select E from EntVersionParticipante E where E.version = :ver"),
			@NamedQuery(name="buscar.versionParticipantes", query="select E from EntVersionParticipante E order by E.version.fechaRegistro desc"),
			@NamedQuery(name="buscarVersionParticipante.versionParticipantes", query="select E from EntVersionParticipante E where E.version = :ver and E.participante = :par")

		}

		)


public class EntVersionParticipante extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -1463334352869305962L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;


	@JoinColumn
	@ManyToOne
	private EntPersona participante;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public EntPersona getParticipante() {
		return participante;
	}

	public void setParticipante(EntPersona dueno) {
		this.participante = dueno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((participante == null) ? 0 : participante.hashCode());
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
		if (!(obj instanceof EntVersionParticipante)) {
			return false;
		}
		EntVersionParticipante other = (EntVersionParticipante) obj;
		if (participante == null) {
			if (other.participante != null) {
				return false;
			}
		} else if (!participante.equals(other.participante)) {
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