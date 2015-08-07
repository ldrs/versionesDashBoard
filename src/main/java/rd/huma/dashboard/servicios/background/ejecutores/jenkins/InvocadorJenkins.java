package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;

import rd.huma.dashboard.model.transaccional.EntJobDespliegueVersion;
import rd.huma.dashboard.servicios.transaccional.ServicioJobDespliegueVersion;


public class InvocadorJenkins {

	private String credenciales;
	private StringBuilder sbPostData = new StringBuilder(150);
	private String url;
	private String urlBase;
	private ResponseHandler responseHanlder;

	public InvocadorJenkins(String credenciales) {
		this.credenciales = credenciales;
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

	public InvocadorJenkins responseHanlder(ResponseHandler handler){
		this.responseHanlder = handler;
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
			this.responseHanlder.sucess().setUrlSeguimiento(urlBase);
				this.responseHanlder.sucess().ejecutar();
		}else{
			this.responseHanlder.getFailure().accept(null);
		}
	}
}

class ResponseHandler{

	private SeguimientoJob sucess;
	private Consumer<Void> failure;
	public ResponseHandler(SeguimientoJob sucess, Consumer<Void> failure) {
		this.sucess = sucess;
		this.failure = failure;
	}

	public SeguimientoJob sucess() {
		return sucess;
	}

	public Consumer<Void> getFailure() {
		return failure;
	}
}

class SeguimientoJob{
	private EntJobDespliegueVersion valor;
	private String urlSeguimiento;
	private Consumer<Boolean> handlerResult;

	public void ejecutar(){
		ServicioJobDespliegueVersion.getInstanciaTransaccional().seguimientoJenkinsSeguimientoDespliegue(valor, urlSeguimiento,handlerResult);
	}

	public void setUrlSeguimiento(String urlSeguimiento) {
		this.urlSeguimiento = urlSeguimiento;
	}

	public void setHandlerResult(Consumer<Boolean> handlerResult) {
		this.handlerResult = handlerResult;
	}
}