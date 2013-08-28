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
@Table(name="gcm_users")
public class GCMUser extends Model {

	public static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	public long id;

	@Column(name="gcm_regid")
    public String regId;
    // -- Queries

    public static Model.Finder<Long,GCMUser> find = new Model.Finder(Long.class, GCMUser.class);

/*    public static GCMUser getGCNUser(String regId) {    
    	GCMUser gcmuser =findwhere().eq("gcm_regid",regId).findRowCount();
    	return listOfHospitals;
    			
    }
*/    
}

