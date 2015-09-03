package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import rd.huma.dashboard.model.transaccional.dominio.ETipoScript;

@Entity
@Table(name="VERSION_SCRIPT")
@NamedQueries({
		 @NamedQuery(name = "contar.versionScripts", query="select count(e) from EntVersionScript E where E.version = :ver and E.habilitado=true"),
		 @NamedQuery(name = "buscarAntesDespues.versionScripts", query="select E from EntVersionScript E where E.version = :ver and tipoScript= :tipo and E.habilitado=true"),
		 @NamedQuery(name = "buscar.versionScripts", query="select E from EntVersionScript E where E.version = :ver and E.habilitado=true")

			}
		)
public class EntVersionScript extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 3460332090850494765L;

	@ManyToOne
	@JoinColumn
	private EntVersion version;

	private String urlScript;

	private String nombre;

	@Enumerated(EnumType.STRING)
	private ETipoScript tipoScript;

	private boolean habilitado = true;

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public String getUrlScript() {
		return urlScript;
	}

	public void setUrlScript(String urlScript) {
		this.urlScript = urlScript;
	}

	public ETipoScript getTipoScript() {
		return tipoScript;
	}

	public void setTipoScript(ETipoScript tipoScript) {
		this.tipoScript = tipoScript;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((urlScript == null) ? 0 : urlScript.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		if (!(obj instanceof EntVersionScript)) {
			return false;
		}
		EntVersionScript other = (EntVersionScript) obj;
		if (urlScript == null) {
			if (other.urlScript != null) {
				return false;
			}
		} else if (!urlScript.equals(other.urlScript)) {
			return false;
		}
		if (version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!version.equals(other.version)) {
			return false;
		}
		return true;
	}


}