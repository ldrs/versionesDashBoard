package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;

@Entity
@Table(name = "VERSION_ALERTA_HISTORICA")
public class EntVersionAlertaHistorica extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -7848391812368239166L;

	@Column(name="mensaje", length=4000)
	private String mensaje;

	private String pathFile;

	@Enumerated(EnumType.STRING)
	private ETipoAlertaVersion alerta;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public ETipoAlertaVersion getAlerta() {
		return alerta;
	}

	public void setAlerta(ETipoAlertaVersion alerta) {
		this.alerta = alerta;
	}

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
}
