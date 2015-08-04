package rd.huma.dashboard.model.sysaid;

public class ExceptionTicketNoEncontrado extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 7100107471018638029L;

	public ExceptionTicketNoEncontrado(long ticket) {
		super(ticket + " No ha sido encontrado");
	}

}
