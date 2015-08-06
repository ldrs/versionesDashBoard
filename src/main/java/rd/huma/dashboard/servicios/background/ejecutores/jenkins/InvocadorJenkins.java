package rd.huma.dashboard.servicios.background.ejecutores.jenkins;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;


public class InvocadorJenkins {

	private String credenciales;
	private StringBuilder sbPostData = new StringBuilder(150);
	private String url;
	private Consumer<?> consumerSucess;
	private Consumer<?> consumerFailure;

	public InvocadorJenkins(String credenciales) {
		this.credenciales = credenciales;
	}

	private InvocadorJenkins setURL(String url){
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


	public  <T> InvocadorJenkins computeOnSucess(Consumer<T> consumer){
		this.consumerSucess = consumer;
		return this;
	}

	public  <T> InvocadorJenkins computeOnFailure(Consumer<T> consumer){
		this.consumerFailure = consumer;
		return this;
	}

	public void invocar(){
		byte[] postData = sbPostData.toString().getBytes();

		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", credenciales);
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length", Integer.toString( postData.length ));
		con.setInstanceFollowRedirects( false );
		con.setUseCaches(false);
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(postData);
		wr.flush();
		wr.close();
	}

}