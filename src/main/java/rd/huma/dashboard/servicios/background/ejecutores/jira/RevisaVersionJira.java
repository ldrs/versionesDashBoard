package rd.huma.dashboard.servicios.background.ejecutores.jira;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.EntJiraParticipante;
import rd.huma.dashboard.model.transaccional.EntTicketSysAid;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionScript;
import rd.huma.dashboard.servicios.background.ejecutores.version.ColectorInformacionFieldsJira;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class RevisaVersionJira {

	private EntJira jira;
	private Set<EntVersionScript> scripts = new HashSet<>();
	private Set<String> duenos= new HashSet<>();
	private Set<EntJiraParticipante> participantes= new HashSet<>();
	private Set<EntTicketSysAid> ticketSysAids= new HashSet<>();
	private Set<String> reportes = new HashSet<>();
	private EntVersion version;
	private Fields fields;
	private ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();

	public RevisaVersionJira(EntJira jira, EntVersion version, Fields fields) {
		this.jira = jira;
		this.version = version;
		this.fields = fields;
	}

	 void ejecutar(){
		new ColectorInformacionFieldsJira(fields, duenos, participantes, scripts, jira, ticketSysAids, reportes).procesar();


		 Set<String> versionParticipantes = getParticipantes();

		 participantes.stream().filter(p -> !versionParticipantes.contains(p.getParticipante().getUsuarioSvn())).collect(Collectors.toSet());

	}

	 private Set<String> getParticipantes(){
		 Set<String> versionParticipantes = new HashSet<>();

			servicioVersion.buscaParticipantes(version).stream().forEach( participante ->  versionParticipantes.add(participante.getParticipante().getUsuarioSvn()));
			return versionParticipantes;
	 }


}