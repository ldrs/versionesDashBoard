package rd.huma.dashboard.model.transaccional;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="REPOSITORIO_DATOS")
@NamedQueries({@NamedQuery(name = "buscar.repositorioDatos", query = "SELECT E from EntRepositorioDatos E where E.schema = :sc and E.servicio = :serv")})

public class EntRepositorioDatos extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -2845525405887083013L;

	private String schema;

	private String servicio;

	private String host;

	private int puerto = 1521;

	private LocalDateTime fechaRegistro = LocalDateTime.now();

	private LocalDateTime ultimaActualizacion = LocalDateTime.now();

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public LocalDateTime getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPuerto() {
		return puerto;
	}
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
}