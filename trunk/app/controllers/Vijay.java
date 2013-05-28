package controllers;

import views.html.*;
import play.mvc.*;
import play.data.Form;
import utils.MailerScheduler;

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
	  String[] emails = {"padmaraj.m@gmail.com"};
	  String body = "<b>Name:</b> "+ askForm.get().name+".<br>"+"<b>Email ID:</b> "+ askForm.get().emailid+".<br>"+"<b>Phone Number:</b> "+ askForm.get().phonenumber+
			  ".<br>"+"<b>Query:</b> " + askForm.get().query;
	  
	  MailerScheduler.scheduleMail(emails,subject,body);
	    return ok("success");
	  }
	  

}