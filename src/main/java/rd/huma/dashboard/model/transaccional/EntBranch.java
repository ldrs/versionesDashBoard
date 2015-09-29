package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Branch")
@NamedQueries({
	@NamedQuery(name="busca.branch",query="select E from EntBranch E where E.branch = :branch")
})
public class EntBranch extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -6123391563607159767L;

	@ManyToOne
	@JoinColumn
	private EntAplicacion aplicacion;


	private String branch;


	private String origen;

	private long revisionOrigen;

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(EntAplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public long getRevisionOrigen() {
		return revisionOrigen;
	}

	public void setRevisionOrigen(long revisionOrigen) {
		this.revisionOrigen = revisionOrigen;
	}
}