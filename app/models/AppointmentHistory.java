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

    @Formats.DateTime(pattern="MM/dd/yyyy")
	@Column(name="date_of_appointment")
    public Date dateOfAppointment;

    @Constraints.Required
    @Column(name="time_of_appointment")
    public String appointmentTime;

    @Column(name="appointment_duration")
    public String appointmentDuration ="00:30";

    @Column(name="appointment_headline")
    public String appointmentHeadline;

	@Column(name="appointment_comment")
    public String appointmentComment;

    
    @Column(name="created")
    public String created=null;

    @Transient
    public String appointmentEndTime=null;

    
    // -- Queries

    public static Model.Finder<String,AppointmentHistory> find = new Model.Finder(String.class, AppointmentHistory.class);

    /**
     * Get Appointments for the Doctor.
     */
    public static List<AppointmentHistory> getAppointments(long doctorId) {
        return find.where()
            .eq("idDoctor", doctorId)
            .findList();
    }


    /**
     * Get Appointments for the Patient.
     */
    public static List<AppointmentHistory> getMyAppointments(long myId) {
        return find.where()
            .eq("idPatient", myId)
            .findList();
    }

    /**
     * Get Appointments for the Patient.
     */
    public static AppointmentHistory getAppointmentDetails (long id) {
        return find.where()
            .eq("idAppointment_History", id)
            .findUnique();
    }

    
}

