package rd.huma.dashboard.servicios.integracion.sysaid;

import java.util.List;
import java.util.Optional;

import com.ilient.api.ApiServiceRequest;
import com.ilient.api.ApiServiceRequestActivity;
import com.ilient.api.SysaidApiService;
import com.ilient.api.SysaidApiServiceService;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

public class ServicioIntegracionTest {

	private static final String ACCONT_ID = "ministeriodehacienda";
	private static final String USER_ID = "integrador";
	private static final String PASS_ID = "integrador";

	//@Test
	public void probar(){
		SysaidApiService service = new SysaidApiServiceService().getSysaidApiServicePort();
		long sessionId = service.login(ACCONT_ID,USER_ID,PASS_ID);


		ApiServiceRequest sr = (ApiServiceRequest)service. loadById(sessionId,new ApiServiceRequest(),10221);
		ApiServiceRequestActivity at = new ApiServiceRequestActivity();
		at.setSrID(10221);
		List<String> datos = service.executeSelectQuery(sessionId, at, null);
		for (String dato : datos) {
			System.out.println("Dato ->"+dato);
			ApiServiceRequestActivity moreData = (ApiServiceRequestActivity)service. loadById(sessionId,new ApiServiceRequestActivity(),dato);

			System.out.println("MoreData-> " +moreData.getDescription());
			System.out.println("MoreData-> " +moreData.getCustNotes());
			System.out.println("MoreData ->"+ moreData.getId());
		}


	//	System.out.println(sr.getDescription());
		//System.out.println(sr.getSrSubType());
//		System.out.println(sr.getSource());
//		System.out.println(sr.getAgreement());
//



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
		 List<Ticket> tick =  ServicioIntegracionSYSAID.instancia().getTickets(configuracionGeneral,10079L,10116L,10208L,10231L);
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
