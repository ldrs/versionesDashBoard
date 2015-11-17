package rd.huma.dashboard.util;

public class Holder<T> {
	private T valor;

	public Holder() {
	}

	public Holder(T valor){
		this.valor = valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public T getValor() {
		return valor;
	}
}