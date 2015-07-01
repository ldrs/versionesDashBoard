package rd.huma.dashboard.servicios.background.ejecutores.version;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import rd.huma.dashboard.model.EntAplicacion;
import rd.huma.dashboard.model.EntConfiguracionGeneral;
import rd.huma.dashboard.model.EntVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class BuscadorPropiedades {

	private EntConfiguracionGeneral configuracionGeneral;
	private EntAplicacion aplicacion;
	private EntVersion version;
	private ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();

	public BuscadorPropiedades(EntConfiguracionGeneral entConfiguracionGeneral,	EntAplicacion aplicacion, EntVersion version) {
		this.configuracionGeneral = entConfiguracionGeneral;
		this.aplicacion = aplicacion;
		this.version = version;
	}

	public void procesar(){
		Response get = ClientBuilder.newClient().target(getRutaSvn()).request().get();
		if (get.getStatus()==404){//no existe version

		}

		String datos = get.readEntity(String.class);
		for (String propiedad : aplicacion.getNombrePropiedadesPom().split(",")){
			int index = datos.indexOf("<"+propiedad+">");
			if (index!=-1){
				String tmp = datos.substring(index+propiedad.length()+2);
				servicioVersion.crearVersionPropiedad(propiedad, tmp.substring(0, tmp.indexOf("<")), version);
			}
		}
	}


	private String getRutaSvn(){
		return configuracionGeneral.getRutaSvn() + version.getSvnOrigen() + "/branches/" + version.getBranchOrigen() + "/pom.xml";
	}
}