package controllers;

import views.html.*;
import java.text.*;
import java.util.Date;
import play.mvc.*;
import java.util.UUID;
import java.util.HashMap;
import play.data.Form;
import utils.MailerScheduler;
import com.opentok.api.API_Config;
import com.opentok.api.OpenTokSDK;
import com.opentok.exception.OpenTokException;
import com.opentok.api.constants.SessionProperties;
import models.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import static play.libs.Json.toJson;

public class Vijay extends Controller {



  public static Result welcome() {
    return ok(welcome.render());
  }
	
	public static class AskForm {

		public String emailid;
		public String phonenumber;
		public String name;
		public String query;

	}
  public static Result askForm() {
	  Form<AskForm> askForm = form(AskForm.class).bindFromRequest();
	  String subject = "Query from "+ askForm.get().name;
	  String[] emails = {"bmtpune@gmail.com"};
	  String body = "<b>Name:</b> "+ askForm.get().name+".<br>"+"<b>Email ID:</b> "+ askForm.get().emailid+".<br>"+"<b>Phone Number:</b> "+ askForm.get().phonenumber+
			  ".<br>"+"<b>Query:</b> " + askForm.get().query;
	  
	  MailerScheduler.scheduleMail(emails,subject,body);
 	    return ok("success");
	  }
	  
	public static class DonateForm {

		public String emailid;
		public String phonenumber;
		public String name;
		public String amount;

	}
	
	
	public static Result updateRegistrationId(String registrationId) {
			GCMUser gcmUser = new GCMUser();
			gcmUser.regId = registrationId;
			gcmUser.save();
		  return ok("success");
		  }

  public static Result donateForm() {
	  Form<DonateForm> askForm = form(DonateForm.class).bindFromRequest();
	  String subject = askForm.get().name+ " would like to donate.";
	  String[] emails = {"bmtpune@gmail.com"};
	  String body = "<b>Name:</b> "+ askForm.get().name+".<br>"+"<b>Email ID:</b> "+ askForm.get().emailid+".<br>"+"<b>Phone Number:</b> "+ askForm.get().phonenumber+
			  ".<br>"+"<b>Amount:</b> " + askForm.get().amount;
	  
	  MailerScheduler.scheduleMail(emails,subject,body);
	  return ok("success");
	  }
  
	public static class ContactForm {

		public String emailid;
		public String phonenumber;
		public String name;
		public String video;
		public String country;
		public String dateOfBirth;
		public String otherDetails;

	}
  public static Result requestContact(){
	  Form<ContactForm> contactForm = form(ContactForm.class).bindFromRequest();
	  InternationalContact contactDetails = new InternationalContact();
	  contactDetails.email=contactForm.get().emailid;
	  contactDetails.name=contactForm.get().name;
	  try
	  {
		  SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		  Date d = df.parse(contactForm.get().dateOfBirth); 
		  contactDetails.dateOfBirth=d;
	  }catch(Exception ex){
	    	System.out.println(ex.getStackTrace());
	    }
	  contactDetails.country=contactForm.get().country;
	  contactDetails.phoneNumber=contactForm.get().phonenumber;
	  contactDetails.videoConference=contactForm.get().video;
	  contactDetails.otherDetails=contactForm.get().otherDetails;
	  String uuid = UUID.randomUUID().toString();
	  contactDetails.vijayId=uuid;
	  contactDetails.save();	
			  
	  String subject = "International Patient: "+contactForm.get().name+ " would like to get in touch.";
	  String[] emails = {"bmtpune@gmail.com"};
	  StringBuilder body = new StringBuilder();
	  body.append("<b>Name:</b> "+ contactForm.get().name+".<br>"+"<b>Email ID:</b> "+ contactForm.get().emailid+".<br>"+"<b>Phone Number:</b> "+ contactForm.get().phonenumber);
	  if("Y".equals(contactForm.get().video)){
		  String url ="http://www.vijayramanan.com/#setsession?"+"session="+uuid+"";
		  body.append(".<br>"+"He has requested for video conferencing. Please use this link to generate: " + url);
	  }
	  body.append(".<br>"+"<b>Date of Birth:</b> " + contactForm.get().dateOfBirth+ ".<br>"+"<b>Country:</b> " + contactForm.get().country+ ".<br>"+"<b>Other Details:</b> " + contactForm.get().otherDetails+"<br> <br>Thanks,<br>Appment");
	  
	  MailerScheduler.scheduleMail(emails,subject,body.toString());
	  return ok();
  }
  
  public static Result createSession(String datetime, String id) {
      String sessionId=null, token = null;
      InternationalContact contactDetails = new InternationalContact();
	  try
	  {
		  SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		  Date d = df.parse(datetime); 
		  contactDetails.sessionDate=d;
		  OpenTokSDK sdk = new OpenTokSDK(23104692, "2d8528251e19b0743d9dc27db12bc13703725658");
	        SessionProperties sp = new SessionProperties();
	        sp.p2p_preference = "enabled";
	        sessionId = sdk.create_session(null, sp).getSessionId();
	        contactDetails.sessionId=sessionId;
	        InternationalContact econtactDetails = InternationalContact.getSession(id);
	        contactDetails.id=econtactDetails.id;
	        contactDetails.update();
	        String url = datetime;
	        String encodedUrl = URLEncoder.encode(url, "UTF-8");
	        String subject="Video session with Dr. Vijay Ramanan.";
	        
	  	  String[] emails = {econtactDetails.email,"bmtpune@gmail.com"};
		  String body = "Hi,<br> I have scheduled a video conference on "+datetime+
		  ". You can use this link to attend:<br>http://www.vijayramanan.com/#opensession?session="+id+"  <br>You can login using this on the scheduled date.<br>Thanks,<br>Dr. Vijay Ramanan";
	        subject="Start session with "+econtactDetails.name;
	        MailerScheduler.scheduleMail(emails,subject,body);
	    }catch(Exception ex){
	    	ex.printStackTrace();
	    }

	  return ok();
  }

  public static Result openSession(String session) {
	  InternationalContact econtactDetails = InternationalContact.getSession(session);
	  OpenTokSDK sdk = new OpenTokSDK(23104692, "2d8528251e19b0743d9dc27db12bc13703725658");
	  String  token = econtactDetails.tokenId;
	  Boolean publisher=false;
      try {
    	  if(null == token || "".equals(token)){
    	   	  token = sdk.generate_token(econtactDetails.sessionId);
        	  InternationalContact contactDetails = new InternationalContact();
        	  contactDetails.id=econtactDetails.id;
        	  contactDetails.tokenId=token;
    	       contactDetails.update();
    	       publisher=true;
    	  }
 
    	   
      }catch(Exception ex){
    	  ex.printStackTrace();
	    	//System.out.println(ex.getStackTrace());
	  }

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("sessionid", econtactDetails.sessionId);
		result.put("token", token);
		result.put("publisher", publisher);
	  return ok(toJson(result));
  }
  
  public static Result generateToken(String sessionid) {
	  OpenTokSDK sdk = new OpenTokSDK(23104692, "2d8528251e19b0743d9dc27db12bc13703725658");
	  	
      String  token = null;
      try {
    	   token = sdk.generate_token(sessionid);
      }catch(Exception ex){
	    	System.out.println(ex.getStackTrace());
	    }

      
      System.out.println(token);
	  return ok(token);
  }
}