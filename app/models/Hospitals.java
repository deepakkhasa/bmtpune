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
@Table(name="hospitals")
public class Hospitals extends Model {

	public static final long serialVersionUID = 1L;

	@Id
	@Column(name="idhospitals")
	public long id;

    @Column(name="hospital_name")
    public String hospitalName;

    @Column(name="hospital_address1")
    public String hospitalAddress1;

    @Column(name="hospital_address2")
    public String hospitalAddress2;

    @Column(name="hospital_city")
    public String hospitalCity;

    @Column(name="hospital_state")
    public String hospitalState;

    @Column(name="hospitals_zip")
    public String hospitalZip;

    @Column(name="hospital_contact_no")
    public String hospitalContactNo;

    // -- Queries

    public static Model.Finder<String,Hospitals> find = new Model.Finder(String.class, Hospitals.class);

    public static List<Hospitals> getAllHospitals() {    
    	return find.all();
    }
    
    public static Hospitals getHospital(long hospitalId) {    
        return find.where()
                .eq("id", hospitalId)
                .findUnique();
    }

}

