package rd.huma.dashboard.servicios.integracion.sysaid;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

import com.ilient.api.ApiServiceRequest;
import com.ilient.api.SysaidApiService;
import com.ilient.api.SysaidApiServiceService;

public class ServicioIntegracionTest {

	private static final String ACCONT_ID = "ministeriodehacienda";
	private static final String USER_ID = "integrador";
	private static final String PASS_ID = "integrador";

	public void probar(){
		SysaidApiService service = new SysaidApiServiceService().getSysaidApiServicePort();
		long sessionId = service.login(ACCONT_ID,USER_ID,PASS_ID);
		ApiServiceRequest sr = (ApiServiceRequest)service. loadById(sessionId,new ApiServiceRequest(),9600);


		ApiServiceRequest sr2 = (ApiServiceRequest)service. loadById(sessionId,new ApiServiceRequest(),10119);

		//System.out.println(sr.getDescription());
//		System.out.println(sr.getSrSubType());
//		System.out.println(sr.getSource());
//		System.out.println(sr.getAgreement());
//

		System.out.println("Estado 1 ->" + sr.getStatus());
		System.out.println("Estado 2 ->" + sr2.getStatus());

	//	System.out.println(sr.getAssignedTo());
//		for (Entry  entry : sr.getCustomFields().getEntry()){
//			System.out.println(entry.getKey() + "-" + entry.getValue());
//		}



		service.logout(sessionId);
	}


	//@Test
	public void probar3(){
		System.out.println("probar3");
		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();
		 List<Ticket> tick =  ServicioIntegracionSYSAID.instancia().getTickets(configuracionGeneral,9612L,9060L,10119L);
		for (Ticket ticket : tick) {
			System.out.println(ticket.getTicket() +" " + ticket.getEstado());
		}
	}

//	@Test
	public void probar2(){
		System.out.println("probar2");
		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();
	 	Optional<Ticket> ticket =  ServicioIntegracionSYSAID.instancia().getTicket(configuracionGeneral,9612L);
	 	if (ticket.isPresent()){
	 		System.out.println(ticket.get().getTicket() +" " + ticket.get().getEstado());
	 	}
	}
}
