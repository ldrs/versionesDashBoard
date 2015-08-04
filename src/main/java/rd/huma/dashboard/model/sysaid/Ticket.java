package rd.huma.dashboard.model.sysaid;

public class Ticket {

	private long ticket;

	private int estado;

	public Ticket(long ticket, int estado) {
		this.ticket = ticket;
		this.estado = estado;
	}

	public long getTicket() {
		return ticket;
	}

	public void setTicket(long ticket) {
		this.ticket = ticket;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}