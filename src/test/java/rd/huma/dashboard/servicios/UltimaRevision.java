package rd.huma.dashboard.servicios;

public class UltimaRevision {

	private long numeroRevision;
	private String usuarioSVN;
	private String fecha;


	UltimaRevision(long numeroRevision, String usuarioSVN, String fecha) {
		this.numeroRevision = numeroRevision;
		this.usuarioSVN = usuarioSVN;
		this.fecha = fecha;
	}

	public String getUsuarioSVN() {
		return usuarioSVN;
	}

	public String getFecha() {
		return fecha;
	}

	public long getNumeroRevision() {
		return numeroRevision;
	}
}