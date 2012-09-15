package controllers;

import play.*;
import play.mvc.*;
import securesocial.core.java.SecureSocial;
import securesocial.core.java.SocialUser;
import views.html.*;

public class Application extends Controller {
  
  public static Result index() {
    return ok(index.render(SecureSocial.currentUser(),""));
  }
  
  @SecureSocial.Secured
  public static Result dashboard() {
	    return ok(dashboard.render(SecureSocial.currentUser(),""));
	  }

  @SecureSocial.Secured  
  public static Result takeAppointment(){
	    return ok(takeappointment.render(SecureSocial.currentUser(),""));
	  
  }
  @SecureSocial.Secured
  public static Result login() {
      SocialUser user = (SocialUser) ctx().args.get(SecureSocial.USER_KEY);      
      return ok(login.render(user));
  }
  
}