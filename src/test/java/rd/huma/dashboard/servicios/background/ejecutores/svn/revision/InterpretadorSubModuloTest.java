package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import rd.huma.dashboard.model.transaccional.AEntModelo;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionSubModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionSubModulo;
import rd.huma.dashboard.model.transaccional.dominio.ETipoCambioFuente;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.integracion.svn.SimulaServicioSVN;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;

public class InterpretadorSubModuloTest {

	@Test
	public void probar(){
		ServicioSVN svn = SimulaServicioSVN.servicioSvn();

		InterpretadorSubModulo t = new InterpretadorSubModulo(modulos(svn.getAplicacion()));
		t.setServicioBranch(mockServicioBranch());
		Assert.assertTrue(
				t.interperta(modulo(svn.getAplicacion()), ETipoCambioFuente.MODIFICACION, "/unidadcontable/servicios/DefinicionUnidadContableDescentralizadas.java").isPresent()
		);

	}

	private EntAplicacionModulo modulo(EntAplicacion aplicacion){
		EntAplicacionModulo mod = new EntAplicacionModulo();
		mod.setAplicacion(aplicacion);
		mod.setNombre("clasificadores");
		return mod;
	}

	private ServicioBranch mockServicioBranch(){
		ServicioBranch servicioBranch = mock(ServicioBranch.class);
		stub(servicioBranch.grabar(any(AEntModelo.class))).toAnswer(this::mockResultado);
		return servicioBranch;
	}

	private EntAplicacionSubModulo mockResultado(InvocationOnMock metodo){
		return metodo.getArgumentAt(0, EntAplicacionSubModulo.class);
	}

	private List<EntAplicacionConfiguracionSubModulo> modulos(EntAplicacion aplicacion){
		return Arrays.asList(
					subModulo(aplicacion, "servicios"),
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
}