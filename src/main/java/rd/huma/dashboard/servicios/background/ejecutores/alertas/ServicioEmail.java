package rd.huma.dashboard.servicios.background.ejecutores.alertas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import rd.huma.dashboard.servicios.transaccional.Servicio;

@Stateless
@Servicio
public class ServicioEmail {
	static final Logger LOGGER = Logger.getLogger(ServicioEmail.class.getSimpleName());

	@Resource(name = "java:jboss/mail/Default")
	private Session session;

    public void enviar(String correos, String subjecto, String mensaje, List<String> archivos) {

        try {

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


	public static ServicioEmail getInstanciaTransaccional(){
		Servicio servicio = ServicioEmail.class.getAnnotation(Servicio.class);
		return CDI.current().select(ServicioEmail.class, servicio).get();
	}
}
