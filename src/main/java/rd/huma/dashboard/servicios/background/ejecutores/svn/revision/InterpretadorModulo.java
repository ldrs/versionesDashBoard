package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import java.util.List;
import java.util.Optional;

import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionModulo;

public class InterpretadorModulo {

	private List<EntAplicacionConfiguracionModulo> configuracionBuscaModulos;

	public InterpretadorModulo(List<EntAplicacionConfiguracionModulo> configuracionBuscaModulos) {
		this.configuracionBuscaModulos = configuracionBuscaModulos;
	}


	public Optional<LineaIntepretadaModulo> interpretador(String lineaCambio){
		for (EntAplicacionConfiguracionModulo conf : configuracionBuscaModulos){
			int idxDespuesObjectivo = lineaCambio.indexOf(conf.getDespuesObjectivo());
			if (idxDespuesObjectivo == -1){
				continue;
			}
			lineaCambio = lineaCambio.substring(idxDespuesObjectivo);
			idxDespuesObjectivo = lineaCambio.indexOf(conf.getAntesObjectivo());
			if (idxDespuesObjectivo == -1){
				continue;
			}
			return Optional.of(new LineaIntepretadaModulo(lineaCambio.substring(idxDespuesObjectivo), lineaCambio.substring(0,idxDespuesObjectivo)));
		}
		return Optional.empty();
	}
}

class LineaIntepretadaModulo{
	private String linea;
	private String modulo;

	LineaIntepretadaModulo(String linea, String modulo) {
		this.linea = linea;
		this.modulo = modulo;
	}

	public String getLinea() {
		return linea;
	}

	public String getModulo() {
		return modulo;
	}
}