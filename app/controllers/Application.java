package controllers;

import java.util.Date;

import java.text.*;

import models.AppointmentHistory;
import models.User;
import play.*;
import play.mvc.*;
import securesocial.core.java.SecureSocial;
import securesocial.core.java.SocialUser;
import views.html.*;
import play.data.Form;
import static play.libs.Json.toJson;

public class Application extends Controller {
  
  public static Result index() {
    return ok(index.render(SecureSocial.currentUser(),""));
  }
	
  	/**
	 * Form class for update details.
	 */

	public static class PersonalDetails {

		public String emailid;
		public String phonenumber;
		public String usertype;

	}

	public static class Appointment {

		public long doctorId;

	}

	@SecureSocial.Secured
	public static Result updateDetails() {

		Form<PersonalDetails> updateForm = form(PersonalDetails.class).bindFromRequest();
	  	SocialUser sUser = SecureSocial.currentUser();
		User user = User.authenticate(sUser.id.provider,sUser.id.id);
		user.email=updateForm.get().emailid;
		User updateUser = new User();
		updateUser.id= user.id;
		updateUser.email=updateForm.get().emailid;
		updateUser.phoneNumber=updateForm.get().phonenumber;
		updateUser.userType=updateForm.get().usertype;
		updateUser.update();
	    return ok("success");
	}

  @SecureSocial.Secured
  public static Result dashboard() {
	  	SocialUser sUser = SecureSocial.currentUser();
	  	
	  	User user = User.authenticate(sUser.id.provider,sUser.id.id);
	  	if(null == user){
	  		user = new User();
	  		user.userid = sUser.id.id;
	  		user.provider=sUser.id.provider;
		  	String askUser ="";
		  	if(null != sUser.email){
		  		user.email =sUser.email;
		  	}
		  	askUser ="N";
		  	user.name = sUser.displayName;		  	
		  	user.save();
	  		return ok(dashboard.render(sUser,askUser,user));	  		
	  	}else{
	  		if(null == user.email || null == user.phoneNumber || null == user.userType){
		  		return ok(dashboard.render(sUser,"N",user));
	  		}else{
		  		return ok(dashboard.render(sUser,"",user));
	  		}
	  	}
	  }

  @SecureSocial.Secured  
  public static Result takeAppointment(){
	    return ok(takeappointment.render(SecureSocial.currentUser(),""));
	  
  }


  @SecureSocial.Secured  
  public static Result getDoctors(){
	    return ok(toJson(User.getDoctors()));
	  
  }

  @SecureSocial.Secured  
  public static Result getDoctorAppointments(){
		Form<Appointment> appointmentForm = form(Appointment.class).bindFromRequest();
	    return ok(toJson(AppointmentHistory.getAppointments(appointmentForm.get().doctorId)));
	  
  }

  @SecureSocial.Secured
  public static Result login() {
      SocialUser user = (SocialUser) ctx().args.get(SecureSocial.USER_KEY);      
      return ok(login.render(user));
  }
 
	/**
	 * Form class for inserting Appointment.
	 */

	public static class InsertAppointment {

		public long doctorId;
		public String headline;
		public String comment;
		public String dateOfAppointment;
		public String timeOfAppointment;
	}

	  @SecureSocial.Secured  
	  public static Result putAppointment(){
			Form<InsertAppointment> appointmentForm = form(InsertAppointment.class).bindFromRequest();
		  	SocialUser sUser = SecureSocial.currentUser();
			User user = User.authenticate(sUser.id.provider,sUser.id.id);
		  	AppointmentHistory appointment = new AppointmentHistory();
		  	appointment.appointmentComment = appointmentForm.get().comment;
		  	appointment.appointmentHeadline= appointmentForm.get().headline;
		  	appointment.doctorId= appointmentForm.get().doctorId;
		    try{
			  	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			  	Date d = df.parse(appointmentForm.get().dateOfAppointment); 
			  	appointment.dateOfAppointment = d;
			    DateFormat TWELVE_TF = new SimpleDateFormat("hh:mm a");
			    DateFormat TWENTY_FOUR_TF = new SimpleDateFormat("HH:mm");
		    	TWENTY_FOUR_TF.format(TWELVE_TF.parse(appointmentForm.get().timeOfAppointment));
			    System.out.println(d.toString()+ " "+ TWENTY_FOUR_TF.format(TWELVE_TF.parse(appointmentForm.get().timeOfAppointment))+ " " + appointment.doctorId);

		    }catch(ParseException ex){
		    	System.out.println(ex.getStackTrace());
		    }

		  	return ok("success");
		  
	  }


}