package rd.huma.dashboard.servicios.integracion.nexus;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.servicios.background.ejecutores.version.BuscadorModulos;
import rd.huma.dashboard.servicios.integracion.svn.ArtefactoParaBorrar;

public class ServicioNexusTest {

	@Test @Ignore
	public void probar(){
		ServicioNexus.nuevo(new EntConfiguracionGeneral()).eliminarModulo("dr.gov.sigef", "admfinanciera", "10.0.1.0.0");
	}

	@Test  @Ignore
	public void versionesSinTag(){

		EntAplicacion aplicacion = new EntAplicacion();
		aplicacion.setSvnPath("sigef");

		ServicioNexus servicioNexus = SimulaNexus.nuevo();
		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();
		List<EntVersionModulo> modulos = new BuscadorModulos(configuracionGeneral, aplicacion, new EntVersion()).buscar("/sigef/trunk/sigef/");

		Artefacto artifact = new Artefacto();
		artifact.setArtefacto("sigef_actual");
		artifact.setGrupo("dr.gov.sigef");
		List<String> versiones = servicioNexus.getVersions(artifact);
		for (String version : versiones) {
			String[] numeros = version.split("\\.");
			if (numeros.length ==3 && Long.valueOf(numeros[2])<700){

				System.out.println(version);
				for (EntVersionModulo m : modulos) {
					ArtefactoParaBorrar artefactoParaBorrar = new ArtefactoParaBorrar(m.getArtefacto().getGrupo(), m.getArtefacto().getArtefacto());
					servicioNexus.eliminarModulo(artefactoParaBorrar.getGrupo(),artefactoParaBorrar.getArtefacto(), version);
				}
			}
		}
	}
}
