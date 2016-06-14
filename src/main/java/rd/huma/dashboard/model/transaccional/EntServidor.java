package rd.huma.dashboard.model.transaccional;

import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rd.huma.dashboard.model.transaccional.dominio.EEstadoServidor;

@Entity
@Table(name="SERVIDOR")
@JsonIgnoreProperties({ "usuario", "password" })
@NamedQueries({
			@NamedQuery(name="buscar.servidor",query = "SELECT E from EntServidor E join E.ambiente A  where A.id = :amb and apagado = false"),
			@NamedQuery(name="buscarPorBranch.servidor",query = "SELECT E from EntServidor E join E.versionActual V  where V.branchOrigen = :branch"),
			@NamedQuery(name="buscarPorVersion.servidor",query = "SELECT E from EntServidor E join E.versionActual V  where V.id = :id"),
			@NamedQuery(name="buscarPorRepositorio.servidor",query = "SELECT E from EntServidor E  where E.baseDatos = :bsd"),
			@NamedQuery(name="buscarTodos.servidor", query ="SELECT S FROM EntServidor S"),
			@NamedQuery(name="buscarPorID.servidor", query ="SELECT e FROM EntServidor e WHERE e.id = :id")
		})
public class EntServidor extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -305284689798835460L;


	private String nombre;

	@JoinColumn
	@ManyToOne
	private EntAmbienteAplicacion ambiente;

	//Configuraciones
	private String rutaEntrada;

	private String usuario;
	private String password;

	private String nombreServidorJenkins;


	@JoinColumn
	@ManyToOne
	private EntRepositorioDatos baseDatos;

	//Estados
	@Enumerated(EnumType.STRING)
	private EEstadoServidor estadoServidor = EEstadoServidor.LIBRE;

	@JoinColumn
	@ManyToOne
	private EntVersion versionActual;

	private boolean apagado;


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public EntAmbienteAplicacion getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(EntAmbienteAplicacion ambiente) {
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
		return password == null ? null : new String(Base64.getDecoder().decode(password.getBytes())).substring(getId().length());
	}
	public void setPassword(String password) {
		this.password =  new String(Base64.getEncoder().encode((getId()+password).getBytes()));
	}
	public EntRepositorioDatos getBaseDatos() {
		return baseDatos;
	}
	public void setBaseDatos(EntRepositorioDatos baseDatos) {
		this.baseDatos = baseDatos;
	}

	public String getNombreServidorJenkins() {
		return nombreServidorJenkins;
	}
	public void setNombreServidorJenkins(String nombreServidorJenkins) {
		this.nombreServidorJenkins = nombreServidorJenkins;
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

	public void setApagado(boolean apagado) {
		this.apagado = apagado;
	}

	public boolean isApagado() {
		return apagado;
	}

}