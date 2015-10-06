package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="REPOSITORIO_DATOS")
@NamedQueries({
	@NamedQuery(name = "buscar.repositorioDatos", query = "SELECT E from EntRepositorioDatos E where E.esquema = :sc and E.servicio = :serv"),
	@NamedQuery(name = "buscarPorAll.repositorioDatos", query = "SELECT E from EntRepositorioDatos E where E.host = :host and E.esquema = :sc and E.servicio = :serv")
	})

public class EntRepositorioDatos extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -2845525405887083013L;

	private String esquema;

	private String servicio;

	private String host;

	private int puerto = 1521;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaActualizacion = Timestamp.from(Instant.now());

	public String getSchema() {
		return esquema;
	}

	public void setSchema(String schema) {
		this.esquema = schema;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
	}

	public Instant getUltimaActualizacion() {
		return ultimaActualizacion.toInstant();
	}

	public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
		this.ultimaActualizacion = Timestamp.valueOf(ultimaActualizacion);
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

	@Override
	public String toString() {
		return new StringBuilder(150).append(host).append(':').append(puerto).append('/').append(servicio).append('.').append(esquema).toString();
	}
}