package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_JIRA")
@NamedQueries({@NamedQuery(name="buscar.jiraNumero",query="SELECT E from EntJira E where e.numero = :numJira")})
public class EntJira extends AEntModelo implements Comparable<EntJira> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1463431661829014017L;


	private String numero;

	private String estado;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (!(obj instanceof EntJira)) {
			return false;
		}
		EntJira other = (EntJira) obj;
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(EntJira o) {
		return o.numero.compareTo(numero);
	}
}