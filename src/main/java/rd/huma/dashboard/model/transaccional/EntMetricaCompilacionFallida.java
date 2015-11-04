package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="METRICA_COMPILACION_FALLIDA")
public class EntMetricaCompilacionFallida extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 6173577294475759984L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	private EntPersona autor;

	private EntAplicacion aplicacion;

	private int jobNumero;


	public EntPersona getAutor() {
		return autor;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAutor(EntPersona autor) {
		this.autor = autor;
	}

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
	}

	public int getJobNumero() {
		return jobNumero;
	}

	public void setJobNumero(int jobNumero) {
		this.jobNumero = jobNumero;
	}
}
