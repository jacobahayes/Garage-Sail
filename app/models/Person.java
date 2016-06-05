package models;

/**
 * Created by JacobHayes on 6/2/16.
 */

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person extends Model{
    @Id
    public String id;

    public String name;
}
