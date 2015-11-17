package rd.huma.dashboard.model.transaccional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BRANCH_REVISION")
@NamedQueries({
	@NamedQuery(name="buscarRevision.branchRevision",query="SELECT E from EntBranchRevision E where E.revision = :rev")
})
public class EntBranchRevision extends AEntModelo{

	/**
	 *
	 */
	private static final long serialVersionUID = 3244865824170116082L;


	private String branch;

	private long revision;

	private boolean creacionBranch;

	private boolean version;

	private long revisionOrigen;

	private String branchOrigen;

	@ManyToOne
	@JoinColumn
	private EntPersona persona;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRevision;

	public long getRevision() {
		return revision;
	}

	public void setRevision(long revision) {
		this.revision = revision;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public boolean isCreacionBranch() {
		return creacionBranch;
	}

	public void setCreacionBranch(boolean creacionBranch) {
		this.creacionBranch = creacionBranch;
	}

	public boolean isVersion() {
		return version;
	}

	public void setVersion(boolean version) {
		this.version = version;
	}

	public EntPersona getPersona() {
		return persona;
	}

	public void setPersona(EntPersona persona) {
		this.persona = persona;
	}

	public Instant getFechaRevision() {
		return fechaRevision.toInstant();
	}

	public void setFechaRevision(Instant fechaRevision) {
		this.fechaRevision = Timestamp.from(fechaRevision);
	}

	public String getBranchOrigen() {
		return branchOrigen;
	}

	public void setBranchOrigen(String branchOrigen) {
		this.branchOrigen = branchOrigen;
	}

	public long getRevisionOrigen() {
		return revisionOrigen;
	}

	public void setRevisionOrigen(long revisionOrigen) {
		this.revisionOrigen = revisionOrigen;
	}
}