package rd.huma.dashboard.servicios.integracion.sysaid;

import java.time.LocalDateTime;
import java.util.Optional;

import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;

import com.ilient.api.ApiServiceRequest;
import com.ilient.api.SysaidApiService;
import com.ilient.api.SysaidApiServiceService;

public final class ServicioIntegracionSYSAID {
	private static final String ACCONT_ID = "ministeriodehacienda";
	private static final ServicioIntegracionSYSAID INSTANCIA = new ServicioIntegracionSYSAID();

	private LocalDateTime fechaUltimoUso = LocalDateTime.now();
	private long sessionIdSysAid = -1;
	private SysaidApiService service;

	private ServicioIntegracionSYSAID() {
	}

	public boolean expiroUsoServicio(){
		 return fechaUltimoUso.plusHours(2).isBefore(LocalDateTime.now());
	}

	public Optional<Ticket> getTicket(long ticket){
		garantizaInicioSesion();
		ApiServiceRequest sr = (ApiServiceRequest)service. loadById(sessionIdSysAid,new ApiServiceRequest(),ticket);
		if (sr == null){
			return Optional.empty();
		}
		return Optional.of(new Ticket(ticket, sr.getStatus()));
	}

	private void garantizaInicioSesion() {
		if (sessionIdSysAid == -1){
			EntConfiguracionGeneral configuracionGeneral = ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get();
			this.service = new SysaidApiServiceService().getSysaidApiServicePort();
			sessionIdSysAid = service.login(ACCONT_ID,configuracionGeneral.getUsrSysaid(),configuracionGeneral.getPwdSysaid());

		}

	}

	public void expirar(){
		if (service!=null){
			service.logout(sessionIdSysAid);
		}
		sessionIdSysAid = -1;
	}

	public static ServicioIntegracionSYSAID instancia() {
		return INSTANCIA;
	}
}