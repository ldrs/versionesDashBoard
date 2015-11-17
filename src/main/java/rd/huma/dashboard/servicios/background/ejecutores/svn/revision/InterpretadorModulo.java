package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import java.util.List;
import java.util.Optional;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionModulo;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;

public class InterpretadorModulo {

	private List<EntAplicacionConfiguracionModulo> configuracionBuscaModulos;
	private EntAplicacion aplicacion;
	private ServicioBranch servicioBranch;

	public InterpretadorModulo(EntAplicacion aplicacion, List<EntAplicacionConfiguracionModulo> configuracionBuscaModulos) {
		this.aplicacion = aplicacion;
		this.configuracionBuscaModulos = configuracionBuscaModulos;
	}


	public Optional<LineaIntepretadaModulo> interpretador(String lineaCambio){
		for (EntAplicacionConfiguracionModulo conf : configuracionBuscaModulos){
			int idx = lineaCambio.indexOf(conf.getAntesObjectivo());
			if (idx == -1){
				continue;
			}
			lineaCambio = lineaCambio.substring(conf.getAntesObjectivo().length()+ idx);
			idx = lineaCambio.indexOf(conf.getDespuesObjectivo());
			if (idx == -1){
				continue;
			}
			String modulo = lineaCambio.substring(0,idx);
			if (modulo.isEmpty()){
				return Optional.empty();
			}

			return Optional.of(new LineaIntepretadaModulo(lineaCambio.substring(idx), getModulo(modulo)));
		}
		return Optional.empty();
	}

	private EntAplicacionModulo getModulo(String nombre){
		 Optional<EntAplicacionModulo> posibleResultado = servicioBranch.buscaModulo(aplicacion, nombre).stream().findFirst();
		 if (posibleResultado.isPresent()){
			 return posibleResultado.get();
		 }

		EntAplicacionModulo modulo = new EntAplicacionModulo();
		modulo.setNombre(nombre);
		modulo.setAplicacion(aplicacion);

		return servicioBranch.grabar(modulo);
	}

	public void setServicioBranch(ServicioBranch servicioBranch) {
		this.servicioBranch = servicioBranch;
	}
}

class LineaIntepretadaModulo{
	private String linea;
	private EntAplicacionModulo modulo;

	LineaIntepretadaModulo(String linea, EntAplicacionModulo modulo) {
		this.linea = linea;
		this.modulo = modulo;
	}

	public String getLinea() {
		return linea;
	}

	public EntAplicacionModulo getModulo() {
		return modulo;
	}
}