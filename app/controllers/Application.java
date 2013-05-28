package controllers;

import java.util.Date;
import java.util.List;
import utils.MailerScheduler;
import java.text.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import models.*;
import play.*;
import play.mvc.*;
import securesocial.core.java.SecureSocial;
import securesocial.core.java.SocialUser;
import views.html.*;
import play.data.Form;
import static play.libs.Json.toJson;

import java.io.IOException;

import com.google.code.javascribd.connection.ScribdClient;
import com.google.code.javascribd.connection.ScribdConnectionException;
import com.google.code.javascribd.docs.GetList;
import com.google.code.javascribd.docs.GetListResponse;
import com.google.code.javascribd.docs.Search;
import com.google.code.javascribd.docs.SearchResponse;
import com.google.code.javascribd.type.ApiKey;
import com.google.code.javascribd.type.SearchScope;
import com.google.code.javascribd.type.SessionKey;
import com.google.code.javascribd.user.Login;
import com.google.code.javascribd.user.LoginResponse;
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
		//updateUser.userType=updateForm.get().usertype;
		updateUser.update();
  		Http.Context.current().session().put(APPMENT_USER, new Long(user.id).toString());
  		Http.Context.current().session().put(APPMENT_USER_TYPE, user.userType);
  		Http.Context.current().session().put(APPMENT_USER_EMAIL, user.email);
  		Http.Context.current().session().put(APPMENT_USER_NAME, user.name);

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
	   	List<DoctorDetails> listOfHospitals1  = new ArrayList<DoctorDetails>();
    	String hospitalName ="";
    	for(DoctorDetails d: doctorDetails){
    		if(!d.hospital.hospitalName.equals(hospitalName)){
    			hospitalName=d.hospital.hospitalName;
    			listOfHospitals1.add(d);
    		}
    	}
	  	SocialUser sUser = SecureSocial.currentUser();

    	User user = User.authenticate(sUser.id.provider,sUser.id.id);
	    return ok(takeappointment.render(SecureSocial.currentUser(),"",user,listOfHospitals1,doctorDetails));
	  
  }

  @SecureSocial.Secured  
  public static Result newsFeed(){
	  	SocialUser sUser = SecureSocial.currentUser();
	  	
    	User user = User.authenticate(sUser.id.provider,sUser.id.id);
    	List<AppointmentHistory> appointments = AppointmentHistory.getAppointmentDetailsForFeed(user.id,user.userType);
	    return ok(newsfeed.render(SecureSocial.currentUser(),user,appointments));
	  
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
	  String userId = Http.Context.current().session().get(APPMENT_USER);
	  Form<Appointment> appointmentForm = form(Appointment.class).bindFromRequest();
	  Long id = new Long(userId);
	  	SocialUser sUser = SecureSocial.currentUser();
	  	User user = User.authenticate(sUser.id.provider,sUser.id.id);
	  	if(appointmentForm.get().hospitalId >0){
	  		return ok(toJson(AppointmentHistory.getMyAppointments(id,appointmentForm.get().hospitalId)));
	  	}
	    return ok(toJson(AppointmentHistory.getMyAppointments(id,0)));
	  
  }

  @SecureSocial.Secured  
  public static Result getAppointmentDetails(Long id){
	  	SocialUser sUser = SecureSocial.currentUser();
	  	User user = User.authenticate(sUser.id.provider,sUser.id.id);
	  	AppointmentHistory appointment = AppointmentHistory.getAppointmentDetails(id.longValue());
	  	if(user.id != appointment.patient.id && user.id != appointment.doctor.id){
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

	public static class Documents {

		public String documentId;
		public String title;
		public String description;
		public String thumbNailURI;
		public String accessKey;

	} 
  public static Result knowledgeCenter() throws ScribdConnectionException, IOException{
      ScribdClient conn = new ScribdClient();
      conn.setUrl("http://api.scribd.com/api");
      ApiKey key = new ApiKey("61ek1y88milxwuxacyw4o");
      Login method = new Login(key, "", "");
      LoginResponse result = conn.execute(method);
      if (result.getError() != null) {
              System.out.println("rsp.error.code=" + result.getError().getCode());
              System.out.println("rsp.error.message=" + result.getError().getMessage());
      }
      SessionKey session = result.getSessionKey();
 /*     System.out.println("rsp.sessionKey=" + result.getSessionKey());
      System.out.println("rsp.name=" + result.getName());
      System.out.println("rsp.username=" + result.getUserName());
      System.out.println("rsp.user_id=" + result.getUserId());
*/
      GetList getList = new GetList(key, session);
      GetListResponse resultList = conn.execute(getList);
      List<Documents> documents =new ArrayList<Documents>();
      for (GetListResponse.Entry res : resultList.getResultSet().getEntries()) {
    	  	Documents d1 = new Documents();
    	  	d1.title = res.getTitle();
    	  	d1.documentId = res.getDocId().toString();
    	  	d1.description = res.getDescription();
    	  	d1.thumbNailURI =res.getThumbnailUrl().toString();
    	  	d1.accessKey= res.getAccessKey();
    	  	documents.add(d1);
      }

      return ok(toJson(documents));
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
	  return ok(toJson(toJson(AppointmentHistory.getMyAppointments(user.id,0))));
	  
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
		public long hospital;
	}

	  @SecureSocial.Secured  
	  public static Result putAppointment(){
			Form<InsertAppointment> appointmentForm = form(InsertAppointment.class).bindFromRequest();
		  	SocialUser sUser = SecureSocial.currentUser();
			User user = User.authenticate(sUser.id.provider,sUser.id.id);
		  	AppointmentHistory appointment = new AppointmentHistory();
		  	appointment.patient.id=user.id;
		  	appointment.appointmentComment = appointmentForm.get().comment;
		  	appointment.appointmentHeadline= appointmentForm.get().headline;
		  	appointment.doctor.id= appointmentForm.get().doctorId;
		  	Hospitals hospital = new Hospitals();
		  	hospital.id = appointmentForm.get().hospital;
		  	appointment.hospital= hospital;
		    try{
			  	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			  	Date d = df.parse(appointmentForm.get().dateOfAppointment); 
			  	appointment.dateOfAppointment = d;
			    DateFormat TWELVE_TF = new SimpleDateFormat("hh:mm a");
			    DateFormat TWENTY_FOUR_TF = new SimpleDateFormat("HH:mm");		    	
			    appointment.appointmentTime =TWENTY_FOUR_TF.format(TWELVE_TF.parse(appointmentForm.get().timeOfAppointment));
			    appointment.save();
			    MailerScheduler.newAppointment(appointment,user);
		    }catch(Exception ex){
		    	System.out.println(ex.getStackTrace());
		    }

		  	return ok("success");
		  
	  }

	  

}