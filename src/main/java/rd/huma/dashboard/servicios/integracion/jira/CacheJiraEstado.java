package rd.huma.dashboard.servicios.integracion.jira;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rd.huma.dashboard.model.jira.Fields;
import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.jira.Status;
import rd.huma.dashboard.model.transaccional.EntJiraEstado;
import rd.huma.dashboard.servicios.transaccional.ServicioJira;

public class CacheJiraEstado {

	private static final Map<String, EntJiraEstado> CACHE_ESTADOS = new ConcurrentHashMap<>();


	public static EntJiraEstado at(String codigo){
		return CACHE_ESTADOS.get(codigo);
	}

	public static void asignarJiraEstado(EntJiraEstado jiraEstado){
		CACHE_ESTADOS.put(jiraEstado.getCodigo(), jiraEstado);
	}

	public static EntJiraEstado at(Issues issues){
		return at(issues.getFields().getStatus());
	}

	public static EntJiraEstado at(Fields fields){
		return at(fields.getStatus());
	}

	public static EntJiraEstado at(Status status){
		return at(status.getId(),status.getName());
	}

	public static EntJiraEstado at(String codigo, String descripcion){
		EntJiraEstado estado = CACHE_ESTADOS.get(codigo);
		if (estado == null) {
			estado = ServicioJira.getInstanciaTransaccional().encuentraOSalvaJiraEstado(codigo, descripcion);
			CACHE_ESTADOS.putIfAbsent(codigo, estado);
		}
		return estado;
	}
}