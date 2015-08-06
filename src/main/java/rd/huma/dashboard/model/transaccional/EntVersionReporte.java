package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_REPORTE")
@NamedQueries({
		@NamedQuery(name="contar.versionReportes", query="select count(e) from EntVersionReporte E where E.version = :ver"),
		@NamedQuery(name="buscar.versionReportes", query="select E from EntVersionReporte E where E.version = :ver")
		}
		)
public class EntVersionReporte extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -2514107819885937403L;


	@JoinColumn
	@ManyToOne
	private EntVersion version;

	private String reporte;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
}