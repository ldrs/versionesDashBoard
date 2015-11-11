package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacionCatalogoCambio;
import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionSubModulo;
import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.SimulaServicioSVN;

public class ProcesarSVNRevisionesTest {

	@Test
	public void probar(){
		ServicioSVN servicioSVN =  SimulaServicioSVN.servicioSvn();

		EntBranch branch = new EntBranch();
		branch.setAplicacion(servicioSVN.getAplicacion());
		branch.setBranch("tk10357A");
		branch.setRevisionUltima(21839);


		EntAplicacionConfiguracionModulo configuracionRecursos = new EntAplicacionConfiguracionModulo();
		configuracionRecursos.setAntesObjectivo("/META-INF/resources/");
		configuracionRecursos.setDespuesObjectivo("/");
		configuracionRecursos.setAplicacion(servicioSVN.getAplicacion());


		EntAplicacionConfiguracionModulo configuracionJava = new EntAplicacionConfiguracionModulo();
		configuracionJava.setAntesObjectivo("/dr/gov/sigef/");
		configuracionJava.setDespuesObjectivo("/");
		configuracionJava.setAplicacion(servicioSVN.getAplicacion());


		new ProcesarSVNReviciones().ejecutar(servicioSVN,Arrays.asList(configuracionJava,configuracionRecursos), modulos(servicioSVN.getAplicacion()) , catalogos(servicioSVN.getAplicacion()),  Arrays.asList(branch));
	}

	private List<EntAplicacionConfiguracionSubModulo> modulos(EntAplicacion aplicacion){
		return Arrays.asList(
					subModulo(aplicacion, "reglas"),
					subModulo(aplicacion, "web")
				);
	}

	private EntAplicacionConfiguracionSubModulo subModulo(EntAplicacion aplicacion, String antes){
		EntAplicacionConfiguracionSubModulo confSubModulo = new EntAplicacionConfiguracionSubModulo();
		confSubModulo.setAplicacion(aplicacion);
		confSubModulo.setAntes(antes);
		return confSubModulo;

	}

	private List<EntAplicacionCatalogoCambio> catalogos(EntAplicacion aplicacion){
		return Arrays.asList(
							nuevoCatalogo(aplicacion, false, false, "ReglaValidacion"),
							nuevoCatalogo(aplicacion, false, false, "ReglaAccion"),
							nuevoCatalogo(aplicacion, false, false, "MapDB")
						);
	}

	private EntAplicacionCatalogoCambio nuevoCatalogo(EntAplicacion aplicacion, boolean agrupador, boolean otro, String valor){
		EntAplicacionCatalogoCambio catalogoCambio = new EntAplicacionCatalogoCambio();
		catalogoCambio.setAplicacion(aplicacion);
		catalogoCambio.setAgrupador(agrupador);
		catalogoCambio.setOtro(otro);
		catalogoCambio.setValor(valor);
		return catalogoCambio;
	}
}