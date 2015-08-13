package rd.huma.dashboard.model.transaccional;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;

@MappedSuperclass
public abstract class AEntVersionAlerta extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -3579062119468019373L;


	@Lob
	@Basic(fetch = FetchType.EAGER)
	private String mensaje;

	private String pathFile;

	@Enumerated(EnumType.STRING)
	private ETipoAlertaVersion alerta;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	private LocalDateTime fechaRegistro = LocalDateTime.now();

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
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