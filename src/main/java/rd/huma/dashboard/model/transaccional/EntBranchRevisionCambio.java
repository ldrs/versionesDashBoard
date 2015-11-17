package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoCambioFuente;

@Entity
@Table(name="BRANCH_REVISION_CAMBIO")
public class EntBranchRevisionCambio extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 8211116611572871107L;

	@JoinColumn
	@ManyToOne
	private EntAplicacionSubModulo subModulo;

	@JoinColumn
	@ManyToOne
	private EntBranchRevision revision;


	@JoinColumn
	@ManyToOne
	private EntAplicacionCatalogoCambio catalogo;

	@Enumerated(EnumType.STRING)
	private ETipoCambioFuente tipoCambio;


	private int cantidad;

	public EntAplicacionSubModulo getSubModulo() {
		return subModulo;
	}

	public void setSubModulo(EntAplicacionSubModulo subModulo) {
		this.subModulo = subModulo;
	}

	public ETipoCambioFuente getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(ETipoCambioFuente tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public EntBranchRevision getRevision() {
		return revision;
	}

	public void setRevision(EntBranchRevision revision) {
		this.revision = revision;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setCatalogo(EntAplicacionCatalogoCambio catalogo) {
		this.catalogo = catalogo;
	}

	public EntAplicacionCatalogoCambio getCatalogo() {
		return catalogo;
	}

}