package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntJira;
import rd.huma.dashboard.model.jira.JiraModel;
import rd.huma.dashboard.util.Authenticator;

public class BuscadorJiraPorQueryBranch {

	private EntConfiguracionGeneral configuracionGeneral;
	private EntAplicacion aplicacion;
	private String branchOrigen;
	
	
	
	public BuscadorJiraPorQueryBranch(
			EntConfiguracionGeneral configuracionGeneral,
			EntAplicacion aplicacion, String branchOrigen) {
		this.configuracionGeneral = configuracionGeneral;
		this.aplicacion = aplicacion;
		this.branchOrigen = branchOrigen;
	}

	public List<EntJira> encuentra(){
		JiraModel modelos = ClientBuilder	.newClient()
						.target(getUrlJira())
						.register(new Authenticator(configuracionGeneral.getUsrJira(), configuracionGeneral.getPwdJira()))
						.request(MediaType.APPLICATION_JSON)
						.get(JiraModel.class)
						;
		
		List<EntJira> jiras = new ArrayList<EntJira>();
		return jiras;
	}
	
	private String getUrlJira(){
		return new StringBuilder(150)	.append(configuracionGeneral.getRutaJira())
								.append("/rest/api/2/search?")
								.append(aplicacion.getNombreCampoJiraLineaDesarrollo())
								.append("~")
								.append(branchOrigen).toString();
	}
}