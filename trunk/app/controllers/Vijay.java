package controllers;

import views.html.*;
import play.mvc.*;
import play.data.Form;
import utils.MailerScheduler;
import com.opentok.api.API_Config;
import com.opentok.api.OpenTokSDK;
import com.opentok.exception.OpenTokException;
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
 /*     OpenTokSDK sdk = new OpenTokSDK(23104692, "2d8528251e19b0743d9dc27db12bc13703725658");

      String sessionId=null, token = null;
      try {
    	   sessionId = sdk.create_session().getSessionId();
    	   token = sdk.generate_token(sessionId);
      }catch(Exception ex){
	    	System.out.println(ex.getStackTrace());
	    }

      
      System.out.println(sessionId);
      System.out.println(token);
*/	    return ok("success");
	  }
	  
	public static class DonateForm {

		public String emailid;
		public String phonenumber;
		public String name;
		public String amount;

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
    

}