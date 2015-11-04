package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoRiesgo;

@Entity
@Table(name="VERSION_RIESGO")
public class EntVersionRiesgo extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 5997789811297392040L;

	@ManyToOne
	@JoinColumn
	private EntVersion version;


	@Enumerated(EnumType.STRING)
	private ETipoRiesgo riesgo;


	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public ETipoRiesgo getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(ETipoRiesgo riesgo) {
		this.riesgo = riesgo;
	}

}
