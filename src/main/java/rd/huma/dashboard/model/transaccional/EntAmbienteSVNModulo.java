package rd.huma.dashboard.model.transaccional;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "AMBIENTE_SVN_MODULO")
@NamedQueries({
	@NamedQuery(name="ambienteSvnModulo.buscar", query = "SELECT E from EntAmbienteSVNModulo E where E.ambienteSVN = :amb and E.artefacto.grupo = :grp and E.artefacto.artefacto = :art and E.artefacto.paquete = :paq and E.artefacto.clasificador is null"),
	@NamedQuery(name="ambienteSvnModulo.buscarConClaficador", query = "SELECT E from EntAmbienteSVNModulo E where E.ambienteSVN = :amb and E.artefacto.grupo = :grp and E.artefacto.artefacto = :art and E.artefacto.paquete = :paq and E.artefacto.clasificador = :claf"),
	@NamedQuery(name="ambienteSvnModulo.buscarRuta", query = "SELECT E from EntAmbienteSVNModulo E JOIN E.ambienteSVN A where A.rutaSvnAmbiente  = :rut")

				})
public class EntAmbienteSVNModulo extends AEntModelo  {

	/**
	 *
	 */
	private static final long serialVersionUID = -9048823529391233498L;

	@JoinColumn
	@ManyToOne
	private EntAmbienteSVN ambienteSVN;

	@Embedded
	private Artefacto artefacto;

	public EntAmbienteSVN getAmbienteSVN() {
		return ambienteSVN;
	}

	public void setAmbienteSVN(EntAmbienteSVN ambienteSVN) {
		this.ambienteSVN = ambienteSVN;
	}

	public Artefacto getArtefacto() {
		return artefacto;
	}

	public void setArtefacto(Artefacto artefacto) {
		this.artefacto = artefacto;
	}
}