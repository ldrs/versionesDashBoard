package rd.huma.dashboard.servicios.utilitarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import rd.huma.dashboard.model.transaccional.dominio.ETipoCambioTabla;
import rd.huma.dashboard.model.transaccional.dominio.ObjectoCambio;

public class ServicioParseoObjectoQuerys {

	private String url;


	public ServicioParseoObjectoQuerys(String url){
		this.url = url;
	}


	public Map<ObjectoCambio, Integer>  buscar(){
		Map<ObjectoCambio, Integer> mapping = new HashMap<>();

		for (ObjectoCambio objecto :  busca(url)){
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
			l.add( tipo.getObjectoCambio(CCJSqlParserUtil.parse(query)));
		}

	}

	private List<ObjectoCambio> busca(String url){

		Response resultado = ClientBuilder.newClient().target(url).request().buildGet().invoke();
		if (resultado.getStatus()==200){
			return buscaObjectosSQL(resultado.readEntity(String.class));
		}
		return Collections.emptyList();
	}

	private List<ObjectoCambio> buscaObjectosSQL(String script){
		List<ObjectoCambio> listaObjectos = new ArrayList<>();

		String[] datos = script.toUpperCase().split(";");
		for (String dato : datos) {
			for(ETipoCambioTabla tipo : ETipoCambioTabla.values()){
				if (dato.matches(tipo.regex())){
					dato = dato.substring(dato.indexOf(tipo.inicioComandoBuscar()));
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