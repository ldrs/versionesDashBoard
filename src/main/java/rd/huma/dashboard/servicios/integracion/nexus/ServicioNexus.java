package rd.huma.dashboard.servicios.integracion.nexus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import rd.huma.dashboard.model.nexus.Data;
import rd.huma.dashboard.model.nexus.Nexus;
import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;

public class ServicioNexus {

	private EntConfiguracionGeneral configuracionGeneral;

	private ServicioNexus(){}

	private ServicioNexus inicializar(){
		return inicializar(ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get());
	}

	private ServicioNexus inicializar(EntConfiguracionGeneral configuracionGeneral){
		this.configuracionGeneral = configuracionGeneral;
		return this;
	}

	public static ServicioNexus nuevo(){
		return new ServicioNexus().inicializar();
	}

	static ServicioNexus nuevo(EntConfiguracionGeneral configuracionGeneral){
		return new ServicioNexus().inicializar(configuracionGeneral);
	}

	public int eliminarModulo(String grupo, String artefacto, String version) {
		String url = new StringBuilder(150).append(configuracionGeneral.getRutaNexus()).append("/service/local/repositories/releases/content/").append(grupo.replace('.', '/')).append('/').append(artefacto).append('/').append(version).toString();
		return ClientBuilder.newClient().register(new Authenticator(configuracionGeneral.getUsrNexus(), configuracionGeneral.getPwdNexus())).target(url).request().delete().getStatus();
	}

	public List<String> getVersions(Artefacto artifact){
		try {

			Client client = ClientBuilder.newClient();


			WebTarget webResource = client.target(new StringBuilder(350).append(configuracionGeneral.getRutaNexus()).append("/service/local/lucene/search?g=").append(artifact.getGrupo()).append("&a=").append(artifact.getArtefacto()).append("&repositoryId=releases").toString());

			Response response = webResource.request("application/json").get(Response.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
			}

			Nexus output = response.readEntity(Nexus.class);

			List<String> versiones = new ArrayList<String>();
			for (Data data : output.getData() ) {
				versiones.add(data.getVersion());
			}
			return versiones;

		  } catch (Exception e) {
			e.printStackTrace();
		  }
		return Collections.emptyList();
	}


	public class Authenticator implements ClientRequestFilter {

	    private final String user;
	    private final String password;

	    public Authenticator(String user, String password) {
	        this.user = user;
	        this.password = password;
	    }

	    public void filter(ClientRequestContext requestContext) throws IOException {
	        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
	        final String basicAuthentication = getBasicAuthentication();
	        headers.add("Authorization", basicAuthentication);

	    }

	    private String getBasicAuthentication() {
	        String token = this.user + ":" + this.password;
	        try {
	            return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
	        } catch (UnsupportedEncodingException ex) {
	            throw new IllegalStateException("Cannot encode with UTF-8", ex);
	        }
	    }
	}
}