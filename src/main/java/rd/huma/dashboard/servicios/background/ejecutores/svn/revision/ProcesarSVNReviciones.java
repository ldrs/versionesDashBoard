package rd.huma.dashboard.servicios.background.ejecutores.svn.revision;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.logging.Logger;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntAplicacionCatalogoCambio;
import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionModulo;
import rd.huma.dashboard.model.transaccional.EntAplicacionConfiguracionSubModulo;
import rd.huma.dashboard.model.transaccional.EntBranchRevision;
import rd.huma.dashboard.model.transaccional.EntBranchRevisionCambio;
import rd.huma.dashboard.model.transaccional.EntBranchRevisionJira;
import rd.huma.dashboard.model.transaccional.EntBranchRevisionMerge;
import rd.huma.dashboard.model.transaccional.EntJira;
import rd.huma.dashboard.model.transaccional.dato.BranchUltimaRevision;
import rd.huma.dashboard.model.transaccional.dominio.ETipoCambioFuente;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraEnComentario;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;
import rd.huma.dashboard.servicios.integracion.svn.ServicioSVN;
import rd.huma.dashboard.servicios.transaccional.ServicioBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.servicios.transaccional.ServicioJira;
import rd.huma.dashboard.servicios.transaccional.ServicioPersona;
import rd.huma.dashboard.util.Holder;
import rd.huma.dashboard.util.UtilFecha;

public class ProcesarSVNReviciones {
	private static final Logger LOGGER = Logger.getLogger(ProcesarSVNReviciones.class.getSimpleName());

	private ServicioSVN servicioSVN;
	private InterpretadorModulo interpretadorModulo;
	private InterpretadorSubModulo intepretadorSubModulo;
	private InterpretadorCambioRevision interpretadorCambioRevision;
	private Set<BuscadorJiraEnComentario> buscadoresJiraComentario;

	private ServicioBranch servicioBranch = ServicioBranch.getInstanciaTransaccional();
	private ServicioPersona servicioPersona = ServicioPersona.getInstanciaTransaccional();

	public void ejecutar(ServicioSVN servicioSVN,List<EntAplicacionConfiguracionModulo> configuracionesModulos, List<EntAplicacionConfiguracionSubModulo> configuracionSubModulos, List<EntAplicacionCatalogoCambio> catalogos, List<BranchUltimaRevision> branchesPorRevisar,  Set<BuscadorJiraEnComentario> buscadoresJiraComentario) {
		this.servicioSVN = servicioSVN;
		interpretadorModulo = new InterpretadorModulo(servicioSVN.getAplicacion(), configuracionesModulos);
		intepretadorSubModulo = new InterpretadorSubModulo(configuracionSubModulos);
		interpretadorCambioRevision = new InterpretadorCambioRevision(catalogos);
		this.buscadoresJiraComentario = buscadoresJiraComentario;
		interpretadorModulo.setServicioBranch(servicioBranch);
		intepretadorSubModulo.setServicioBranch(servicioBranch);
		branchesPorRevisar.forEach(this::branch);
	}

	private void branch(BranchUltimaRevision branch){
		LOGGER.info(String.format("Procesando el branch %s ", branch.getBranch()));
		String comentario = servicioSVN.buscaRevisiones(branch.getBranch(),branch.getUltimaRevision());
		String[] revisionesComentario = comentario.split(ServicioSVN.SEPERADOR_REVISION);
		for (String revision : revisionesComentario) {
			if (!revision.isEmpty()){
				parseaRevision(revision,branch);
			}
		}
	}

	private void parseaRevision(String revision,BranchUltimaRevision branch){
		Holder<EntBranchRevision> holder = new Holder<>(new EntBranchRevision());
		holder.getValor().setBranch(branch.getBranch());
		Set<EntJira> jirasEncontrados = new TreeSet<>();
		Set<RevisionMerge> merges = new TreeSet<>();
		Map<String,EntBranchRevisionCambio> cambios = new HashMap<>();
		String[] lineas = revision.split("\n");

		for (int i = 1; i < lineas.length; i++) {
			String linea = lineas[i];
			intepretacionLinea(i, linea, holder, branch,jirasEncontrados,merges,cambios);
		}

		holder.setValor(servicioBranch.actualizar(holder.getValor()));
		guardarJirasEncontrados(holder.getValor(),jirasEncontrados);
		guardarCambiosDB(holder.getValor(),cambios.values());
		guardarMerges(merges,holder.getValor());
	}

	private void guardarMerges(Set<RevisionMerge> merges, EntBranchRevision valor) {
		for (RevisionMerge mergeDato : merges) {
			EntBranchRevisionMerge merge = new EntBranchRevisionMerge();
			if (valor.getRevision() == mergeDato.getOrigen()){
				merge.setRevisionOrigen(valor);
			}
			if (valor.getRevision() == mergeDato.getDestino()){
				merge.setRevisionDestino(valor);
			}
			if (merge.getRevisionDestino() == null){
				 servicioBranch.buscarRevision(mergeDato.getDestino()).stream().findFirst().ifPresent(r -> merge.setRevisionDestino(r));
			}
			if (merge.getRevisionOrigen() == null){
				 servicioBranch.buscarRevision(mergeDato.getOrigen()).stream().findFirst().ifPresent(r -> merge.setRevisionOrigen(r));
			}
			if (servicioBranch.buscaMerge(merge.getRevisionOrigen(), merge.getRevisionDestino()).isEmpty()){
				servicioBranch.grabar(merge);
			}
		}
	}

	private void guardarCambiosDB(EntBranchRevision valor, Collection<EntBranchRevisionCambio> values) {
		for (EntBranchRevisionCambio cambio : values) {
			cambio.setRevision(valor);
			servicioBranch.grabar(cambio);
		}
	}

	private void guardarJirasEncontrados(EntBranchRevision revision, Set<EntJira> jirasEncontrados) {
		ServicioJira servicioJira = ServicioJira.getInstanciaTransaccional();
		for (EntJira entJira : jirasEncontrados) {

			EntJira jiraReal = servicioJira.encuentra(entJira.getNumero()).orElseGet(creaJiraReal(entJira.getNumero()));
			if (servicioBranch.buscarRevisionJira(revision, jiraReal).isEmpty()){
				EntBranchRevisionJira revisionJira = new EntBranchRevisionJira();
				revisionJira.setJira(jiraReal);
				revisionJira.setRevision(revision);
				servicioBranch.grabar(revisionJira);
			}
		}
	}

	private Supplier<EntJira> creaJiraReal(String numero){
		return new Supplier<EntJira>() {

			@Override
			public EntJira get() {
				EntJira jiraRetorno = null;
				try{
					Optional<EntJira> encontroJira = new BuscadorJiraRestApi(new JiraQuery(ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get(), ETipoQueryJira.KEY, numero)).encuentra().stream().findFirst();
					if (encontroJira.isPresent()){
						return ServicioJira.getInstanciaTransaccional().encuentraOSalva(numero, encontroJira.get().getJiraEstado());
					}

				}catch (Exception e){
					e.printStackTrace();
				}
				jiraRetorno = new EntJira();
				jiraRetorno.setNumero(numero);
				jiraRetorno.setTicketFalso(true);
				return ServicioJira.getInstanciaTransaccional().salva(jiraRetorno);
			}
		};
	}

	private void intepretacionLinea(int i,String linea, Holder<EntBranchRevision> holder, BranchUltimaRevision branch, Set<EntJira> jirasEncontrados,Set<RevisionMerge> merges, Map<String, EntBranchRevisionCambio> cambios){
		boolean intepretaCambios = true;
		EntBranchRevision eRevision = holder.getValor();
		if (i ==1){
			String[] partes = linea.split("\\|");
			long revision =  Long.valueOf(partes[0].trim().substring(1));
			Optional<EntBranchRevision> optional =  servicioBranch.buscarRevision(revision).stream().findFirst();
			if (optional.isPresent()){
				holder.setValor(optional.get());
				eRevision = holder.getValor();
				intepretaCambios = false;
			}else{
				eRevision.setRevision(revision);
				eRevision.setPersona(servicioPersona.buscaOCreaPersona(partes[1].trim()));
				eRevision.setFechaRevision(UtilFecha.getFechaSVN(partes[2].trim()));
				if (eRevision.getRevision()>0){
					servicioBranch.grabar(eRevision);
				}
			}
		}else if (i>1 && eRevision.getRevision()>0){
			linea = linea.trim();
			if (intepretaCambios && (linea.startsWith("M ") || linea.startsWith("A ") || linea.startsWith("D "))){
				parseoLinea(linea,eRevision, branch,cambios);
			}else{
				if (linea.startsWith("Merged via: r")){
					marcaComoRevisionMerge(eRevision, linea,merges);
				}else if (intepretaCambios){
					intepracionJiras(eRevision,linea,jirasEncontrados);
				}
			}
		}
	}

	private void intepracionJiras(EntBranchRevision eRevision, String linea,Set<EntJira> jirasEncontrados) {
		buscadoresJiraComentario.forEach(buscador ->   jirasEncontrados.addAll(buscador.encuentraJira(linea)));
	}

	private void marcaComoRevisionMerge(EntBranchRevision eRevision,String linea, Set<RevisionMerge> merges) {
		String posiblesRevisionesMerges = linea.substring(12);
		if (posiblesRevisionesMerges.contains(",")){
			String[] revs= posiblesRevisionesMerges.split(",");
			for (String rev : revs) {
				merges.add(new RevisionMerge(eRevision.getRevision(), Long.valueOf(rev.trim().substring(1))));
			}
		}else{
			merges.add(new RevisionMerge(eRevision.getRevision(), Long.valueOf(posiblesRevisionesMerges.substring(1))));
		}
	}

	private void parseoLinea(String linea,EntBranchRevision eRevision,BranchUltimaRevision branch, Map<String, EntBranchRevisionCambio> cambios){
		String[] partesLineas = linea.split(" ");
		String lineaCambio = partesLineas[1];
		if (lineaCambio.isEmpty()){
			return;
		}

		String branchInterpetado = interpretaBranch(lineaCambio.substring(1));
		if (branchInterpetado!=null && !branch.getBranch().equals(branchInterpetado)){
			eRevision.setBranch(branchInterpetado);
		}


		if ("A".equals(partesLineas[0]) && partesLineas.length>2 && linea.contains("(from /") ){
			intepretaOrigen(partesLineas[3], eRevision,servicioSVN.getAplicacion());
		}


		interpretaCambios(getTipo(partesLineas[0]), lineaCambio,cambios);
	}

	private ETipoCambioFuente getTipo(String tipo){
		if (tipo.contains("A")){
			return ETipoCambioFuente.ADICION;
		}

		if (tipo.contains("M")){
			return ETipoCambioFuente.MODIFICACION;
		}
		return ETipoCambioFuente.BORRADO;
	}

	private void intepretaOrigen(String origen, EntBranchRevision eRevision, EntAplicacion aplicacion){
		if (origen.startsWith("/branches")){
			String tmpOrigen = origen.substring(10);
			int idx = tmpOrigen.indexOf(':');
			eRevision.setRevisionOrigen(Long.valueOf(tmpOrigen.substring(idx+1,tmpOrigen.length()-1)));
			int index = tmpOrigen.indexOf('/');
			eRevision.setBranchOrigen(tmpOrigen.substring(0, index == -1 ?tmpOrigen.length()  :  index));

		}else if (origen.startsWith(aplicacion.getRutaPathTrunk())){
			eRevision.setBranchOrigen(aplicacion.getRutaPathTrunk());
			int idx = origen.indexOf(':');
			eRevision.setRevisionOrigen(Long.valueOf(origen.substring(idx+1,origen.length()-1)));
		}else if (origen.startsWith("/tags")){
			String tmpOrigen = origen.substring(6);
			int idx = tmpOrigen.indexOf(':');
			eRevision.setRevisionOrigen(Long.valueOf(tmpOrigen.substring(idx+1,tmpOrigen.length()-1)));
			int index = tmpOrigen.indexOf('/');
			eRevision.setBranchOrigen(tmpOrigen.substring(0, index == -1 ?tmpOrigen.length()  :  index));
		}
		eRevision.setCreacionBranch(true);
	}

	private void interpretaCambios(ETipoCambioFuente eTipoCambioFuente, String linea, Map<String, EntBranchRevisionCambio> cambios) {
		if (linea.split("/").length>2){
			interpretadorModulo.interpretador(linea).ifPresent(m ->  interpretaModulo(eTipoCambioFuente,m,cambios))
			;
		}

	}

	private void interpretaModulo(ETipoCambioFuente cambioFuente,LineaIntepretadaModulo lineaCambiada, Map<String, EntBranchRevisionCambio> cambios) {
		intepretadorSubModulo.interperta(lineaCambiada.getModulo(), cambioFuente, lineaCambiada.getLinea()).ifPresent(intepretacion -> intepretaCambioPorCatalogo(cambioFuente, intepretacion,cambios));
	}

	private void intepretaCambioPorCatalogo(ETipoCambioFuente cambioFuente, LineaInterpretadaSubModulo intepretacion, Map<String, EntBranchRevisionCambio> cambios){
		interpretadorCambioRevision.interpretar(intepretacion.getCambio()).ifPresent(catalogo -> guardarCambios(cambioFuente, intepretacion, catalogo,cambios));
	}

	private void guardarCambios(ETipoCambioFuente cambioFuente, LineaInterpretadaSubModulo intepretacion, EntAplicacionCatalogoCambio catalogo, Map<String, EntBranchRevisionCambio> cambios){

		String llave = catalogo.getId()+intepretacion.getSubModuloActual().getId();
		EntBranchRevisionCambio cambio = cambios.get(llave);
		if (cambio == null){
			cambio = new EntBranchRevisionCambio();
			cambio.setSubModulo(intepretacion.getSubModuloActual());
			cambio.setTipoCambio(cambioFuente);
			cambio.setCatalogo(catalogo);
			cambios.put(llave, cambio);
		}
		cambio.setCantidad(cambio.getCantidad()+1);
	}

	private String interpretaBranch(String linea){
		int idxSeparador = linea.indexOf('/');
		if (idxSeparador == -1){
			return null;
		}
		String tmp = linea.substring(idxSeparador+1);
		idxSeparador = tmp.indexOf('/');
		if (idxSeparador == -1){
			return null;
		}

		return tmp.substring(0,idxSeparador);
	}
}

class RevisionMerge implements Comparable<RevisionMerge>{

	private long origen;
	private long destino;

	RevisionMerge(long origen, long destino) {
		this.origen = origen;
		this.destino = destino;
	}

	public long getOrigen() {
		return origen;
	}

	public long getDestino() {
		return destino;
	}

	@Override
	public int compareTo(RevisionMerge o) {
		return Long.compare(origen, o.origen)+Long.compare(destino, o.destino);
	}
}