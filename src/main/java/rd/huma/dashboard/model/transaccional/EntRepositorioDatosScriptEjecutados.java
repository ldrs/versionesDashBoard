package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import rd.huma.dashboard.model.transaccional.dominio.EEstadoScript;

@Entity
@Table(name="REPOSITORIO_DATOS_SCRIPT_EJE")
@NamedQueries({
	@NamedQuery(name="buscarPorUrl.scriptEjecutados",query = "SELECT E from EntRepositorioDatosScriptEjecutados E join E.script S where E.repositorioDatos = :rep and S.urlScript = :scr "),
	@NamedQuery(name="buscarPorJob.scriptEjecutados",query = "SELECT E from EntRepositorioDatosScriptEjecutados E join E.job J where J.id = :id"),
	@NamedQuery(name="buscarPorVersion.scriptEjecutados",query = "SELECT E from EntRepositorioDatosScriptEjecutados E join E.job J join J.version V where V.numero = :ver")
})
public class EntRepositorioDatosScriptEjecutados extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 5531411340878896672L;

	@JoinColumn
	@ManyToOne
	private EntRepositorioDatos repositorioDatos;


	@JoinColumn
	@ManyToOne
	private EntVersionScript script;

	private EntPersona autorScript;

	private long revisionScript;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEjecucion = Timestamp.from(Instant.now());


	@Enumerated(EnumType.STRING)
	private EEstadoScript estadoScript = EEstadoScript.PENDIENTE_EJECUCION;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	private String resultado;


	@JoinColumn
	@ManyToOne
	private EntJobDespliegueVersion job;

	public EntRepositorioDatos getRepositorioDatos() {
		return repositorioDatos;
	}

	public void setRepositorioDatos(EntRepositorioDatos repositorioDatos) {
		this.repositorioDatos = repositorioDatos;
	}

	public EntVersionScript getScript() {
		return script;
	}

	public void setScript(EntVersionScript script) {
		this.script = script;
	}

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
	}


	public Instant getFechaEjecucion() {
		return fechaEjecucion.toInstant();
	}

	public void setFechaEjecucion(LocalDateTime fecha) {
		this.fechaEjecucion = Timestamp.valueOf(fecha);
	}

	public EEstadoScript getEstadoScript() {
		return estadoScript;
	}

	public void setEstadoScript(EEstadoScript estadoScript) {
		this.estadoScript = estadoScript;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public EntPersona getAutorScript() {
		return autorScript;
	}

	public void setAutorScript(EntPersona autorScript) {
		this.autorScript = autorScript;
	}

	public long getRevisionScript() {
		return revisionScript;
	}

	public void setRevisionScript(long revisionScript) {
		this.revisionScript = revisionScript;
	}

	public EntJobDespliegueVersion getJob() {
		return job;
	}

	public void setJob(EntJobDespliegueVersion job) {
		this.job = job;
	}

}