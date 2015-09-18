package rd.huma.dashboard.servicios.integracion.mail;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.junit.Ignore;
import org.junit.Test;

public class ServicioMailLectorTest {


	@Test @Ignore
	public void probar() throws MessagingException{
		Properties props = new  Properties();
		props.setProperty("mail.store.protocol", "imaps");


		Session session = Session.getInstance(props, null);


		Store store = session.getStore();
		store.connect("imap.sigef.gov.do", "dashboard.version@sigef.gov.do", "Sigef0101");
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		if (inbox.getMessageCount()>0){

			Message msg = inbox.getMessage(1);
		}
	}


}
