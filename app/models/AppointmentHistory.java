package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.validation.NotNull;


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
    
    @Column(name="appointment_confirmation")
    public String appointmentConfirmation;

	@Column(name="appointment_comment")
    public String appointmentComment;

    @OneToOne
    @JoinColumn(name="idHospital")
    public Hospitals hospital;
	
    @Column(name="created")
    public String created=null;

    @Transient
    public String appointmentEndTime=null;

    
    // -- Queries

    public static Model.Finder<String,AppointmentHistory> find = new Model.Finder(String.class, AppointmentHistory.class);

    /**
     * Get Appointments for the Doctor.
     */
    public static List<AppointmentHistory> getAppointments(long doctorId,long hospitalId) {
    	if(hospitalId > 0){
	    	 return find
	     	.where()
	         .eq("idDoctor", doctorId)
	         .eq("idHospital", hospitalId)
	         .findList();
    	} 
        return find
            	.where()
                .eq("idDoctor", doctorId)
                .findList();

    }


    /**
     * Get Appointments for the Patient.
     */
    public static List<AppointmentHistory> getMyAppointments(long myId,long hospitalId) {
    	if(hospitalId >0){
            return find.where()
                    .eq("idDoctor", myId)
                     .eq("idHospital", hospitalId)
                    .findList();

    	}
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

    public static List<AppointmentHistory> getAppointmentDetailsForFeed (long id,String userType) {
    	List<AppointmentHistory> appointments = new ArrayList<AppointmentHistory>();
    	if("D".equals(userType)){
    		appointments =find.where().eq("idDoctor", id).orderBy("date_of_appointment desc").findList();;
    	}else{
    		appointments =find.where().eq("idPatient", id).orderBy("date_of_appointment desc").findList();;
    	}
         return appointments;
    }

    
}

