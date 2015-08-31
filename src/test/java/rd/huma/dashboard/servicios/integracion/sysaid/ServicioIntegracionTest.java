package rd.huma.dashboard.servicios.integracion.sysaid;

import java.util.Optional;

import org.junit.Test;
import org.omg.CORBA.TCKind;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

import com.ilient.api.ApiServiceRequest;
import com.ilient.api.ApiServiceRequest.CustomFields.Entry;
import com.ilient.api.SysaidApiService;
import com.ilient.api.SysaidApiServiceService;

public class ServicioIntegracionTest {

	private static final String ACCONT_ID = "ministeriodehacienda";
	private static final String USER_ID = "integrador";
	private static final String PASS_ID = "integrador";

	public void probar(){
		SysaidApiService service = new SysaidApiServiceService().getSysaidApiServicePort();
		long sessionId = service.login(ACCONT_ID,USER_ID,PASS_ID);
		ApiServiceRequest sr = (ApiServiceRequest)service. loadById(sessionId,new ApiServiceRequest(),9301);



		System.out.println(sr.getStatus());

		System.out.println(sr.getAssignedTo());
		for (Entry  entry : sr.getCustomFields().getEntry()){
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}



		service.logout(sessionId);
	}

	public void probar2(){
		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();
	 	Optional<Ticket> ticket =  ServicioIntegracionSYSAID.instancia().getTicket(configuracionGeneral,9301);
	 	if (ticket.isPresent()){
	 		System.out.println(ticket.get().getTicket() + ticket.get().getEstado());
	 	}
	}
}
