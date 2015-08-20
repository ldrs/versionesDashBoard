package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="HISTORICO_DESPLIEGUE_DUENO")
public class EntHistoricoDespliegueDueno extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 497640345094480885L;



	@JoinColumn
	@ManyToOne
	private EntHistoricoDespliegue historicoDespliegue;

	@JoinColumn
	@ManyToOne
	private EntPersona dueno;

	public EntHistoricoDespliegue getHistoricoDespliegue() {
		return historicoDespliegue;
	}

	public void setHistoricoDespliegue(EntHistoricoDespliegue historicoDespliegue) {
		this.historicoDespliegue = historicoDespliegue;
	}

	public EntPersona getDueno() {
		return dueno;
	}

	public void setDueno(EntPersona dueno) {
		this.dueno = dueno;
	}

}