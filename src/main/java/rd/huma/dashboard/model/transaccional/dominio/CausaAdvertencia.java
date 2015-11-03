package rd.huma.dashboard.model.transaccional.dominio;

public class CausaAdvertencia {

	private boolean causoProblema;
	private CharSequence causa;

	CausaAdvertencia(boolean causoProblema) {
		this.causoProblema = causoProblema;
	}

	public boolean isCausoProblema() {
		return causoProblema;
	}

	CausaAdvertencia setCausa(CharSequence causa) {
		this.causa = causa;
		return this;
	}

	public CharSequence getCausa() {
		return causa;
	}
}