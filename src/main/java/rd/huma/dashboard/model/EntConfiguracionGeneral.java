package rd.huma.dashboard.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONFIGURACION_GENERAL")
public class EntConfiguracionGeneral {

	@Id
	private String id = UUID.randomUUID().toString();

	private String rutaJira;
	private String usrJira;
	private String pwdJira;

	private String rutaSysaid;
	private String usrSysaid;
	private String pwdSysaid;

	private String rutaSvn;
	private String usrSvn;
	private String pwdSvn;

	private String rutaJenkins;
	private String usrJenkins;
	private String pwdJenkins;
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
	public String getId() {
		return id;
	}


}