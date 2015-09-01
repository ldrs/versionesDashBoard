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

/**
 *
 *
 Query para analizar buscarVersionParticipante.versionParticipantes


 select GD.persona_id, P.usuarioSvn  , E.alerta , GP.grupo
   from   VERSION_ALERTA_HISTORICA E,
     CONFIGURACION_NOTIFICACION C,     GRUPO_DETALLE GD,
     PERSONA P ,
     GRUPO_PERSONA GP
 WHERE
 C.alerta = E.alerta
 AND C.ambiente_id = E.ambiente_id
 AND GD.grupoPersona_id = C.grupoPersona_id
 AND P.id = GD.persona_id
 AND GP.id = GD.grupoPersona_id
 AND GP.id = C.grupoPersona_id
 AND E.id= ?
 AND (C.alertaPorAmbiente=true or   exists(select 1 from VERSION_PARTICIPANTES D where D.participante_id = GD.persona_id and D.version_id=E.version_id) );


 *
 */
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