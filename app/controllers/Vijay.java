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
import java.io.IOException;
import java.util.Comparator;
import java.util.Collections;

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
import java.util.ArrayList;
import java.util.List;


public class Vijay extends Controller {



  public static Result welcome() {
    return ok(welcome.render());
  }

  public static Result contactUs() {
	    return ok(contactus.render());
	  }

  public static Result aboutUs() {
	    return ok(aboutus.render());
	  }
  
  public static Result labs() {
	    return ok(labs.render());
	  }

  public static Result clinicalStudies() {
	    return ok(clinicalstudies.render());
	  }

  public static Result international() {
	    return ok(international.render());
	  }
  
  public static Result videos() {
      return ok(videos.render());
  }
  public static Result testimonials() {
      return ok(testimonials.render());
  }

  public static Result documents() throws ScribdConnectionException, IOException{
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
      GetList getList = new GetList(key, session);
      conn.execute(getList);
      GetListResponse resultList = conn.execute(getList);
      List<Documents> documents =new ArrayList<Documents>();
      SimpleDateFormat IN_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
      SimpleDateFormat OUT_DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa");
      for (GetListResponse.Entry res : resultList.getResultSet().getEntries()) {
    	  	Documents d1 = new Documents();
    	  	d1.title = res.getTitle();
    	  	d1.documentId = res.getDocId().toString();
    	  	d1.docId = res.getDocId().intValue();
    	  	d1.description = res.getDescription();
    	  	d1.thumbNailURI =res.getThumbnailUrl().toString();
    	  	d1.accessKey= res.getAccessKey();
    	  	try{
    	  		d1.whenUploaded =OUT_DATE_FORMAT.format(IN_DATE_FORMAT.parse(res.getWhenUploaded()));
        	  	d1.whenUpdated = OUT_DATE_FORMAT.format(IN_DATE_FORMAT.parse(res.getWhenUpdated()));
    	  	}catch(ParseException e){
    	  		System.out.println("error");
    	  	}
    	  	
    	  	d1.reads = res.getReads();
    	  	d1.pageCount = res.getPageCount();
    	  	documents.add(d1);
      }
      
      Collections.sort(documents, new Comparator<Documents>(){
          SimpleDateFormat IN_DATE_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa");
    	  public int compare(Documents s1, Documents s2) {
    		  try{
      		    if (IN_DATE_FORMAT.parse(s1.whenUpdated).after(IN_DATE_FORMAT.parse(s2.whenUpdated))) {
    		        return -1;
    		      } else if (IN_DATE_FORMAT.parse(s1.whenUpdated).before(IN_DATE_FORMAT.parse(s2.whenUpdated))) {
    		        return 1;
    		      }  
    			  
    		  }catch(ParseException e){
      	  		System.out.println("error1");
      	  	}
    		      return 0;
    	  }
    	});


      return ok(knowledgecenter.render(documents));
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
		  return ok(toJson("success"));
		  }

  public static Result donateForm() {
	  Form<DonateForm> askForm = form(DonateForm.class).bindFromRequest();
	  String subject = askForm.get().name+ " would like to donate.";
	  String[] emails = {"bmtpune@gmail.com"};
	  String body = "<b>Name:</b> "+ askForm.get().name+".<br>"+"<b>Email ID:</b> "+ askForm.get().emailid+".<br>"+"<b>Phone Number:</b> "+ askForm.get().phonenumber+
			  ".<br>"+"<b>Amount:</b> " + askForm.get().amount;
	  
	  MailerScheduler.scheduleMail(emails,subject,body);
	  return ok(toJson("success"));
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