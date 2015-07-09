package rd.huma.dashboard.servicios.web.simulacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntPersona;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionDuenos;
import rd.huma.dashboard.model.transaccional.EntVersionJira;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;

public class SimulaVersion {

	
	private static List<EntVersion> versiones = new ArrayList<EntVersion>();
	private static List<EntVersion> versionesServidores = new ArrayList<EntVersion>();
	private static Map<EntVersion, List<EntVersionJira>> jiras = new HashMap<>();
	private static Map<EntVersion, List<EntVersionTicket>> tickets = new HashMap<>();
	private static Map<EntVersion, List<EntVersionDuenos>> duenos = new HashMap<>();
	
	static{
		for (int i= 0;i<=new Random(1).nextInt(7);i++){
			nuevaVersion(versiones);
		}
		nuevaVersion(versionesServidores);
	}
	
	public static List<EntVersion> getVersiones() {
		return versiones;
	}
	
	public static List<EntVersion> getVersionesServidores() {
		return versionesServidores;
	}
	
	public static Map<EntVersion, List<EntVersionJira>> getJiras() {
		return jiras;
	}
	
	public static Map<EntVersion, List<EntVersionTicket>> getTickets() {
		return tickets;
	}
	
	public static Map<EntVersion, List<EntVersionDuenos>> getDuenos() {
		return duenos;
	}
	
	private static void nuevaVersion(List<EntVersion> versiones){
		EntVersion version = new EntVersion();
		version.setNumero(UUID.randomUUID().toString());
		version.setAutor(UUID.randomUUID().toString());
		version.setBranchOrigen(UUID.randomUUID().toString());
		
		
		versiones.add(version);
		for (int i= 0;i<=3;i++){
			EntJira jira = new EntJira();
			jira.setEstado("ABIERTO");
			jira.setNumero(UUID.randomUUID().toString());
			
			EntVersionJira versionJira = new EntVersionJira();
			versionJira.setJira(jira);
			versionJira.setVersion(version);
			List<EntVersionJira> lst = jiras.get(version);
			if (lst == null){
				lst = new ArrayList<>();
				jiras.put(version, lst);
			}
			lst.add(versionJira);;
		}
		
		for (int i= 0;i<=2;i++){
			EntTicketSysAid jira = new EntTicketSysAid();
			jira.setNumero(UUID.randomUUID().toString());
			
			EntVersionTicket versionJira = new EntVersionTicket();
			versionJira.setTicketSysAid(jira);
			versionJira.setVersion(version);
			
			List<EntVersionTicket> lst = tickets.get(version);
			if (lst == null){
				lst = new ArrayList<>();
				tickets.put(version, lst);
			}
			lst.add(versionJira);
		}
		
		for (int i= 0;i<=3;i++){
			EntPersona jira = new EntPersona();
			jira.setUsuarioSvn(UUID.randomUUID().toString());
			jira.setNombre(jira.getUsuarioSvn());
			jira.setCorreo("depende@gmail.com");
			
			
			EntVersionDuenos versionJira = new EntVersionDuenos();
			versionJira.setDueno(jira);
			versionJira.setVersion(version);
			
			List<EntVersionDuenos> lst = duenos.get(version);
			if (lst == null){
				lst = new ArrayList<>();
				duenos.put(version, lst);
			}
			lst.add(versionJira);
		}
		
	}
}