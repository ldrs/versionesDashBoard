package rd.huma.dashboard.servicios.integracion.sysaid;

import java.util.Optional;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;

import com.ilient.api.ApiServiceRequest;
import com.ilient.api.SysaidApiService;
import com.ilient.api.SysaidApiServiceService;

public final class ServicioIntegracionSYSAID {
	private static final String ACCONT_ID = "ministeriodehacienda";


	private ServicioIntegracionSYSAID() {
	}

	public Optional<Ticket> getTicket(EntConfiguracionGeneral configuracionGeneral, long ticket){
		try{

			SysaidApiService service = new SysaidApiServiceService().getSysaidApiServicePort();
			long sessionId = service.login(ACCONT_ID,configuracionGeneral.getUsrSysaid(),configuracionGeneral.getPwdSysaid());
			try{
				ApiServiceRequest sr = (ApiServiceRequest)service. loadById(sessionId,new ApiServiceRequest(),9301);

				if (sr == null){
					return Optional.empty();
				}
				return Optional.of(new Ticket(ticket, sr.getStatus()));
			}finally {
				service.logout(sessionId);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static ServicioIntegracionSYSAID instancia() {
		return new ServicioIntegracionSYSAID();
	}
}