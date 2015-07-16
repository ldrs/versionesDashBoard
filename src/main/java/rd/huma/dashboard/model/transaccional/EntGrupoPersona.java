package rd.huma.dashboard.model.transaccional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="GRUPO_PERSONA")
@NamedQueries({@NamedQuery(name="todos.grupoPersona" ,query = "SELECT E from EntGrupoPersona E")})
public class EntGrupoPersona extends AEntModelo implements Comparable<EntGrupoPersona> {

	/**
	 *
	 */
	private static final long serialVersionUID = 3121998813137437578L;

	@Column(unique=true)
	private String grupo;

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public int compareTo(EntGrupoPersona o) {
		return grupo.compareTo(o.grupo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
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
		if (!(obj instanceof EntGrupoPersona)) {
			return false;
		}
		EntGrupoPersona other = (EntGrupoPersona) obj;
		if (grupo == null) {
			if (other.grupo != null) {
				return false;
			}
		} else if (!grupo.equals(other.grupo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return grupo;
	}
}