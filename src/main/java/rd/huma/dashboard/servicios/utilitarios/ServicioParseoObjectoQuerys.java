package rd.huma.dashboard.servicios.utilitarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;
import rd.huma.dashboard.model.transaccional.dominio.ETipoCambioTabla;
import rd.huma.dashboard.model.transaccional.dominio.ObjectoCambio;

public class ServicioParseoObjectoQuerys {

	private String datos;

	public ServicioParseoObjectoQuerys(String datos){
		this.datos = datos;
	}

	public Map<ObjectoCambio, Integer>  buscar(){
		Map<ObjectoCambio, Integer> mapping = new HashMap<>();

		for (ObjectoCambio objecto :  buscaObjectosSQL(datos)){
			Integer veces =  mapping.get(objecto);
			if (veces == null){
				mapping.put(objecto, Integer.valueOf(1));
			}else{
				mapping.put(objecto, veces+1);
			}
		}

		return mapping;
	}


	private void buscandoInformacion(String query, ETipoCambioTabla tipo, List<ObjectoCambio> l) throws JSQLParserException{
		if (tipo == ETipoCambioTabla.INSERT_INTO){
		}else{
			if (Arrays.stream(query.split("")).filter(s -> "'".equals(s)).count()==1){
				query = query.replace('\'', ' ');
			}

			ObjectoCambio objectoCambio = tipo.parsear(query);
			if (objectoCambio != null){
				l.add(objectoCambio);
			}
		}
	}

	private List<ObjectoCambio> buscaObjectosSQL(String script){
		List<ObjectoCambio> listaObjectos = new ArrayList<>();

		String[] datos = script.toUpperCase().split(";");
		for (String dato : datos) {
			dato = dato.replace('\n', ' ').trim();
			for(ETipoCambioTabla tipo : ETipoCambioTabla.values()){
				if (dato.matches(tipo.regex()) || (tipo.inicioComandoBuscar()!=null && dato.indexOf(tipo.inicioComandoBuscar())!=-1)){
					if (tipo.inicioComandoBuscar()!=null){
						dato = dato.substring(dato.indexOf(tipo.inicioComandoBuscar()));
					}
					try{
						buscandoInformacion(dato,tipo,listaObjectos);
					}catch(JSQLParserException e){

					}
				}
			}
		}
		return listaObjectos;
	}
}