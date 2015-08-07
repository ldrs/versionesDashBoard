package rd.huma.dashboard.model.transaccional;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;

@Entity
@Table(name="JOB_DESPLIEGUE")
public class EntJobDespliegueVersion extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 5188787159227943111L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	@JoinColumn
	@ManyToOne
	private EntServidor servidor;

	private String jobNumber;

	private LocalDateTime fechaRegistro = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private EEstadoJobDespliegue estado = EEstadoJobDespliegue.ESPERANDO_DEPLOY;

	@Enumerated(EnumType.STRING)
	private ETipoDespliegueJob tipoDespliegue;

	private String url;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public EntServidor getServidor() {
		return servidor;
	}

	public void setServidor(EntServidor servidor) {
		this.servidor = servidor;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}


	public EEstadoJobDespliegue getEstado() {
		return estado;
	}

	public void setEstado(EEstadoJobDespliegue estado) {
		this.estado = estado;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public ETipoDespliegueJob getTipoDespliegue() {
		return tipoDespliegue;
	}
	public void setTipoDespliegue(ETipoDespliegueJob tipoDespliegue) {
		this.tipoDespliegue = tipoDespliegue;
	}
}