package rd.huma.dashboard.model.transaccional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_LOG")
public class EntVersionLog extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 4466855170415843482L;

	private LocalDateTime fechaRegistro = LocalDateTime.now();

	private String path;

	private LocalDate expiracion = LocalDate.now().plusDays(3);

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public LocalDate getExpiracion() {
		return expiracion;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}