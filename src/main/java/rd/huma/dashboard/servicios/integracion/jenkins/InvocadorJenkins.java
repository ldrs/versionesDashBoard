package rd.huma.dashboard.servicios.integracion.jenkins;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;
import java.util.function.Predicate;

import rd.huma.dashboard.model.jenkins.Parameters;
import rd.huma.dashboard.model.transaccional.EntConfiguracionGeneral;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.seguimiento.EjecutorJenkinsSeguimiento;
import rd.huma.dashboard.servicios.background.ejecutores.jenkins.seguimiento.ResultadoSeguimientoJenkins;

public class InvocadorJenkins {

	private String credenciales;
	private StringBuilder sbPostData = new StringBuilder(150);
	private String url;
	private String urlBase;
	private Consumer<ResultadoInvocadorJenkins> jenkinsResponseHandler;
	private Predicate<Parameters> filtroEncontrar;

	public InvocadorJenkins(EntConfiguracionGeneral configuracionGeneral) {
		this.credenciales = new JenkinsCredenciales(configuracionGeneral).getCredenciales();
		this.jenkinsResponseHandler = new Consumer<ResultadoInvocadorJenkins>() {

			@Override
			public void accept(ResultadoInvocadorJenkins t) {
			}
		};
	}

	public InvocadorJenkins setURL(String url){
		this.url = url;
		return this;
	}

	public InvocadorJenkins adicionarParametro(String llave, String valor){
		if (sbPostData.length()!=0){
			sbPostData.append('&');
		}
		sbPostData.append(llave).append('=').append(valor);
		return this;
	}

	public InvocadorJenkins setFiltroEncontrar(Predicate<Parameters> filtroEncontrar) {
		this.filtroEncontrar = filtroEncontrar;
		return this;
	}

	public InvocadorJenkins responseHanlder(Consumer<ResultadoInvocadorJenkins> handler){
		this.jenkinsResponseHandler = handler;
		return this;
	}

	public void invocar(){
		byte[] postData = sbPostData.toString().getBytes();

		HttpURLConnection con;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", credenciales);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length", Integer.toString( postData.length ));
			con.setInstanceFollowRedirects( false );
			con.setUseCaches(false);
			con.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())){

				wr.write(postData);
				wr.flush();
			}
			manejaResponse(con.getResponseCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void manejaResponse( int responseCode){
		if (responseCode==201){
			new EjecutorJenkinsSeguimiento(urlBase, filtroEncontrar, this::procesaResultadoJenkins);
		}else{
			jenkinsResponseHandler.accept(new ResultadoInvocadorJenkins(EEstadoJobJenkins.FALLIDO_REQUEST_INVALIDO));
		}
	}

	private void procesaResultadoJenkins(ResultadoSeguimientoJenkins resultado){
		jenkinsResponseHandler.accept(new ResultadoInvocadorJenkins(resultado));
	}
}