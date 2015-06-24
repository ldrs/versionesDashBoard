package rd.huma.dashboard.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILA_DEPLOYEMENT")
public class EntFilaDeployement extends AEntModelo {
	/**
	 *
	 */
	private static final long serialVersionUID = 7034287352452940249L;


	@JoinColumn
	@ManyToOne
	private LocalDateTime fecha;

	@JoinColumn
	@ManyToOne
	private EntAmbiente ambiente;

	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public EntAmbiente getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(EntAmbiente ambiente) {
		this.ambiente = ambiente;
	}




}