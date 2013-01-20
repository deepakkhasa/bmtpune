package models;


import java.text.SimpleDateFormat;
import java.util.*;
import models.*;
import javax.persistence.*;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.validation.NotNull;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


/**
 * User entity managed by Ebean
 */
@Entity
@Table(name="doctor_details")
public class DoctorDetails extends Model {

	public static final long serialVersionUID = 1L;

	@Id
	@Column(name="iddoctor_details")
	public long id;

	@Column(name="doctor_id")
    public long doctorId;


    @NotNull
    @OneToOne
    @JoinColumn(name="idhospitals")
    public Hospitals hospital;

    @Column(name="day")
    public int day;
    

    @Column(name="start_time")
    public String startTime;

    @Column(name="end_time")
    public String endTime;
    // -- Queries

    public static Model.Finder<Long,DoctorDetails> find = new Model.Finder(Long.class, DoctorDetails.class);

    public static List<DoctorDetails> getHospitalsForDoctor(long doctorId) {    
    	return find.fetch("hospital").where().eq("doctor_id",doctorId).findList();
    			
    }
}

