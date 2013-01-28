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
@Table(name="user")
public class User extends Model {

	public static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	public long id;

    @Column(name="email_address")
    public String email;

    @Constraints.Required
    @Column(name="name")
    public String name;

    @Column(name="provider")
    public String provider;

    @Column(name="userid")
    public String userid;

    @Column(name="phone_no")
    public String phoneNumber;

    @Column(name="user_type")
    public String userType= "P";

    @Column(name="created")
    public String created=null;

    // -- Queries

    public static Model.Finder<String,User> find = new Model.Finder(String.class, User.class);

    /**
     * Authenticate a User.
     */
    public static User authenticate(String provider, String userId) {
        return find.where()
            .eq("provider", provider)
            .eq("userid", userId)
            .findUnique();
    }


    /**
     * Get Doctors.
     */
    public static List<User> getDoctors() {
        return find.where()
            .eq("user_type", "D")
            .findList();
    }

}

