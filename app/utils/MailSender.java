package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import play.Play;
import play.api.templates.Html;
import akka.actor.UntypedActor;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailSender  extends UntypedActor{

	public static void emailSender(String[] toEmail,
            String subject,
            String body) throws EmailException{

		// Load SMTP configuration
	    String smtpHost = Play.application().configuration().getString( "mail.smtp.host" );
	    String smtpUser = Play.application().configuration().getString( "mail.smtp.user" );
	    String smtpPassword = Play.application().configuration().getString( "mail.smtp.password" );
	    String smtpPort = Play.application().configuration().getString( "mail.smtp.port" );
	    String smtpDebug = Play.application().configuration().getString( "mail.smtp.debug" );
	    boolean bDebug = new Boolean(smtpDebug);
	    String smtpTLS = Play.application().configuration().getString( "mail.smtp.TLS" );
	    boolean bTLS = new Boolean(smtpTLS);
	    int intPort = new Integer(smtpPort);
	    HtmlEmail email = new HtmlEmail();
	    email.setHostName(smtpHost);
	    email.setFrom(smtpUser);
	    email.setSubject(subject);
	 // embed the image and get the content id
	    URL headerURL=null;
	    URL footerURL=null;
		String myURL=null;
		myURL = Play.application().configuration().getString( "my.email.url" );

		try {
			headerURL = new URL(myURL +"/assets/images/logo.jpg");
			footerURL = new URL(myURL +"/assets/images/footer.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	    String headercid = email.embed(headerURL, "header");
	    String footercid = email.embed(footerURL, "footer");
		String html=body.replaceAll("\\$\\{" + 0 + "\\}", "cid:" + headercid )
				.replaceAll("\\$\\{" + 1 + "\\}", "cid:" + footercid);
	    email.setDebug(bDebug);
	    email.setAuthenticator(new DefaultAuthenticator(smtpUser, smtpPassword));
	    email.setSmtpPort(intPort);
	    email.setTLS(bTLS);
	    
	    for(int i =0;i < toEmail.length;i++){
	    email.addTo(toEmail[i]);	
	    }
	   email.setHtmlMsg(html);
	   email.send();
	
	}
	

	@Override
	public void onReceive(Object message) throws Exception {
		
		if (message instanceof MessageCarrier){
			emailSender(((MessageCarrier) message).emails,((MessageCarrier) message).subject,((MessageCarrier) message).body);
		 }else
		      System.out.println("Error: Actor sent wrong message!!!");
		  
	}

}
