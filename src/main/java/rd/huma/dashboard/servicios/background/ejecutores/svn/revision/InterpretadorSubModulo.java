package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionSubModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionSubModulo;
import rd.huma.dashboard.model.transaccional.dominio.ETipoCambioFuente;

public class InterpretadorSubModulo {

	private List<EntAplicacionConfiguracionSubModulo> subModulosConfiguracion;

	public InterpretadorSubModulo(List<EntAplicacionConfiguracionSubModulo> subModulosConfiguracion) {
		this.subModulosConfiguracion = subModulosConfiguracion;
	}

	public Optional<LineaInterpretadaSubModulo> interperta(EntAplicacionModulo modulo, ETipoCambioFuente cambioFuente, String cambio){

		String[] paths = cambio.split("/");
		if (!paths[paths.length-1].contains(".")){
			return Optional.empty();
		}
		int indexUltimoSubModulo = paths.length-2;
		for (int idx = paths.length-2;idx>=0;idx--){
			String pathActual = paths[idx];
			for (EntAplicacionConfiguracionSubModulo configuracionSubModulo : subModulosConfiguracion) {
				if (pathActual.startsWith(configuracionSubModulo.getAntes())){
					indexUltimoSubModulo = idx-1;
				}
			}
		}
		EntAplicacionSubModulo padre = null;
		List<EntAplicacionSubModulo> retorno = new ArrayList<>();
		for(int idx=0;idx<=indexUltimoSubModulo;idx++){
			EntAplicacionSubModulo subModulo = getSubModulo(modulo, paths[idx]);
			subModulo.setSubModuloPadre(padre);
			padre = subModulo;
			retorno.add(subModulo);
		}
		return Optional.of(new LineaInterpretadaSubModulo(retorno, String.join("", Arrays.copyOfRange(paths, indexUltimoSubModulo+1, paths.length)), padre));
	}

	private EntAplicacionSubModulo getSubModulo(EntAplicacionModulo modulo, String nombre){
		 EntAplicacionSubModulo submodulo = new EntAplicacionSubModulo();
		 submodulo.setNombre(nombre);
		 submodulo.setModulo(modulo);
		 return submodulo;
	}

}

class LineaInterpretadaSubModulo{
	private List<EntAplicacionSubModulo> subModulos;
	private String cambio;
	private EntAplicacionSubModulo subModuloActual;

	LineaInterpretadaSubModulo(List<EntAplicacionSubModulo> subModulos,	String cambio, EntAplicacionSubModulo subModuloActual) {
		this.subModulos = subModulos;
		this.cambio = cambio;
		this.subModuloActual = subModuloActual;
	}


	public EntAplicacionSubModulo getSubModuloActual() {
		return subModuloActual;
	}

	public String getCambio() {
		return cambio;
	}

	public List<EntAplicacionSubModulo> getSubModulos() {
		return subModulos;
	}

}