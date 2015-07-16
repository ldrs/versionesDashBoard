package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="GRUPO_DETALLE", uniqueConstraints = {@UniqueConstraint(columnNames = {"grupoPersona","persona"})})
@NamedQueries({
	@NamedQuery(name="busca.detalleGrupo", query="SELECT E FROM EntGrupoPersonaDetalle E where E.grupoPersona = :grupo and E.persona = :persona"),
	@NamedQuery(name="buscaPorGrupo.detalleGrupo", query="SELECT E FROM EntGrupoPersonaDetalle E join E.grupo G where G.id = :idGrupo")
				})
public class EntGrupoPersonaDetalle implements Comparable<EntGrupoPersonaDetalle> {

	@JoinColumn
	@ManyToOne
	private EntGrupoPersona grupoPersona;


	@JoinColumn
	@ManyToOne
	private EntPersona persona;


	public EntGrupoPersona getGrupoPersona() {
		return grupoPersona;
	}


	public void setGrupoPersona(EntGrupoPersona grupoPersona) {
		this.grupoPersona = grupoPersona;
	}


	public EntPersona getPersona() {
		return persona;
	}


	public void setPersona(EntPersona persona) {
		this.persona = persona;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((grupoPersona == null) ? 0 : grupoPersona.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EntGrupoPersonaDetalle)) {
			return false;
		}
		EntGrupoPersonaDetalle other = (EntGrupoPersonaDetalle) obj;
		if (grupoPersona == null) {
			if (other.grupoPersona != null) {
				return false;
			}
		} else if (!grupoPersona.equals(other.grupoPersona)) {
			return false;
		}
		if (persona == null) {
			if (other.persona != null) {
				return false;
			}
		} else if (!persona.equals(other.persona)) {
			return false;
		}
		return true;
	}


	@Override
	public int compareTo(EntGrupoPersonaDetalle o) {
		return o.grupoPersona.compareTo(o.grupoPersona) + o.persona.compareTo(o.persona);
	}
}