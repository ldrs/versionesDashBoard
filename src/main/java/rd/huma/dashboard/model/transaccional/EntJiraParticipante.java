package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoParticipante;

@Entity
@Table(name="JIRA_PARTICIPANTE")
@NamedQueries({@NamedQuery(name="jiraParticipante.buscar", query="Select E from EntJiraParticipante E join E.participante P join E.jira J where P.usuarioSVN = :usr and J.numero = :numJira")})
public class EntJiraParticipante extends AEntModelo implements Comparable<EntJiraParticipante>{

	/**
	 *
	 */
	private static final long serialVersionUID = -6546012185692906820L;


	@JoinColumn
	@ManyToOne
	private EntJira jira;


	@JoinColumn
	@ManyToOne
	private EntPersona participante;

	@Enumerated(EnumType.STRING)
	private ETipoParticipante tipo;

	public EntJira getJira() {
		return jira;
	}

	public void setJira(EntJira jira) {
		this.jira = jira;
	}

	public EntPersona getParticipante() {
		return participante;
	}

	public void setParticipante(EntPersona participante) {
		this.participante = participante;
	}

	public ETipoParticipante getTipo() {
		return tipo;
	}

	public void setTipo(ETipoParticipante tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jira == null) ? 0 : jira.hashCode());
		result = prime * result
				+ ((participante == null) ? 0 : participante.hashCode());
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
		if (!(obj instanceof EntJiraParticipante)) {
			return false;
		}
		EntJiraParticipante other = (EntJiraParticipante) obj;
		if (jira == null) {
			if (other.jira != null) {
				return false;
			}
		} else if (!jira.equals(other.jira)) {
			return false;
		}
		if (participante == null) {
			if (other.participante != null) {
				return false;
			}
		} else if (!participante.equals(other.participante)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(EntJiraParticipante o) {
		return jira.getNumero().compareTo(o.getJira().getNumero())
			+ participante.getUsuarioSvn().compareTo(o.getParticipante().getUsuarioSvn());
	}
}