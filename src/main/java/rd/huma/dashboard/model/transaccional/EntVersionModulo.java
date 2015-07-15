package rd.huma.dashboard.model.transaccional;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_MODULO")
@NamedQuery(name="buscar.versionModulo",query="SELECT E from EntVersionModulo E where E.version in :e")
public class EntVersionModulo extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = -4860664355734377968L;

	@JoinColumn
	@ManyToOne
	private EntVersion version;
	
	@Embedded
	private Artefacto artefacto;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public Artefacto getArtefacto() {
		return artefacto;
	}

	public void setArtefacto(Artefacto artefacto) {
		this.artefacto = artefacto;
	}
	
}