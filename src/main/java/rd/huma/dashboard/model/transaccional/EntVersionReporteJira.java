package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_REPORTE_JIRA")
@NamedQueries({
	@NamedQuery(name="buscaPorReporte",query="SELECT J from EntVersionReporteJira J where J.reporte = :rep")
})
public class EntVersionReporteJira extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -8686035490104641324L;



	@JoinColumn
	@ManyToOne
	private EntJira jira;

	@JoinColumn
	@ManyToOne
	private EntVersionReporte reporte;


	public EntJira getJira() {
		return jira;
	}

	public void setJira(EntJira jira) {
		this.jira = jira;
	}

	public EntVersionReporte getReporte() {
		return reporte;
	}

	public void setReporte(EntVersionReporte reporte) {
		this.reporte = reporte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jira == null) ? 0 : jira.hashCode());
		result = prime * result + ((reporte == null) ? 0 : reporte.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof EntVersionReporteJira)) {
			return false;
		}
		EntVersionReporteJira other = (EntVersionReporteJira) obj;
		if (jira == null) {
			if (other.jira != null) {
				return false;
			}
		} else if (!jira.equals(other.jira)) {
			return false;
		}
		if (reporte == null) {
			if (other.reporte != null) {
				return false;
			}
		} else if (!reporte.equals(other.reporte)) {
			return false;
		}
		return true;
	}



}
