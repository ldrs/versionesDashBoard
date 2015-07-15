package rd.huma.dashboard.model.transaccional;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="REPOSITORIO_DATOS")
public class EntRepositorioDatos extends AEntModelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2845525405887083013L;

	private String schema;
	
	private String servicio;
	
	private LocalDate fechaRegistro;
	
	private LocalDate ultimaActualizacion;

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