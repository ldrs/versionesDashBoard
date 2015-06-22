package rd.huma.dashboard.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SERVIDOR")
public class EntServidor implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -305284689798835460L;

	@Id
	private String id = UUID.randomUUID().toString();


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
	public String getId() {
		return id;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return new String(Base64.getDecoder().decode(password.getBytes())).substring(id.length());
	}
	public void setPassword(String password) {
		this.password =  new String(Base64.getEncoder().encode((id+password).getBytes()));
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
		int result = 1;
		result = prime * result
				+ ((ambiente == null) ? 0 : ambiente.hashCode());
		result = prime * result
				+ ((estadoServidor == null) ? 0 : estadoServidor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((rutaEntrada == null) ? 0 : rutaEntrada.hashCode());
		result = prime * result
				+ ((versionActual == null) ? 0 : versionActual.hashCode());
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
		if (estadoServidor != other.estadoServidor) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (rutaEntrada == null) {
			if (other.rutaEntrada != null) {
				return false;
			}
		} else if (!rutaEntrada.equals(other.rutaEntrada)) {
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
