package rd.huma.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Persona")
@NamedQueries({@NamedQuery(name="buscaPersonaSVN", query="SELECT E from EntPersona E where E.usuarioSVN = :usrSVN") })
public class EntPersona  extends AEntModelo {


	/**
	 *
	 */
	private static final long serialVersionUID = -8021702470379998822L;


	private String nombre;

	private String usuarioSvn;

	private String correo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuarioSvn() {
		return usuarioSvn;
	}

	public void setUsuarioSvn(String usuarioSvn) {
		this.usuarioSvn = usuarioSvn;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((usuarioSvn == null) ? 0 : usuarioSvn.hashCode());
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
		if (!(obj instanceof EntPersona)) {
			return false;
		}
		EntPersona other = (EntPersona) obj;
		if (correo == null) {
			if (other.correo != null) {
				return false;
			}
		} else if (!correo.equals(other.correo)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (usuarioSvn == null) {
			if (other.usuarioSvn != null) {
				return false;
			}
		} else if (!usuarioSvn.equals(other.usuarioSvn)) {
			return false;
		}
		return true;
	}



}