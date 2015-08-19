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
@Table(name="AMBIENTE_SVN")
@NamedQueries({
				@NamedQuery(name="ambienteSVN.todos", query = "SELECT E from EntAmbienteSVN E"),
				@NamedQuery(name="ambienteSVN.porRuta", query = "SELECT E from EntAmbienteSVN E where E.rutaSvnAmbiente = :ruta")
			   }
			  )
public class EntAmbienteSVN extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 7968579889112325685L;

	private String rutaSvnAmbiente;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaActualizacion = Timestamp.from(Instant.now());

	public String getRutaSvnAmbiente() {
		return rutaSvnAmbiente;
	}

	public void setRutaSvnAmbiente(String rutaSvnAmbiente) {
		this.rutaSvnAmbiente = rutaSvnAmbiente;
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

}