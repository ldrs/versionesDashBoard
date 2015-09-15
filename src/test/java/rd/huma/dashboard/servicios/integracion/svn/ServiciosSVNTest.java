package rd.huma.dashboard.servicios.integracion.svn;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;

import rd.huma.dashboard.model.jira.Issues;
import rd.huma.dashboard.model.sysaid.Ticket;
import rd.huma.dashboard.model.transaccional.Artefacto;
import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.model.transaccional.EntVersion;
import rd.huma.dashboard.model.transaccional.EntVersionModulo;
import rd.huma.dashboard.servicios.background.ejecutores.version.BuscadorModulos;
import rd.huma.dashboard.servicios.integracion.jira.BuscadorJiraRestApi;
import rd.huma.dashboard.servicios.integracion.jira.ETipoQueryJira;
import rd.huma.dashboard.servicios.integracion.jira.JiraQuery;
import rd.huma.dashboard.servicios.integracion.nexus.ServicioNexus;
import rd.huma.dashboard.servicios.integracion.nexus.SimulaNexus;
import rd.huma.dashboard.servicios.integracion.sysaid.ServicioIntegracionSYSAID;

public class ServiciosSVNTest {

	@Test @Ignore
	public void versionesSinTag(){
		ServicioSVN svn = new ServicioSVN();
		EntAplicacion aplicacion = new EntAplicacion();
		aplicacion.setSvnPath("sigef");
		svn.configurar(new EntConfiguracionGeneral(), aplicacion);

		Set<String> tags = svn.tags(); //Versiones Validas

		ServicioNexus servicioNexus = SimulaNexus.nuevo();
		Artefacto artifact = new Artefacto();
		artifact.setArtefacto("sigef_actual");
		artifact.setGrupo("dr.gov.sigef");
		List<String> versiones = servicioNexus.getVersions(artifact);
		for (String version : versiones) {
			if (!tags.contains(version)){
				System.out.println(version);
			}
		}
	}

	@Test @Ignore
	public void versionesYaEnProduccion() throws InterruptedException{


		EntConfiguracionGeneral configuracionGeneral = new EntConfiguracionGeneral();
		ServicioSVN svn = new ServicioSVN();
		EntAplicacion aplicacion = new EntAplicacion();
		aplicacion.setSvnPath("sigef");
		svn.configurar(configuracionGeneral, aplicacion);

		List<EntVersionModulo> modulos = new BuscadorModulos(configuracionGeneral, aplicacion, new EntVersion()).buscar("/sigef/trunk/sigef/");
		Set<ArtefactoParaBorrar> modulosBorrarbles = new HashSet<>();
		for (EntVersionModulo m : modulos) {
			modulosBorrarbles.add(new ArtefactoParaBorrar(m.getArtefacto().getGrupo(), m.getArtefacto().getArtefacto()));
		}

		Set<String> tags = svn.tags(); //Versiones Validas

		ServicioNexus servicioNexus = SimulaNexus.nuevo();
		Artefacto artifact = new Artefacto();
		artifact.setArtefacto("sigef_actual");
		artifact.setGrupo("dr.gov.sigef");
		List<String> versiones = servicioNexus.getVersions(artifact);
		Set<BranchTag> branchesOrigenVersionesNexus = new TreeSet<>();
		for (String version : versiones) {
			if (tags.contains(version)){

				String log = svn.buscaInicioPath("/tags/"+version);
				int index = log.indexOf("from");
				if (index!=-1){
					log = log.substring(index+6);
					if (log.startsWith("branches")){
						log =  log.substring(9);
						log = log.substring(0, log.indexOf(':'));
						branchesOrigenVersionesNexus.add(new BranchTag(version, log) );
					}
				}

			}
		}
		ServicioIntegracionSYSAID s = ServicioIntegracionSYSAID.instancia();
		ExecutorService pool = Executors.newFixedThreadPool(50);
		for (BranchTag branchesOrigen : branchesOrigenVersionesNexus) {
			List<Issues> issues = new BuscadorJiraRestApi(new JiraQuery(configuracionGeneral, ETipoQueryJira.BRANCH, branchesOrigen.getBranch())).getIssues();
			Optional<Issues> encontrado = issues.stream().findFirst();
			if (encontrado.isPresent()){
				Issues issue = encontrado.get();
				if (issue.getFields().getSysAidTicket()!=null){
					Optional<Ticket> ticketFound = s.getTicket(configuracionGeneral, Long.valueOf(issue.getFields().getSysAidTicket()));
					if (ticketFound.isPresent()){
						Ticket ticket = ticketFound.get();
						if (ticket.getEstado()==21){
							System.out.println(branchesOrigen.getTag() + " -> " + branchesOrigen.getBranch() + " " + ticket.getTicket());

							for(ArtefactoParaBorrar artefactoParaBorrar : modulosBorrarbles){
								pool.submit(new Callable<Void>() {

									@Override
									public Void call() throws Exception {
										System.out.println(  branchesOrigen.getTag()+ " -> "+ artefactoParaBorrar.getGrupo() + "."+ artefactoParaBorrar.getArtefacto()  +" ->" + servicioNexus.eliminarModulo(artefactoParaBorrar.getGrupo(),artefactoParaBorrar.getArtefacto(), branchesOrigen.getTag()));
										return null;
									}
								});
							}
						}
					}
				}
			}
		}
		pool.awaitTermination(5, TimeUnit.MINUTES);
	}
}

class ArtefactoParaBorrar{
	private String grupo;
	private String artefacto;

	ArtefactoParaBorrar(String grupo, String artefacto) {
		this.grupo = grupo;
		this.artefacto = artefacto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artefacto == null) ? 0 : artefacto.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
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
		if (!(obj instanceof ArtefactoParaBorrar)) {
			return false;
		}
		ArtefactoParaBorrar other = (ArtefactoParaBorrar) obj;
		if (artefacto == null) {
			if (other.artefacto != null) {
				return false;
			}
		} else if (!artefacto.equals(other.artefacto)) {
			return false;
		}
		if (grupo == null) {
			if (other.grupo != null) {
				return false;
			}
		} else if (!grupo.equals(other.grupo)) {
			return false;
		}
		return true;
	}

	public String getGrupo() {
		return grupo;
	}
	public String getArtefacto() {
		return artefacto;
	}

}

class BranchTag implements Comparable<BranchTag>{
	private String tag;
	private String branch;


	BranchTag(String tag, String branch) {
		this.tag = tag;
		this.branch = branch;
	}

	public String getTag() {
		return tag;
	}

	public String getBranch() {
		return branch;
	}

	@Override
	public int compareTo(BranchTag o) {
		return branch.compareTo(o.branch);
	}

}
