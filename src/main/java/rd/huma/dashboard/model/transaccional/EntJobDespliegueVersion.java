package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import rd.huma.dashboard.model.transaccional.dominio.EEstadoJobDespliegue;
import rd.huma.dashboard.model.transaccional.dominio.ETipoDespliegueJob;
import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;

@Entity
@Table(name="JOB_DESPLIEGUE")
@NamedQueries({
	@NamedQuery(name="buscaPorId.jobDespliegue", query="SELECT E from EntJobDespliegueVersion E join E.version V where V.id = :id"),
	@NamedQuery(name="buscaPorVersionTipo.jobDespliegue", query="SELECT E from EntJobDespliegueVersion E  join E.version V where V = :ver AND E.tipoDespliegue = :tipo  AND E.estado = :est"),
	@NamedQuery(name="metricaAgrupadaYear.jobDespliegue", query="SELECT count(E), E.estado, FUNCTION('MONTH',e.fechaRegistro) from EntJobDespliegueVersion E where E.tipoDespliegue = :tipo group by E.estado, FUNCTION('MONTH',e.fechaRegistro)"),
	@NamedQuery(name="buscaPorBranch.jobDespliegue", query="SELECT E from EntJobDespliegueVersion E join E.version V where V.branchOrigen = :branch AND E.tipoDespliegue = :tipo order by E.fechaRegistro desc"),
})
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

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	@Enumerated(EnumType.STRING)
	private EEstadoJobDespliegue estado = EEstadoJobDespliegue.ESPERANDO_DEPLOY;

	@Enumerated(EnumType.STRING)
	private ETipoDespliegueJob tipoDespliegue = ETipoDespliegueJob.VERSION;

	@Enumerated(EnumType.STRING)
	private ETipoScript tipoScript;

	@JoinColumn
	@ManyToOne
	private EntFilaDespliegue filaDespliegue;

	private String jobNumber;

	private String url;

	private String inicioJob;

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

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
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

	public ETipoScript getTipoScript() {
		return tipoScript;
	}

	public void setTipoScript(ETipoScript tipoScript) {
		this.tipoScript = tipoScript;
	}

	public EntFilaDespliegue getFilaDespliegue() {
		return filaDespliegue;
	}

	public void setFilaDespliegue(EntFilaDespliegue filaDespliegue) {
		this.filaDespliegue = filaDespliegue;
	}

	public void setInicioJob(String inicioJob) {
		this.inicioJob = inicioJob;
	}

	public String getInicioJob() {
		return inicioJob;
	}

}