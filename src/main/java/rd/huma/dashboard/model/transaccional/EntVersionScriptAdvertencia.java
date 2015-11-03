package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoAdvertencia;

@Entity
@Table(name="VERSION_SCRIPT_ADVERTENCIA")
@NamedQueries({
	@NamedQuery(name="buscaPorScript.versionScriptAdvertencia",query="SELECT J from EntVersionScriptAdvertencia J where J.script = :src")
})
public class EntVersionScriptAdvertencia extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -70168492223697202L;


	@JoinColumn
	@ManyToOne
	private EntVersionScript versionScript;

	@Enumerated(EnumType.STRING)
	private ETipoAdvertencia advertencia;

	private String causa;

	public EntVersionScript getVersionScript() {
		return versionScript;
	}

	public void setVersionScript(EntVersionScript versionScript) {
		this.versionScript = versionScript;
	}

	public ETipoAdvertencia getAdvertencia() {
		return advertencia;
	}

	public void setAdvertencia(ETipoAdvertencia advertencia) {
		this.advertencia = advertencia;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}
}