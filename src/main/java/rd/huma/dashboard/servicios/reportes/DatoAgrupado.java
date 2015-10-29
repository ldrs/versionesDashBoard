package rd.huma.dashboard.servicios.reportes;


public class DatoAgrupado implements Comparable<DatoAgrupado>{
	private String serie;
	private long cantidad;
	private int mes;

	public DatoAgrupado(long cantidad, String serie, int mes) {
		this.cantidad = cantidad;
		this.serie = serie;
		this.mes = mes;
	}

	public long getCantidad() {
		return cantidad;
	}
	public String getSerie() {
		return serie;
	}

	public int getMes() {
		return mes;
	}

	@Override
	public int compareTo(DatoAgrupado o) {
		return Integer.compare(mes, o.mes);
	}
}