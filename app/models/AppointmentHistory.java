package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


/**
 * User entity managed by Ebean
 */
@Entity
@Table(name="appointment_history")
public class AppointmentHistory extends Model {

	public static final long serialVersionUID = 2L;

	@Id
	@Column(name="idAppointment_History")
	public long id;

	@Column(name="idPatient")
	public long patientId;

	@Column(name="idDoctor")
	public long doctorId;

	@Column(name="date_of_appointment")
    public Date dateOfAppointment;

    @Constraints.Required
    @Column(name="time_of_appointment")
    public String appointmentTime;

	@Column(name="appointment_headline")
    public String appointmentHeadline;

	@Column(name="appointment_comment")
    public String appointmentComment;

    
    @Column(name="created")
    public String created=null;

    // -- Queries

    public static Model.Finder<String,AppointmentHistory> find = new Model.Finder(String.class, AppointmentHistory.class);

    /**
     * Authenticate a User.
     */
    public static List<AppointmentHistory> getAppointments(long doctorId) {
    	System.out.println("here...");
        return find.where()
            .eq("idDoctor", doctorId)
            .findList();
    }


}

