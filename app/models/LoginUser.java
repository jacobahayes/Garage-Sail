package models;

/**
 * Created by JacobHayes on 6/2/16.
 */

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoginUser extends Model{

    public String username;

    public String password;
}
