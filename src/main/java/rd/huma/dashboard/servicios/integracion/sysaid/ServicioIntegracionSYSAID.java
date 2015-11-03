package rd.huma.dashboard.servicios.integracion.sysaid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;

import com.ilient.api.ApiServiceRequest;
import com.ilient.api.SysaidApiService;
import com.ilient.api.SysaidApiServiceService;

public final class ServicioIntegracionSYSAID {
	//private static final Logger LOGGER = Logger.getLogger("ServicioIntegracionSYSAID");


	private static final String ACCONT_ID = "ministeriodehacienda";


	private ServicioIntegracionSYSAID() {
	}

	public Optional<Ticket> getTicket(Long ticket){
		return getTicket(ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get(), ticket);
	}

	public Optional<Ticket> getTicket(EntConfiguracionGeneral configuracionGeneral, Long ticket){
		try{

			SysaidApiService service = new SysaidApiServiceService().getSysaidApiServicePort();
			long sessionId = service.login(ACCONT_ID,configuracionGeneral.getUsrSysaid(),configuracionGeneral.getPwdSysaid());
			try{
				ApiServiceRequest sr = (ApiServiceRequest)service. loadById(sessionId,new ApiServiceRequest(), ticket.intValue());

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

	public List<Ticket> getTickets(EntConfiguracionGeneral configuracionGeneral, Long... tickets){
		List<Ticket> ticketsRetorno = new ArrayList<>();
		try{

			SysaidApiService service = new SysaidApiServiceService().getSysaidApiServicePort();
			long sessionId = service.login(ACCONT_ID,configuracionGeneral.getUsrSysaid(),configuracionGeneral.getPwdSysaid());
			try {

				for (Long ticket : tickets) {
					ApiServiceRequest sr = (ApiServiceRequest)service. loadById(sessionId,new ApiServiceRequest(), ticket.intValue());
				//	LOGGER.info(String.format("Buscando ticket %s y fue encontrado %s " , String.valueOf(ticket),String.valueOf(sr!=null)));
					if (sr!=null){
						ticketsRetorno.add(new Ticket(ticket, sr.getStatus()));
					}
				}
			}finally{
				service.logout(sessionId);
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		return ticketsRetorno;
	}

	public static ServicioIntegracionSYSAID instancia() {
		return new ServicioIntegracionSYSAID();
	}
}