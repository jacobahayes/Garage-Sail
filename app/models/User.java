package models;

/**
 * Created by JacobHayes on 6/2/16.
 */

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User extends Model {

    /**
     * constructor for creating a default User
     * with no admin or bookkeeper rights
     */
    public User() {
        admin = "false";
        bookkeeper = false;
    }

    @Id
    @Column(unique = true)
    private int id;

    @Column(unique = true)
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

    /**
     * setter for date variable
     * @param date the date to set
     */
    public void setDate(String date) {
        dateCreated = date;
    }

    /**
     * getter for date variable
     * @return the date
     */
    public String getDate() {
        return dateCreated;
    }

    /**
     * getter for login attempts variable
     * @return the login attempts
     */
    public int getLoginAtempts() {
        return loginAtempts;
    }

    /**
     * resets login attempts to zero
     */
    public void resetLoginAtempts() {
        loginAtempts = 0;
    }

    /**
     * adds a login attempt
     */
    public void addLoginAtempt() {
        loginAtempts = loginAtempts + 1;
    }

    /**
     * getter for admin variable
     * @return the admin rights
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * setter for admin variable
     * @param admin the admin rights to set
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    /**
     * getter for locked variable
     * @return locked or not
     */
    public boolean getLocked() {
        return locked;
    }

    /**
     * setter for locked variable
     * @param locked set whether user is locked or not
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * getter for bookkeeper variable
     * @return bookkeepr or not
     */
    public boolean isBookkeeper() {
        return bookkeeper;
    }

    /**
     * setter for bookkeeper variable
     * @param bookkeeper the bookkeeper rights to set
     */
    public void setBookkeeper(boolean bookkeeper) {
        this.bookkeeper = bookkeeper;
    }

    /**
     * getter for id variable
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for id variable
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for username variable
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter for username variable
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter for password variable
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for password variable
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter for email variable
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for email variable
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for first name variable
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter for first name variable
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter for last name variable
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter for last name variable
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter for sales variable
     * @return the list of sales
     */
    public List<Sale> getSales() {
        return sales;
    }

    /**
     * adds a new sale to the sale list
     * @param newSale the new sale to add
     */
    public void newSale(Sale newSale) {
        sales.add(newSale);
    }

    /**
     * used for finding a User in the default local db
     * @return a finder mapping an integer to the User
     */
    public static Finder<Integer, User> find() {
        return new Finder<Integer, User>(Integer.class, User.class);
    }
}
