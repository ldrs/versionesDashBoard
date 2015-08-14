package rd.huma.dashboard.model.transaccional;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="VERSION_ALERTA")
@NamedQueries({ @NamedQuery(name="versiones.alerta", query = "SELECT E.version FROM EntVersionAlerta E group by E.version"),
				@NamedQuery(name="buscaPorVersion.alerta", query = "SELECT E FROM EntVersionAlerta E where E.version = :ver order by E.fechaRegistro")
				})
public class EntVersionAlerta extends AEntVersionAlerta {

	/**
	 *
	 */
	private static final long serialVersionUID = 4918362759521074590L;


}