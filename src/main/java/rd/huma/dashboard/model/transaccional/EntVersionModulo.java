package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_MODULO")
public class EntVersionModulo extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = -4860664355734377968L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;

	private String modulo;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
}