package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import rd.huma.dashboard.model.transaccional.dominio.ETipoUndeploy;

@Entity
@Table(name="HISTORICO_UNDEPLOY")
@NamedQueries({
	@NamedQuery(name="buscarPorVersion.historicoUndeploy",query="select E from EntHistoricoUndeploy E join E.version V where V.id = :ver")
})
public class EntHistoricoUndeploy extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -7648163194132459409L;


	@JoinColumn
	@ManyToOne
	private EntVersion version;


	@JoinColumn
	@ManyToOne
	private EntServidor servidor;

	@JoinColumn
	@ManyToOne
	private EntPersona autor;

	@Enumerated(EnumType.STRING)
	private ETipoUndeploy tipoUndeploy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	public EntVersion getVersion() {
		return version;
	}

	public void setVersion(EntVersion version) {
		this.version = version;
	}

	public EntServidor getServidor() {
		return servidor;
	}

	public void setServidor(EntServidor servidor) {
		this.servidor = servidor;
	}

	public EntPersona getAutor() {
		return autor;
	}

	public void setAutor(EntPersona autor) {
		this.autor = autor;
	}

	public Instant getFechaRegistro() {
		return fechaRegistro.toInstant();
	}

	public ETipoUndeploy getTipoUndeploy() {
		return tipoUndeploy;
	}

	public void setTipoUndeploy(ETipoUndeploy tipoUndeploy) {
		this.tipoUndeploy = tipoUndeploy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result
				+ ((fechaRegistro == null) ? 0 : fechaRegistro.hashCode());
		result = prime * result
				+ ((servidor == null) ? 0 : servidor.hashCode());
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
		if (!(obj instanceof EntHistoricoUndeploy)) {
			return false;
		}
		EntHistoricoUndeploy other = (EntHistoricoUndeploy) obj;
		if (autor == null) {
			if (other.autor != null) {
				return false;
			}
		} else if (!autor.equals(other.autor)) {
			return false;
		}
		if (fechaRegistro == null) {
			if (other.fechaRegistro != null) {
				return false;
			}
		} else if (!fechaRegistro.equals(other.fechaRegistro)) {
			return false;
		}
		if (servidor == null) {
			if (other.servidor != null) {
				return false;
			}
		} else if (!servidor.equals(other.servidor)) {
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