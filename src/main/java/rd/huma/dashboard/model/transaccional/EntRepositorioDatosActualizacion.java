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

import rd.huma.dashboard.model.transaccional.dominio.EEstadoRepositorioActualizacionDatos;

@Entity
@Table(name="REPOSITORIO_DATOS_ACTUALIZACION")
@NamedQueries({
	@NamedQuery(name = "buscar.repositorioDatosActualizacion", query = "SELECT E from EntRepositorioDatosActualizacion E where E.fechaFin is null and E.repositorioDatos = :repo")
	})
public class EntRepositorioDatosActualizacion extends AEntModelo {

	/**
	 *
	 */
	private static final long serialVersionUID = 4141269653182800022L;


	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = Timestamp.from(Instant.now());

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;

	@Enumerated(EnumType.STRING)
	private EEstadoRepositorioActualizacionDatos estado = EEstadoRepositorioActualizacionDatos.PROCESO;

	@JoinColumn
	@ManyToOne
	private EntRepositorioDatos repositorioDatos;

	public EntRepositorioDatos getRepositorioDatos() {
		return repositorioDatos;
	}

	public void setRepositorioDatos(EntRepositorioDatos repositorioDatos) {
		this.repositorioDatos = repositorioDatos;
	}

	public void setEstado(EEstadoRepositorioActualizacionDatos estado) {
		this.estado = estado;
	}

	public EEstadoRepositorioActualizacionDatos getEstado() {
		return estado;
	}

	public void asignarFechaFin(){
		fechaFin = Timestamp.from(Instant.now());
	}
}