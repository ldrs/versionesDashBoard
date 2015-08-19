package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="CONFIGURACION_GENERAL")
@NamedQuery(name="configuracionGeneral", query="SELECT E from EntConfiguracionGeneral E")
public class EntConfiguracionGeneral extends AEntModelo {


	/**
	 *
	 */
	private static final long serialVersionUID = -3334366160213466672L;


	private String rutaJira = "http://172.16.7.42:8080/";
	private String usrJira;
	private String pwdJira;

	private String rutaSysaid = "http://serviciossiafe.hacienda.gov.do:8080/";
	private String usrSysaid = "integrador";
	private String pwdSysaid = "integrador";

	private String rutaSvn = "http://172.16.7.35:9880/svn/";
	private String usrSvn;
	private String pwdSvn;

	private String rutaJenkins = "http://172.16.7.39:8080/";
	private String usrJenkins = "jenkins";
	private String pwdJenkins = "c48186a382ae4829b9392222b65e233f";


	private String nombreCampoJiraLineaDesarrollo = "cf[10810]";

	private String nombreCampoSysAid = "customfield_10902";

	private String nombreActiveDirectory = "sigefint.gov.do:389";

	private String domainBaseActiveDirecty = "DC=sigefint,DC=gov,DC=do";


	private String rutaDashBoard = "http://172.16.7.52:8080/dashboard/";

	public String getRutaJira() {
		return rutaJira;
	}
	public void setRutaJira(String rutaJira) {
		this.rutaJira = rutaJira;
	}
	public String getUsrJira() {
		return usrJira;
	}
	public void setUsrJira(String usrJira) {
		this.usrJira = usrJira;
	}
	public String getPwdJira() {
		return pwdJira;
	}
	public void setPwdJira(String pwdJira) {
		this.pwdJira = pwdJira;
	}
	public String getRutaSysaid() {
		return rutaSysaid;
	}
	public void setRutaSysaid(String rutaSysaid) {
		this.rutaSysaid = rutaSysaid;
	}
	public String getUsrSysaid() {
		return usrSysaid;
	}
	public void setUsrSysaid(String usrSysaid) {
		this.usrSysaid = usrSysaid;
	}
	public String getPwdSysaid() {
		return pwdSysaid;
	}
	public void setPwdSysaid(String pwdSysaid) {
		this.pwdSysaid = pwdSysaid;
	}
	public String getRutaSvn() {
		return rutaSvn;
	}
	public void setRutaSvn(String rutaSvn) {
		this.rutaSvn = rutaSvn;
	}
	public String getUsrSvn() {
		return usrSvn;
	}
	public void setUsrSvn(String usrSvn) {
		this.usrSvn = usrSvn;
	}
	public String getPwdSvn() {
		return pwdSvn;
	}
	public void setPwdSvn(String pwdSvn) {
		this.pwdSvn = pwdSvn;
	}
	public String getRutaJenkins() {
		return rutaJenkins;
	}
	public void setRutaJenkins(String rutaJenkins) {
		this.rutaJenkins = rutaJenkins;
	}
	public String getUsrJenkins() {
		return usrJenkins;
	}
	public void setUsrJenkins(String usrJenkins) {
		this.usrJenkins = usrJenkins;
	}
	public String getPwdJenkins() {
		return pwdJenkins;
	}
	public void setPwdJenkins(String pwdJenkins) {
		this.pwdJenkins = pwdJenkins;
	}

	public String getRutaDashBoard() {
		return rutaDashBoard;
	}

	public void setRutaDashBoard(String rutaDashBoard) {
		this.rutaDashBoard = rutaDashBoard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((pwdJenkins == null) ? 0 : pwdJenkins.hashCode());
		result = prime * result + ((pwdJira == null) ? 0 : pwdJira.hashCode());
		result = prime * result + ((pwdSvn == null) ? 0 : pwdSvn.hashCode());
		result = prime * result
				+ ((pwdSysaid == null) ? 0 : pwdSysaid.hashCode());
		result = prime * result
				+ ((rutaJenkins == null) ? 0 : rutaJenkins.hashCode());
		result = prime * result
				+ ((rutaJira == null) ? 0 : rutaJira.hashCode());
		result = prime * result + ((rutaSvn == null) ? 0 : rutaSvn.hashCode());
		result = prime * result
				+ ((rutaSysaid == null) ? 0 : rutaSysaid.hashCode());
		result = prime * result
				+ ((usrJenkins == null) ? 0 : usrJenkins.hashCode());
		result = prime * result + ((usrJira == null) ? 0 : usrJira.hashCode());
		result = prime * result + ((usrSvn == null) ? 0 : usrSvn.hashCode());
		result = prime * result
				+ ((usrSysaid == null) ? 0 : usrSysaid.hashCode());
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
		if (!(obj instanceof EntConfiguracionGeneral)) {
			return false;
		}
		EntConfiguracionGeneral other = (EntConfiguracionGeneral) obj;
		if (pwdJenkins == null) {
			if (other.pwdJenkins != null) {
				return false;
			}
		} else if (!pwdJenkins.equals(other.pwdJenkins)) {
			return false;
		}
		if (pwdJira == null) {
			if (other.pwdJira != null) {
				return false;
			}
		} else if (!pwdJira.equals(other.pwdJira)) {
			return false;
		}
		if (pwdSvn == null) {
			if (other.pwdSvn != null) {
				return false;
			}
		} else if (!pwdSvn.equals(other.pwdSvn)) {
			return false;
		}
		if (pwdSysaid == null) {
			if (other.pwdSysaid != null) {
				return false;
			}
		} else if (!pwdSysaid.equals(other.pwdSysaid)) {
			return false;
		}
		if (rutaJenkins == null) {
			if (other.rutaJenkins != null) {
				return false;
			}
		} else if (!rutaJenkins.equals(other.rutaJenkins)) {
			return false;
		}
		if (rutaJira == null) {
			if (other.rutaJira != null) {
				return false;
			}
		} else if (!rutaJira.equals(other.rutaJira)) {
			return false;
		}
		if (rutaSvn == null) {
			if (other.rutaSvn != null) {
				return false;
			}
		} else if (!rutaSvn.equals(other.rutaSvn)) {
			return false;
		}
		if (rutaSysaid == null) {
			if (other.rutaSysaid != null) {
				return false;
			}
		} else if (!rutaSysaid.equals(other.rutaSysaid)) {
			return false;
		}
		if (usrJenkins == null) {
			if (other.usrJenkins != null) {
				return false;
			}
		} else if (!usrJenkins.equals(other.usrJenkins)) {
			return false;
		}
		if (usrJira == null) {
			if (other.usrJira != null) {
				return false;
			}
		} else if (!usrJira.equals(other.usrJira)) {
			return false;
		}
		if (usrSvn == null) {
			if (other.usrSvn != null) {
				return false;
			}
		} else if (!usrSvn.equals(other.usrSvn)) {
			return false;
		}
		if (usrSysaid == null) {
			if (other.usrSysaid != null) {
				return false;
			}
		} else if (!usrSysaid.equals(other.usrSysaid)) {
			return false;
		}
		return true;
	}

	public String getNombreCampoJiraLineaDesarrollo() {
		return nombreCampoJiraLineaDesarrollo;
	}
	public void setNombreCampoJiraLineaDesarrollo(String nombreCampoJiraLineaDesarrollo) {
		this.nombreCampoJiraLineaDesarrollo = nombreCampoJiraLineaDesarrollo;
	}
	public String getNombreCampoSysAid() {
		return nombreCampoSysAid;
	}
	public void setNombreCampoSysAid(String nombreCampoSysAid) {
		this.nombreCampoSysAid = nombreCampoSysAid;
	}
	public String getNombreActiveDirectory() {
		return nombreActiveDirectory;
	}
	public void setNombreActiveDirectory(String nombreActiveDirectory) {
		this.nombreActiveDirectory = nombreActiveDirectory;
	}
	public String getDomainBaseActiveDirecty() {
		return domainBaseActiveDirecty;
	}
	public void setDomainBaseActiveDirecty(String domainBaseActiveDirecty) {
		this.domainBaseActiveDirecty = domainBaseActiveDirecty;
	}
}