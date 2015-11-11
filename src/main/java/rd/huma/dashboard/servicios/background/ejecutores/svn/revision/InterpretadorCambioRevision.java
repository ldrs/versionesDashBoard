package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import java.util.List;
import java.util.Optional;

import rd.huma.dashboard.model.transaccional.EntAplicacionCatalogoCambio;

public class InterpretadorCambioRevision {

	private List<EntAplicacionCatalogoCambio> catalogoCambioExcluyentes;
	private List<EntAplicacionCatalogoCambio> catalogoCambioOtros;

	public InterpretadorCambioRevision(List<EntAplicacionCatalogoCambio> catalogos) {
		for (EntAplicacionCatalogoCambio catalogo : catalogos) {
			if (!catalogo.isAgrupador()){
				if (catalogo.isOtro()){
					catalogoCambioOtros.add(catalogo);

				} else{
					catalogoCambioExcluyentes.add(catalogo);
				}
			}
		}
	}


	public Optional<EntAplicacionCatalogoCambio> interpretar(String cambio){

		for (EntAplicacionCatalogoCambio catalogo : catalogoCambioExcluyentes) {
			if (cambio.contains(catalogo.getValor())){
				return Optional.of(catalogo);
			}
		}

		for (EntAplicacionCatalogoCambio catalogo : catalogoCambioOtros) {
			if (cambio.matches(catalogo.getValor())){
				return Optional.of(catalogo);
			}
		}
		return Optional.empty();
	}
}