package rd.huma.dashboard.servicios.integracion.svn.ambiente;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.maven.Dependency;
import rd.huma.dashboard.model.maven.Model;
import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntAmbienteSVN;
import rd.huma.dashboard.model.transaccional.EntAmbienteSVNModulo;


public class ServicioAmbienteSvnBuscaModulos {

	private EntAmbienteSVN ambienteSVN;

	public ServicioAmbienteSvnBuscaModulos(EntAmbienteSVN ambienteSVN) {
		this.ambienteSVN = ambienteSVN;
	}

	public List<EntAmbienteSVNModulo> procesar(){
		List<EntAmbienteSVNModulo> modulos = new ArrayList<>();
		Builder tmp = ClientBuilder.newClient().target(ambienteSVN.getRutaSvnAmbiente() +"/pom.xml").request().accept(MediaType.MEDIA_TYPE_WILDCARD);
		Model project = tmp.get(Model.class);

		for (Dependency dependencia : project.getDependencies().getDependency()){
			if (dependencia.getGroupId().startsWith("dr.gov")){

				EntAmbienteSVNModulo ambienteSVNModulo = new EntAmbienteSVNModulo();
				ambienteSVNModulo.setAmbienteSVN(ambienteSVN);
				Artefacto artefacto = new Artefacto();

				artefacto.setArtefacto(dependencia.getArtifactId());
				artefacto.setGrupo(dependencia.getGroupId());
				artefacto.setPaquete(dependencia.getType());
				ambienteSVNModulo.setArtefacto(artefacto);
				modulos.add(ambienteSVNModulo);
			}

		}
		return modulos;
	}
}