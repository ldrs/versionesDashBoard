package rd.huma.dashboard.model;

import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SERVIDOR")
public class EntServidor extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -305284689798835460L;


	private String nombre;

	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;

	//Configuraciones
	private String rutaEntrada;

	private String usuario;
	private String password;

	private String baseDatos;

	//Estados
	@Enumerated(EnumType.STRING)
	private EEstadoServidor estadoServidor = EEstadoServidor.LIBRE;

	@JoinColumn
	@ManyToOne
	private EntVersion versionActual;


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public EntAmbiente getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}
	public String getRutaEntrada() {
		return rutaEntrada;
	}
	public void setRutaEntrada(String rutaEntrada) {
		this.rutaEntrada = rutaEntrada;
	}
	public EEstadoServidor getEstadoServidor() {
		return estadoServidor;
	}
	public void setEstadoServidor(EEstadoServidor estadoServidor) {
		this.estadoServidor = estadoServidor;
	}
	public EntVersion getVersionActual() {
		return versionActual;
	}
	public void setVersionActual(EntVersion versionActual) {
		this.versionActual = versionActual;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return new String(Base64.getDecoder().decode(password.getBytes())).substring(getId().length());
	}
	public void setPassword(String password) {
		this.password =  new String(Base64.getEncoder().encode((getId()+password).getBytes()));
	}

	public String getBaseDatos() {
		return baseDatos;
	}
	public void setBaseDatos(String baseDatos) {
		this.baseDatos = baseDatos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((ambiente == null) ? 0 : ambiente.hashCode());
		result = prime * result
				+ ((baseDatos == null) ? 0 : baseDatos.hashCode());
		result = prime * result
				+ ((estadoServidor == null) ? 0 : estadoServidor.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((rutaEntrada == null) ? 0 : rutaEntrada.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result
				+ ((versionActual == null) ? 0 : versionActual.hashCode());
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
		if (!(obj instanceof EntServidor)) {
			return false;
		}
		EntServidor other = (EntServidor) obj;
		if (ambiente == null) {
			if (other.ambiente != null) {
				return false;
			}
		} else if (!ambiente.equals(other.ambiente)) {
			return false;
		}
		if (baseDatos == null) {
			if (other.baseDatos != null) {
				return false;
			}
		} else if (!baseDatos.equals(other.baseDatos)) {
			return false;
		}
		if (estadoServidor != other.estadoServidor) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (rutaEntrada == null) {
			if (other.rutaEntrada != null) {
				return false;
			}
		} else if (!rutaEntrada.equals(other.rutaEntrada)) {
			return false;
		}
		if (usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!usuario.equals(other.usuario)) {
			return false;
		}
		if (versionActual == null) {
			if (other.versionActual != null) {
				return false;
			}
		} else if (!versionActual.equals(other.versionActual)) {
			return false;
		}
		return true;
	}
}