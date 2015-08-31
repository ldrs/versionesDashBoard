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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;

@Entity
@Table(name="VERSION_ALERTA")
@NamedQueries({ @NamedQuery(name="versiones.alerta", query = "SELECT E.version FROM EntVersionAlerta E group by E.version"),
				@NamedQuery(name="buscaPorVersion.alerta", query = "SELECT E FROM EntVersionAlerta E where E.version = :ver order by E.fechaRegistro"),
				@NamedQuery(name="buscaPorPersonasPorTipo.alerta",query="SELECT GD.persona FROM EntVersionAlerta E ,EntConfiguracionNotificacion C, EntGrupoPersonaDetalle GD where C.ambiente=E.ambiente and GD.grupoPersona =C.grupoPersona  and C.alerta = E.alerta and E = :alt and (C.alertaPorAmbiente=true or  exists(select 1  from EntVersionParticipante D where D.participante = GD.persona and D.version = E.version)  )")
				})


/**
 * @author priamogermosen
 *
 */

public class EntVersionAlerta extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 4918362759521074590L;


	@Column(name="mensaje", length=4000)
	private String mensaje;

	private String pathFile;

	@Enumerated(EnumType.STRING)
	private ETipoAlertaVersion alerta;

	@JoinColumn
	@ManyToOne
	private EntVersion version;


	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;

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

	public EntAmbiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}


}