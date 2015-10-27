package rd.huma.dashboard.model.transaccional.dato;

import java.io.Serializable;

public class AreaPorTicket implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6468131209981727619L;


	private String nombre;

	private long numero;

	private int orden;

	public AreaPorTicket() {
	}

	public AreaPorTicket(String nombre,int orden,long numero){
		this.nombre = nombre;
		this.numero = numero;
		this.orden = orden;
	}

	public String getNombre() {
		return nombre;
	}

	public long getNumero() {
		return numero;
	}

	public int getOrden() {
		return orden;
	}

}