package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.util.Duration;
import play.Play;
import play.api.templates.Html;
import play.libs.Akka;
import play.mvc.Controller;
import views.html.*;
import models.*;
public class MailerScheduler {

   	
	public static void newAppointment(
			AppointmentHistory appointment ,
			User user
            ) {
		User duser = User.getUserDetails(appointment.doctor.id);
		Hospitals hospital = Hospitals.getHospital(appointment.hospital.id);
		Html messageBody = newappointment.render(duser.name,user.name,hospital.hospitalName,appointment.appointmentHeadline,appointment.appointmentTime,appointment.appointmentComment);
		//System.out.println(messageBody);
		String[] emails = {duser.email};
		String subject = "New appointment Notification";
		scheduleMail(emails, subject,messageBody.toString());
	}

	public static void scheduleMail(String[] emails, String subject, String body){
      	 ActorRef testActor = Akka.system().actorOf(new Props(MailSender.class));
      	 MessageCarrier m = new MessageCarrier();
      	 	m.emails =emails;
      	 	m.subject=subject;
      	 	m.body = body;
      	     Akka.system().scheduler().scheduleOnce(
      	          Duration.create(1, TimeUnit.SECONDS),
      	          testActor, 
      	          m
      	      );

	}
	
}