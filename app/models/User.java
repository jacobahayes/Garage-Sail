package models;

/**
 * Created by JacobHayes on 6/2/16.
 */

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity
public class User extends Model{

    public User() {
        admin = "false";
    }

    @Id
    @Column(unique=true)
    private int id;

    @Column(unique=true)
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private int loginAtempts;

    private String admin;
    private boolean locked;

    private boolean bookkeeper;

    private List<Sale> sales;

    private String dateCreated;

    public void setDate(String date) {
        dateCreated = date;
    }

    public String getDate() { return dateCreated; }

    public int getLoginAtempts(){ return loginAtempts; }
    public void resetLoginAtempts() { loginAtempts = 0; }
    public void addLoginAtempt() { loginAtempts = loginAtempts + 1; }

    public String getAdmin() {
        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public boolean getLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }

    public boolean isBookkeeper() {
        return bookkeeper;
    }
    public void setBookkeeper(boolean bookkeeper) {
        this.bookkeeper = bookkeeper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Sale> getSales() { return sales; }

    public void newSale(Sale newSale) {
        sales.add(newSale);
    }


    public static Finder<Integer, User> find() {
        return new Finder<Integer, User>(Integer.class, User.class);
    }
}
