package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;

@Entity
@Table(name="CONFIGURACION_NOTIFICACION")
@NamedQueries({
		@NamedQuery(name="buscaUK.configNotificacion",query="SELECT E from EntConfiguracionNotificacion E where E.ambiente = :amb and  E.grupoPersona = :grp and E.alerta = :tipo"),
		@NamedQuery(name="buscaGrupo.configNotificacion",query="SELECT E from EntConfiguracionNotificacion E where E.ambiente = :amb and E.alerta = :tipo"),
		})
public class EntConfiguracionNotificacion extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 5133301913700376898L;


	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;


	@JoinColumn
	@ManyToOne
	private EntGrupoPersona grupoPersona;


	@Enumerated(EnumType.STRING)
	private ETipoAlertaVersion alerta;


	public EntAmbiente getAmbiente() {
		return ambiente;
	}


	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}


	public EntGrupoPersona getGrupoPersona() {
		return grupoPersona;
	}


	public void setGrupoPersona(EntGrupoPersona grupoPersona) {
		this.grupoPersona = grupoPersona;
	}


	public ETipoAlertaVersion getAlerta() {
		return alerta;
	}


	public void setAlerta(ETipoAlertaVersion alerta) {
		this.alerta = alerta;
	}
}