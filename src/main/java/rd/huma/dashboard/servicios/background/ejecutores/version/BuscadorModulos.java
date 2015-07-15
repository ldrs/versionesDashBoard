package rd.huma.dashboard.servicios.background.ejecutores.version;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

import rd.huma.dashboard.model.maven.Project;
import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;

public class BuscadorModulos {

	private EntConfiguracionGeneral configuracionGeneral;
	private EntVersion version;
	private EntAplicacion aplicacion;
	private ServicioVersion servicioVersion = ServicioVersion.getInstanciaTransaccional();

	public BuscadorModulos(EntConfiguracionGeneral configuracionGeneral, EntAplicacion aplicacion, EntVersion version) {
		this.configuracionGeneral = configuracionGeneral;
		this.version = version;
		this.aplicacion = aplicacion;
	}


	public List<EntVersionModulo> buscar(){
		return encuentraModulos(true,getRutaSvn(), new ArrayList<EntVersionModulo>());
	}

	private List<EntVersionModulo> encuentraModulos(boolean root, String ruta , List<EntVersionModulo> modulos){
		Builder tmp = ClientBuilder.newClient().target(ruta+"/pom.xml").request().accept(MediaType.MEDIA_TYPE_WILDCARD);
		Project project = tmp.get(Project.class);
		if (project.getModules() != null){
			for (String modulo : project.getModules().getModule()){
				encuentraModulos(false,ruta+"/"+modulo, modulos);
			}
		}
		EntVersionModulo versionModulo = new EntVersionModulo();
		versionModulo.setVersion(version);
		 Artefacto artefacto =  new Artefacto();
		 artefacto.setArtefacto(project.getArtifactId());
		 artefacto.setGrupo(project.getGroupId() == null ? project.getParent().getGroupId() : project.getGroupId());
		 artefacto.setPaquete(project.getPackaging() == null ? "jar" : project.getPackaging());
		 versionModulo.setArtefacto(artefacto);
		modulos.add(versionModulo);

		if (root){
			String propiedad = tmp.get(String.class);
			int index = propiedad.indexOf("<rutaSvnAmbiente>");
			if (index == -1){
				version.setRutaSvnAmbiente(aplicacion.getRutaSvnAmbiente());
			}else{
				version.setRutaSvnAmbiente( propiedad.substring(index, propiedad.indexOf("</rutaSvnAmbiente>")));
			}
		}

		return modulos;
	}

	private String getRutaSvn(){
		return configuracionGeneral.getRutaSvn() + version.getSvnOrigen() + "/branches/" + version.getBranchOrigen();
	}


	public void procesar() {
		for (EntVersionModulo versionModulo : buscar()){
			servicioVersion.crearVersionModulo(versionModulo);
		}
		servicioVersion.merge(version);
	}

}