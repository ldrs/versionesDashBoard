package rd.huma.dashboard.servicios.web;

import static javax.json.Json.createObjectBuilder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rd.huma.dashboard.model.transaccional.EntBranch;
import rd.huma.dashboard.model.transaccional.EntBranchMerge;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionTicket;
import rd.huma.dashboard.servicios.transaccional.Servicio;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioVersion;
import rd.huma.dashboard.util.UtilFecha;
import rd.huma.dashboard.util.UtilString;

@Path("branchConsulta")
public class WSBranchConsulta {

	//private static Logger LOGGER = Logger.getLogger("NODOS");

	@Inject
	private @Servicio ServicioVersion servicioVersion;

	@Inject
	private @Servicio ServicioBranch servicioBranch;

	@GET
	@Path("consulta/{branch}")
	public String buscaVersionesBranch(@PathParam("branch") String branch){
		JsonArrayBuilder jsonVersiones = Json.createArrayBuilder();
		List<EntVersion> versiones =  servicioVersion.buscaPorBranch(branch);
		Set<String> tickets = new HashSet<>();
		versiones.forEach( v -> jsonVersiones.add(toJson(branch,v,tickets)));

		JsonArrayBuilder ticketsJson = Json.createArrayBuilder();

		tickets.forEach(ticketsJson::add);

		return Json.createObjectBuilder().add("versiones", jsonVersiones).add("tickets", ticketsJson).build().toString();
	}

	private JsonObjectBuilder toJson(String branch, EntVersion version, Set<String> tickets){
		Optional<EntBranch> posibleBranch = servicioBranch.buscaBranch(branch);

		servicioVersion.buscaNumeroTicketsSegunBranch(branch).forEach(tickets::add);


		JsonObjectBuilder origen = Json.createObjectBuilder();

		return		createObjectBuilder()
				.add("numero", version.getNumero())
				.add("versionId", version.getId())
				.add("svnOrigen", version.getSvnOrigen())
				.add("svnRevision", version.getRevisionSVN())
				.add("estado", version.getEstado().name())
				.add("autor", version.getAutor().getNombreNullSafe())
				.add("fecha", UtilFecha.getFechaFormateada(version.getFechaRegistro()))
				.add("cantidadScripts", servicioVersion.contarScriptVersion(version))
				.add("cantidadReports", servicioVersion.contarReporteVersion(version))
				.add("origen", posibleBranch.isPresent()? origen.add("branch", posibleBranch.get().getBranch()).add("revision", posibleBranch.get().getRevisionOrigen())  :origen)
				;
	}

	@GET
	@Path("origenBranch/{branch}")
	public String toJsonOrigenGrafico(@PathParam("branch") String branch){
		JsonObjectBuilder jsonVersiones = Json.createObjectBuilder();
		Optional<EntBranch> posibleBranch = servicioBranch.buscaBranch(branch);
		if (posibleBranch.isPresent()){
			creaOrigenGrafico(posibleBranch.get(), jsonVersiones);
		}

		return jsonVersiones.build().toString();
	}

	private static Comparator<EntBranch> nuevoComparadorPorRevision(){
		return (a,b)->Long.compare(a.getRevisionOrigen(),b.getRevisionOrigen());
	}

	static SortedSet<EntBranch> nuevaRama(){
		return new TreeSet<EntBranch>(nuevoComparadorPorRevision());
	}

	private void creaOrigenGrafico(EntBranch branch, JsonObjectBuilder builder){

		builder.add("branchActual", Json.createObjectBuilder().add("branch", branch.getBranch()).add("inicio", branch.getRevisionOrigen()).add("fin", branch.getRevisionUltima()).add("origenTrunk", UtilString.contiene(branch.getOrigen(), "trunk")) );
		Map<String, Nodo> mapaNodos = new HashMap<>();
		String rutaTrunk = branch.getAplicacion().getRutaPathTrunk();
		Nodo trunk = new Nodo(rutaTrunk, 0);
		mapaNodos.put(rutaTrunk, trunk);

		buscaOrigen(branch, mapaNodos, new HashSet<>());
		JsonObjectBuilder branches =  Json.createObjectBuilder();
		cargaNodos(branches,  trunk);

		builder.add("branches", branches);
	}

	private void cargaNodos(JsonObjectBuilder json , Nodo nodoActual){

		JsonArrayBuilder jsonBranches = Json.createArrayBuilder();

		for (Nodo nodo : nodoActual.getHijos()) {
			JsonObjectBuilder nodoHijo = Json.createObjectBuilder();
			nodoHijo.add("nombre", nodo.getNombre());
			cargaVersionNodo(nodo, nodoHijo);

			if (!nodo.getDestinos().isEmpty()){
				JsonArrayBuilder jsonDestinos = Json.createArrayBuilder();
				for(EntBranch merge : nodo.getDestinos()){
					jsonDestinos.add(merge.getBranch());
				}
				nodoHijo.add("destinos", jsonDestinos);
			}

			if (!nodo.getOrigenes().isEmpty()){
				JsonArrayBuilder jsonDestinos = Json.createArrayBuilder();
				for(EntBranch merge : nodo.getOrigenes()){
					jsonDestinos.add(merge.getBranch());
				}
				nodoHijo.add("origenes", jsonDestinos);
			}
			if (!nodo.getHijos().isEmpty()){
				cargaNodos(nodoHijo, nodo);
			}

			jsonBranches.add(nodoHijo);
		}

		cargaVersionNodo(nodoActual, json);

		json.add("nombre", nodoActual.getNombre()).add("branches", jsonBranches);
	}

	private void cargaVersionNodo(Nodo nodo, JsonObjectBuilder jsonNodo){
		String branch = nodo.getNombre();

		List<EntVersion> versiones = servicioVersion.buscaPorBranch(branch);
		if (!versiones.isEmpty()){
			versiones.sort(new Comparator<EntVersion>() {

				@Override
				public int compare(EntVersion o1, EntVersion o2) {
					return o1.getRevisionSVN().compareTo(o2.getRevisionSVN()) ;
				}
			});
			JsonArrayBuilder jsonVersiones = Json.createArrayBuilder();
			for (EntVersion version: versiones){
				JsonArrayBuilder tickets = Json.createArrayBuilder();
				for (EntVersionTicket ticket : servicioVersion.buscaTickets(version)){
					tickets.add(ticket.getTicketSysAid().getNumero());
				}
				jsonVersiones.add(Json.createObjectBuilder().add("numero", version.getNumero())	.add("autor", version.getAutor().getNombreNullSafe()).add("tickets", tickets) );
			}
			jsonNodo.add("versiones", jsonVersiones);
		}

	}

	private void buscaOrigen(EntBranch branch,Map<String, Nodo> nodos, Set<EntBranch> buscados){
		if (!buscados.add(branch)){
			return;
		}

		String nombreOrigen = UtilString.subStringDespues(branch.getOrigen(), "/branches/");

		EntBranch branchOrigen = null;
		if (UtilString.noContiene(branch.getOrigen(), "trunk")){

			Optional<EntBranch> posibleBranch =  servicioBranch.buscaBranch(nombreOrigen);
			if (posibleBranch.isPresent()){
				branchOrigen = posibleBranch.get();
			}
		}

		Nodo nodoEncontrado = nodos.get(nombreOrigen);
		if (nodoEncontrado == null){
			nodoEncontrado =  branchOrigen == null ? new Nodo(nombreOrigen,branch.getRevisionOrigen()-1): new Nodo(branchOrigen);
			nodos.put(nombreOrigen, nodoEncontrado);
		}
		Nodo nodoHijo =  nodos.get(branch.getBranch());
		if (nodoHijo == null){
			nodoHijo = new Nodo(branch);
			nodos.put(nodoHijo.getNombre(), nodoHijo);
		}
		nodoEncontrado.getHijos().add(nodoHijo);

		for (EntBranchMerge branchDestino : servicioBranch.buscaDestinoBranch(branch)){
			nodoHijo.getDestinos().add(branchDestino.getBranchOrigen());
			buscaOrigen(branchDestino.getBranchOrigen(), nodos,buscados);
		}
		for (EntBranchMerge origen : servicioBranch.buscaOrigenBranch(branch)){
			nodoHijo.getOrigenes().add(origen.getBranchDestino());
			buscaOrigen(origen.getBranchDestino(), nodos,buscados);
		}

		if (branchOrigen!=null){
			buscaOrigen(branchOrigen, nodos,buscados);
		}
	}
}


class Nodo implements Comparable<Nodo>{
	private String nombre;
	private long revision;
	private EntBranch branch;
	private Set<Nodo> hijos = new TreeSet<>();
	private SortedSet<EntBranch> destinos = WSBranchConsulta.nuevaRama();
	private SortedSet<EntBranch> origenes  = WSBranchConsulta.nuevaRama();

	Nodo(String nombre, long revision) {
		this.nombre = nombre;
		this.revision = revision;
	}

	Nodo(EntBranch branch) {
		this.nombre = branch.getBranch();
		this.revision = branch.getRevisionOrigen();
	}

	public String getNombre() {
		return nombre;
	}

	public long getRevision() {
		return revision;
	}

	@Override
	public int compareTo(Nodo o) {
		return Long.compare(revision, o.revision);
	}

	public EntBranch getBranch() {
		return branch;
	}

	public SortedSet<EntBranch> getOrigenes() {
		return origenes;
	}
	public SortedSet<EntBranch> getDestinos() {
		return destinos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + (int) (revision ^ (revision >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Nodo)) {
			return false;
		}
		Nodo other = (Nodo) obj;
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (revision != other.revision) {
			return false;
		}
		return true;
	}

	public Set<Nodo> getHijos() {
		return hijos;
	}
}