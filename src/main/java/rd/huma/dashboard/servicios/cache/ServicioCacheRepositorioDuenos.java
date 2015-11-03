package rd.huma.dashboard.servicios.cache;

import java.util.HashSet;
import java.util.Set;

public class ServicioCacheRepositorioDuenos {

	private static final Set<String> CACHE = new HashSet<>();

	public static void adicionar(String dueno){
		CACHE.add(dueno);
	}

	public static Set<String> getCache() {
		return CACHE;
	}
}