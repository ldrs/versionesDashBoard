package rd.huma.dashboard.servicios.integracion.sysaid;

import com.ilient.api.ApiServiceRequest;

import rd.huma.dashboard.model.sysaid.Ticket;

public class TransformadorTicket {

	private TransformadorTicket(){};


	public static Ticket of(ApiServiceRequest sr){
		return new Ticket(sr.getId(), sr.getStatus());
	}
}