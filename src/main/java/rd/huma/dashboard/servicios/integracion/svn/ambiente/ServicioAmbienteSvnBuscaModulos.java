package rd.huma.dashboard.servicios.integracion.svn.ambiente;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.maven.Dependency;
import rd.huma.dashboard.model.maven.Project;
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
		Project project = tmp.get(Project.class);

		for (Dependency dependencia : project.getDependencies().getDependency()){
			if (dependencia.getGroupId().startsWith("dr.gov")){

				EntAmbienteSVNModulo ambienteSVNModulo = new EntAmbienteSVNModulo();
				ambienteSVNModulo.setAmbienteSVN(ambienteSVN);
				ambienteSVNModulo.setArtefacto(dependencia.getArtifactId());
				ambienteSVNModulo.setGrupo(dependencia.getGroupId());
				ambienteSVNModulo.setPaquete(dependencia.getType());
				modulos.add(ambienteSVNModulo);
			}

		}
		return modulos;
	}
}