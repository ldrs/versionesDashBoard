package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="APLICACION")
@NamedQueries(
				{
				@NamedQuery(name="aplicacion.buscar",query="SELECT E from EntAplicacion E where E.nombre = :nomApp"),
				@NamedQuery(name="aplicacion.todos",query="SELECT E from EntAplicacion E order by E.orden")
				}
			)
public class EntAplicacion extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -8506267188084434427L;


	private String nombre;

	private String jiraKey;

	private String svnPath;

	private String nombrePropiedadesPom;

	private int orden;

	private String rutaSvnAmbiente;

	private String rutaPathTrunk;

	private String nombreJobDeployJenkins;

	private String nombreJobSQLJenkins;

	private String nombreJobOracleReportJenkins;

	private String nombreArtefactoBusquedaNexus;

	private String nombreGrupoBusquedaNexus;

	private String nombreJobSubeServidores;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getJiraKey() {
		return jiraKey;
	}

	public void setJiraKey(String jiraKey) {
		this.jiraKey = jiraKey;
	}

	public String getSvnPath() {
		return svnPath;
	}

	public void setSvnPath(String svnPath) {
		this.svnPath = svnPath;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getNombrePropiedadesPom() {
		return nombrePropiedadesPom;
	}

	public void setNombrePropiedadesPom(String nombrePropiedadesPom) {
		this.nombrePropiedadesPom = nombrePropiedadesPom;
	}

	public String getRutaSvnAmbiente() {
		return rutaSvnAmbiente;
	}

	public void setRutaSvnAmbiente(String rutaSvnAmbiente) {
		this.rutaSvnAmbiente = rutaSvnAmbiente;
	}


	public void setNombreJobDeployJenkins(String jobJenkinsDeployements) {
		this.nombreJobDeployJenkins = jobJenkinsDeployements;
	}

	public String getNombreJobDeployJenkins() {
		return nombreJobDeployJenkins;
	}

	public void setNombreJobSQLJenkins(String nombreJobSQLJenkins) {
		this.nombreJobSQLJenkins = nombreJobSQLJenkins;
	}

	public String getNombreJobSQLJenkins() {
		return nombreJobSQLJenkins;
	}

	public String getNombreJobOracleReportJenkins() {
		return nombreJobOracleReportJenkins;
	}

	public void setNombreJobOracleReportJenkins(String nombreJobOracleReportJenkins) {
		this.nombreJobOracleReportJenkins = nombreJobOracleReportJenkins;
	}

	public String getNombreArtefactoBusquedaNexus() {
		return nombreArtefactoBusquedaNexus;
	}

	public String getNombreGrupoBusquedaNexus() {
		return nombreGrupoBusquedaNexus;
	}

	public void setNombreArtefactoBusquedaNexus(
			String nombreArtefactoBusquedaNexus) {
		this.nombreArtefactoBusquedaNexus = nombreArtefactoBusquedaNexus;
	}

	public void setNombreGrupoBusquedaNexus(String nombreGrupoBusquedaNexus) {
		this.nombreGrupoBusquedaNexus = nombreGrupoBusquedaNexus;
	}

	public String getRutaPathTrunk() {
		return rutaPathTrunk;
	}

	public void setRutaPathTrunk(String rutaPathTrunk) {
		this.rutaPathTrunk = rutaPathTrunk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jiraKey == null) ? 0 : jiraKey.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + orden;
		result = prime * result + ((svnPath == null) ? 0 : svnPath.hashCode());
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
		if (!(obj instanceof EntAplicacion)) {
			return false;
		}
		EntAplicacion other = (EntAplicacion) obj;
		if (jiraKey == null) {
			if (other.jiraKey != null) {
				return false;
			}
		} else if (!jiraKey.equals(other.jiraKey)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (orden != other.orden) {
			return false;
		}
		if (svnPath == null) {
			if (other.svnPath != null) {
				return false;
			}
		} else if (!svnPath.equals(other.svnPath)) {
			return false;
		}
		return true;
	}

	public String getNombreJobSubeServidores() {
		return nombreJobSubeServidores;
	}

	public void setNombreJobSubeServidores(String nombreJobSubeServidores) {
		this.nombreJobSubeServidores = nombreJobSubeServidores;
	}
}