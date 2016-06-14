package rd.huma.dashboard.antipatron.dto;

/**
 *
 * Clase Creada para transporte con el servicio.
 *
 */
public class Servidor {


	private String nombre;

	private String rutaEntrada;

	private String nombreServidorJenkins;

	private String ambiente;

	private String baseDatos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRutaEntrada() {
		return rutaEntrada;
	}

	public void setRutaEntrada(String rutaEntrada) {
		this.rutaEntrada = rutaEntrada;
	}

	public String getNombreServidorJenkins() {
		return nombreServidorJenkins;
	}

	public void setNombreServidorJenkins(String nombreServidorJenkins) {
		this.nombreServidorJenkins = nombreServidorJenkins;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getBaseDatos() {
		return baseDatos;
	}

	public void setBaseDatos(String baseDatos) {
		this.baseDatos = baseDatos;
	}


}
