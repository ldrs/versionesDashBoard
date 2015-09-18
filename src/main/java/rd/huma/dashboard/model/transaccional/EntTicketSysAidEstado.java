package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TICKET_SYSAID_ESTADO")
public class EntTicketSysAidEstado extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = -8424356197074999154L;

	private int codigo;
	private String descripcion;

	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EntAmbiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}
}