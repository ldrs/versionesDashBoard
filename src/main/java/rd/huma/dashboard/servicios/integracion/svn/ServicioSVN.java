package rd.huma.dashboard.servicios.integracion.svn;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.ClientBuilder;

import rd.huma.dashboard.model.transaccional.EntAplicacion;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.integracion.svn.util.SVNOrigenBranch;
import rd.huma.dashboard.servicios.integracion.svn.util.ServicioSvnBuscaOrigenBranch;
import rd.huma.dashboard.servicios.transaccional.ServicioConfiguracionGeneral;
import rd.huma.dashboard.util.IOUtil;

public class ServicioSVN {

	public static final String SEPERADOR_REVISION = "------------------------------------------------------------------------";

	private EntConfiguracionGeneral configuracionGeneral;

	private EntAplicacion aplicacion;

	void configurar(EntConfiguracionGeneral configuracionGeneral, EntAplicacion aplicacion){
		this.configuracionGeneral = configuracionGeneral;
		this.aplicacion = aplicacion;
	}

	public String toBranchCompleto(String branch){
		return rootPath().append("/branches/").append(branch).toString();
	}

	public StringBuilder toBranches(String branch){
		return new StringBuilder(150).append("/branches/").append(branch);
	}


	public String buscaComentario(String path, String svnRevsion){
		try {
			Process proceso = Runtime.getRuntime().exec(new StringBuilder(150).append("svn log -r").append(svnRevsion).append(' ').append(rootPath()).append(path).toString());
			proceso.waitFor(3, TimeUnit.SECONDS);

			return IOUtil.toString(proceso.getInputStream());
		} catch (IOException | InterruptedException e) {
			throw new IllegalStateException("No pudo ser encontrado el log.");
		}
	}

	public String buscaInicioPath(String path){
		 return ejecutarSvn("svn log --stop-on-copy --verbose ", rootPath().append(path));
	}

	public SVNOrigenBranch buscaOrigenBranchPorRevision(String branch, long revision){
		return new ServicioSvnBuscaOrigenBranch(branch,aplicacion).getOrigen(buscaBranchPorRevision(branch,revision));
	}

	private String  buscaComentarioSVNDesdeInicioBranch(String url){
		try {
			Process proceso = Runtime.getRuntime().exec("svn log --stop-on-copy --verbose "+ url);
			proceso.waitFor(10, TimeUnit.SECONDS);

			return IOUtil.toString(proceso.getInputStream());
		} catch (IOException | InterruptedException e) {
			throw new IllegalStateException("No pudo ser encontrado el log.");
		}
	}

	public String buscaBranchPorRevision(String branch, long revision){
		 return ejecutarSvn("svn log --stop-on-copy --verbose --use-merge-history ",  toBranches(branch).append('@').append(revision));
	}

	public SVNOrigenBranch getOrigen(String branch){
		return new ServicioSvnBuscaOrigenBranch( branch,aplicacion).getOrigen(buscaComentarioSVNDesdeInicioBranch(toBranchCompleto(branch)));
	}

	private String ejecutarSvn(CharSequence commando, CharSequence ruta){
		   try {
				Process proceso = Runtime.getRuntime().exec(new StringBuilder(150).append(commando).append(rootPath()).append(ruta).toString());
				proceso.waitFor(3, TimeUnit.SECONDS);

				return IOUtil.toString(proceso.getInputStream());
			} catch (IOException | InterruptedException e) {
				throw new IllegalStateException("No pudo ser encontrado el log.");
			}
	}

	public Set<String> tags(){
		Set<String> tags = new HashSet<>();
		String url = rootPath().append("/tags/").toString();
		String html = ClientBuilder.newClient().target(url).request().get(String.class);
		html = html.substring(html.indexOf("<li>")).replace("</ul>", "").replace("</body></html>", "").replace("<li>", "").replace("</li>", "").replace("</a>", "") .trim();
		for (String link : html.split("<a href=")){
			int index = link.indexOf('>');
			if (index!=-1){
				link = link.substring(index+1).trim();
				link = link.substring(0,link.length()-1);
				if (!".".equals(link) && link.length()>0){
					tags.add(link);
				}
			}
		}
		return tags;
	}

	private StringBuilder rootPath(){
		return new StringBuilder(150).append(configuracionGeneral.getRutaSvn()).append(aplicacion.getSvnPath());
	}

	public static ServicioSVN para(EntAplicacion aplicacion){
		ServicioSVN servicioSVN = new ServicioSVN();
		servicioSVN.configurar(ServicioConfiguracionGeneral.getCacheConfiguracionGeneral().get(), aplicacion);
		return servicioSVN;
	}

	public String buscaRevisiones(String branch, long revisionUltima) {
		 return ejecutarSvn("svn log --stop-on-copy --verbose --use-merge-history ",  toBranches(branch).append('@').append(revisionUltima));
	}

	public EntAplicacion getAplicacion() {
		return aplicacion;
	}

}