package rd.huma.dashboard.servicios.background.ejecutores.alertas;

import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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
		properties.put("mail.transport.protocol","smtp");
		properties.put("mail.smtp.auth", Boolean.FALSE.toString());
		return properties;
	}


    public boolean enviar(String correos, String subjecto, String mensaje, List<String> archivos) {
    	if (correos.isEmpty()){
    		LOGGER.warning("No se pudo mandar el correo " + subjecto + " por que no tiene a quien enviarle");
    		return false;
    	}

        try {

        	final Session session = Session.getDefaultInstance(propiedadesCorreo(), null);

            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correos));

            message.setFrom(new InternetAddress("dashboard.version@sigef.gov.do"));
            message.setSubject(subjecto);

            if (archivos.isEmpty()){
            	message.setContent(mensaje, "text/html");
            }else{
            	LOGGER.info(String.format("Se estan mandando %s archivos del correo %s", String.valueOf(archivos.size()), subjecto) );

            	MimeMultipart multipart = new MimeMultipart();

                BodyPart messageBodyPart = new MimeBodyPart();

                messageBodyPart.setText(mensaje);

                multipart.addBodyPart(messageBodyPart);

            	for (String ruta : archivos){
            		MimeBodyPart part = new MimeBodyPart();
            		DataSource source = new FileDataSource(Paths.get(ruta).toFile());
            		part.setDataHandler(new DataHandler(source));
            		part.setFileName(ruta.substring(ruta.lastIndexOf('/')+1));

            		multipart.addBodyPart(part);

            	}

            	message.setContent(multipart);
            }


            Transport.send(message);
            return true;
        } catch (MessagingException e) {
        	e.printStackTrace();
        	LOGGER.warning("No se pudo mandar el correo de subjecto" + subjecto + " por: "+ e.getMessage());
        }
        return false;
    }



}
