package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BRANCH_REVISION_CAMBIO")
public class EntBranchRevisionCambio {

	@JoinColumn
	@ManyToOne
	private EntAplicacionSubModulo subModulo;

	@JoinColumn
	@ManyToOne
	private EntBranchRevision revision;

	private int agregados;
	private int eliminados;
	private int modificados;


	public EntAplicacionSubModulo getSubModulo() {
		return subModulo;
	}

	public void setModificados(int modificados) {
		this.modificados = modificados;
	}

	public int getAgregados() {
		return agregados;
	}

	public void setAgregados(int agregados) {
		this.agregados = agregados;
	}

	public int getEliminados() {
		return eliminados;
	}

	public void setEliminados(int eliminados) {
		this.eliminados = eliminados;
	}

	public int getModificados() {
		return modificados;
	}

	public void setSubModulo(EntAplicacionSubModulo subModulo) {
		this.subModulo = subModulo;
	}

	public EntBranchRevision getRevision() {
		return revision;
	}

	public void setRevision(EntBranchRevision revision) {
		this.revision = revision;
	}
}