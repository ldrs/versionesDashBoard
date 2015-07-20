package rd.huma.dashboard.model.transaccional;

import java.time.LocalDate;

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

	private LocalDate fechaRegistro = LocalDate.now();

	private LocalDate ultimaActualizacion = LocalDate.now();

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

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public LocalDate getUltimaActualizacion() {
		return ultimaActualizacion;
	}

	public void setUltimaActualizacion(LocalDate ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}




}