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
@Table(name="vijay_international_contact")
public class InternationalContact extends Model {

	public static final long serialVersionUID = 1L;

	@Id
	@Column(name="idvijay_international_contact")
	public long id;

    @Column(name="email")
    public String email;

    @Column(name="name")
    public String name;

    @Column(name="date_of_birth")
    public Date dateOfBirth;

    @Column(name="country")
    public String country;

    @Column(name="phone_number")
    public String phoneNumber;

    @Column(name="video_conf")
    public String videoConference;

    @Column(name="session_id")
    public String sessionId;

    @Column(name="token_id")
    public String tokenId;

    @Column(name="session_done")
    public String sessionDone;

    @Column(name="other_details")
    public String otherDetails;
    
    @Column(name="vijay_id")
    public String vijayId;
    
    @Column(name="session_date")
    public Date sessionDate;

    
    // -- Queries

    public static Model.Finder<String,InternationalContact> find = new Model.Finder(String.class, InternationalContact.class);

    public static InternationalContact getSession(String id) {
        return find.where()
            .eq("vijay_id", id)
            .findUnique();
    }

}

