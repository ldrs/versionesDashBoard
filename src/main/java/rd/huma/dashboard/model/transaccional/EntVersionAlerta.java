package rd.huma.dashboard.model.transaccional;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;

@Entity
@Table(name="VERSION_ALERTA")
public class EntVersionAlerta extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 4918362759521074590L;


	@Lob
	@Basic(fetch = FetchType.EAGER)
	private String mensaje;

	private String pathFile;

	@Enumerated(EnumType.STRING)
	private ETipoAlertaVersion alerta;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

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