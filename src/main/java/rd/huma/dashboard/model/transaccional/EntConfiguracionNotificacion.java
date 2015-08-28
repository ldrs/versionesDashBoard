package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoAlertaVersion;

@Entity
@Table(name="CONFIGURACION_NOTIFICACION")
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