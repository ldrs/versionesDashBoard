package rd.huma.dashboard.model.transaccional;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="AMBIENTE_SVN")
@NamedQueries({
				@NamedQuery(name="ambienteSVN.todos", query = "SELECT E from EntAmbienteSVN E"),
				@NamedQuery(name="ambienteSVN.porRuta", query = "SELECT E from EntAmbienteSVN E where E.rutaSvnAmbiente = :ruta")
			   }
			  )
public class EntAmbienteSVN extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 7968579889112325685L;

	private String rutaSvnAmbiente;

	private LocalDate fechaCreacion = LocalDate.now();

	private LocalDate fechaModificacion = LocalDate.now();

	public String getRutaSvnAmbiente() {
		return rutaSvnAmbiente;
	}

	public void setRutaSvnAmbiente(String rutaSvnAmbiente) {
		this.rutaSvnAmbiente = rutaSvnAmbiente;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDate fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


}