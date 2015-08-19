package rd.huma.dashboard.servicios.background.ejecutores.alertas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ServicioEmail {
	static final Logger LOGGER = Logger.getLogger(ServicioEmail.class.getSimpleName());

	public Properties propiedadesCorreo(){
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "172.16.1.27");
		properties.put("mail.smtp.auth",  Boolean.TRUE.toString());
		properties.put("mail.transport.protocol","smtp");
		return properties;
	}


    public void enviar(String correos, String subjecto, String mensaje, List<String> archivos) {

        try {

        	final Session session = Session.getInstance(propiedadesCorreo(), null);

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correos));

            message.setFrom(new InternetAddress("dashboard.version@sigef.gov.do"));
            message.setSubject(subjecto);
            message.setText(mensaje);
            if (!archivos.isEmpty()){
            	MimeMultipart multipart = new MimeMultipart();
            	for (String ruta : archivos){
            		multipart.addBodyPart(new MimeBodyPart(Files.newInputStream(Paths.get(ruta))));
            	}

            	message.setContent(multipart);
            }


            Transport.send(message);

        } catch (MessagingException | IOException e) {
        	LOGGER.warning("No se pudo mandar el correo");
        }
    }



}
