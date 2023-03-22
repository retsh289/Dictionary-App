package service;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.*;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

 
public class MailSending {
	//mail config
	public static final String HOST_NAME = "smtp.gmail.com";
	
	public static final int SSL_PORT = 465; // Port for SSL
	
	public static final int TSL_PORT = 587; // Port for TLS/STARTTLS
	
	public static final String APP_EMAIL = "hungn12323@gmail.com"; // your email
	
	public static final String APP_PASSWORD = "gnrnahlwsoctbhcw"; // your password
	
//	public static final String RECEIVE_EMAIL = "hungn12333@gmail.com";
	
	
	public static void  sendMail(String RECEIVE_EMAIL,String subject,String htmlContent) {
		// 1) get the session object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host",   HOST_NAME);
        props.put("mail.smtp.socketFactory.port",   SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port",   TSL_PORT);
 
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(  APP_EMAIL,   APP_PASSWORD);
            }
        });
 
        // 2) compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(  APP_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(  RECEIVE_EMAIL));
 
            // 3) create HTML content
            message.setSubject(subject);
                   
            message.setContent(htmlContent, "text/html");
             
            // 4) send message
            Transport.send(message);
 
            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		MailSending.sendMail("hungn12333@gmail.com","xx","awsa");
	}

	
}
