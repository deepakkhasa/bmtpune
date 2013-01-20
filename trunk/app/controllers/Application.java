package controllers;

import java.util.Date;
import java.util.List;

import java.text.*;

import models.*;
import play.*;
import play.mvc.*;
import securesocial.core.java.SecureSocial;
import securesocial.core.java.SocialUser;
import views.html.*;
import play.data.Form;
import static play.libs.Json.toJson;

public class Application extends Controller {

	 public static final String APPMENT_USER = "appment.user";
	 public static final String APPMENT_USER_TYPE = "appment.usertype";
	 public static final String APPMENT_USER_EMAIL = "appment.useremail";
	 public static final String APPMENT_USER_NAME = "appment.username";

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
		public long hospitalId;

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
	  		return ok(dashboard.render(sUser,askUser,user,null));	  		
	  	}else{
	  		
	  		
	  		Http.Context.current().session().put(APPMENT_USER, new Long(user.id).toString());
	  		Http.Context.current().session().put(APPMENT_USER_TYPE, user.userType);
	  		Http.Context.current().session().put(APPMENT_USER_EMAIL, user.email);
	  		Http.Context.current().session().put(APPMENT_USER_NAME, user.name);
	  		 List<User> doctor= User.getDoctors();
	  		List<DoctorDetails> doctorDetails = DoctorDetails.getHospitalsForDoctor(doctor.get(0).id);
	  		if(null == user.email || null == user.phoneNumber || null == user.userType){
		  		return ok(dashboard.render(sUser,"N",user,doctorDetails));
	  		}else{
		  		return ok(dashboard.render(sUser,"",user,doctorDetails));
	  		}
	  	}
	  }

  @SecureSocial.Secured  
  public static Result takeAppointment(){
	 // System.out.println("user is :"+ Http.Context.current().session().get(APPMENT_USER));
	  List<User> doctor= User.getDoctors();
	 // String user = Http.Context.current().session().get(APPMENT_USER);
	  List<DoctorDetails> doctorDetails = DoctorDetails.getHospitalsForDoctor(doctor.get(0).id);
	    return ok(takeappointment.render(SecureSocial.currentUser(),"",doctorDetails));
	  
  }


  @SecureSocial.Secured  
  public static Result getDoctors(){
	    return ok(toJson(User.getDoctors()));
	  
  }

  @SecureSocial.Secured  
  public static Result getDoctorAppointments(){
		Form<Appointment> appointmentForm = form(Appointment.class).bindFromRequest();
	    return ok(toJson(AppointmentHistory.getAppointments(appointmentForm.get().doctorId,appointmentForm.get().hospitalId)));
	  
  }

  @SecureSocial.Secured  
  public static Result getMyAppointments(){
	  	SocialUser sUser = SecureSocial.currentUser();
	  	User user = User.authenticate(sUser.id.provider,sUser.id.id);
	    return ok(toJson(AppointmentHistory.getMyAppointments(user.id)));
	  
  }

  @SecureSocial.Secured  
  public static Result getAppointmentDetails(Long id){
	  	SocialUser sUser = SecureSocial.currentUser();
	  	User user = User.authenticate(sUser.id.provider,sUser.id.id);
	  	AppointmentHistory appointment = AppointmentHistory.getAppointmentDetails(id.longValue());
	  	System.out.println("Date : "+ appointment.dateOfAppointment + " "+ appointment.dateOfAppointment.toString());
	  	if(user.id != appointment.patientId && user.id != appointment.doctorId){
	  		appointment = new AppointmentHistory();
	  	}
	    return ok(toJson(appointment));
	  
  }

  
  @SecureSocial.Secured
  public static Result login() {
      SocialUser user = (SocialUser) ctx().args.get(SecureSocial.USER_KEY);      
      return ok(login.render(user));
  }
 

  public static Result contactus() {
      return ok(contactus.render(SecureSocial.currentUser()));
  }


  public static Result aboutus() {
      return ok(aboutus.render(SecureSocial.currentUser()));
  }

  
  public static Result knowledgeCenter() {
      return ok(knowledgecenter.render(SecureSocial.currentUser()));
  }

  public static Result videos() {
      return ok(videos.render(SecureSocial.currentUser()));
  }

	public static class UserDetails {

		public String userId;
		public String provider;
	}

//@SecureSocial.Secured
  public static Result androidDashboard(){
	//  System.out.println("user is :"+ SecureSocial.currentUser());
	  Form<UserDetails> userDetails = form(UserDetails.class).bindFromRequest();
	  User user = User.authenticate(userDetails.get().provider,userDetails.get().userId);	  
	  return ok(toJson(toJson(AppointmentHistory.getMyAppointments(user.id))));
	  
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
		  	appointment.patientId=user.id;
		  	appointment.appointmentComment = appointmentForm.get().comment;
		  	appointment.appointmentHeadline= appointmentForm.get().headline;
		  	appointment.doctorId= appointmentForm.get().doctorId;
		    try{
			  	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			  	Date d = df.parse(appointmentForm.get().dateOfAppointment); 
			  	appointment.dateOfAppointment = d;
			    DateFormat TWELVE_TF = new SimpleDateFormat("hh:mm a");
			    DateFormat TWENTY_FOUR_TF = new SimpleDateFormat("HH:mm");		    	
			    appointment.appointmentTime =TWENTY_FOUR_TF.format(TWELVE_TF.parse(appointmentForm.get().timeOfAppointment));
			    appointment.save();
		    }catch(ParseException ex){
		    	System.out.println(ex.getStackTrace());
		    }

		  	return ok("success");
		  
	  }


}