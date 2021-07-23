package miniproject;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {
	
	public boolean loginmail(String email)
	{
	 final String to =email;
	    final String subject = "bank application";
	    final String messg ="you just login to our bankapplication";

	    final String from = "tejasjagdale60@gmail.com";
	    final String pass = "yrggtgtccxgsiynd";

	    
	    String host = "smtp.gmail.com";
	    Properties props = new Properties();

	    props.put("mail.smtp.host", host);
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.user", from);
	    props.put("mail.password", pass);
	    props.put("mail.port", "587");
	    
	    Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(from, pass);
	        }
	    });
	    try {
	        
	        MimeMessage message = new MimeMessage(mailSession);
	        message.setFrom(new InternetAddress(from));
	        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	        message.setSubject(subject);
	        message.setText(messg);
	        Transport.send(message);
	       System.out.print("Your mail sent successfully....");
	       return true;
	    } catch (MessagingException mex) {
	        mex.printStackTrace();
	        System.out.print("Error: unable to send mail....");
	        return false;
	    }
	}
	
	public boolean otpmail(String email)
  {
	
	final String to =email;
    final String subject = "bank application";
    final String messg ="thank you for geting registered in aur online banking system  please click on the given link to activate your account http://localhost:8080/miniproject/acverification.jsp";

    final String from = "tejasjagdale60@gmail.com";
    final String pass = "yrggtgtccxgsiynd";

    
    String host = "smtp.gmail.com";
    Properties props = new Properties();

    props.put("mail.smtp.host", host);
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.user", from);
    props.put("mail.password", pass);
    props.put("mail.port", "587");
    
    Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, pass);
        }
    });
    try {
        
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        message.setSubject(subject);
        message.setText(messg);
        Transport.send(message);
       System.out.println("Your mail sent successfully....");
       return true;
    } catch (MessagingException mex) {
        mex.printStackTrace();
        System.out.println("Error: unable to send mail....");
        return false;
    }
  }

}
