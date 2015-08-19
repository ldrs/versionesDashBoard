package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="VERSION_LOG")
public class EntVersionLog extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 4466855170415843482L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	private String path;

	private Date expiracion = java.sql.Date.valueOf(LocalDate.now().plusDays(3));

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
	}

	public Instant getExpiracion() {
		return expiracion.toInstant();
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}