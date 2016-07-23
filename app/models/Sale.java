package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by spencer on 6/22/2016.
 */
@Entity
public class Sale extends Model {

    @Id
    @Column(unique = true)
    private int id;

    private String name;
    private String startTime;
    private String endTime;
    private String date;
    private int saleAdminId;
    private String location;
    private String description;
    private String status;
    private List<Item> itemList;

    /**
     * getter for name variable
     * @return the name
     */
    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * setter for name variable

     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for sale admin id variable
     * @return the sale admin id
     */
    public int getSaleAdminId() {
        return saleAdminId;
    }

    /**
     * setter for sale admin id variable
     * @param saleAdminId the sale admin id to set
     */
    public void setSaleAdminId(int saleAdminId) {
        this.saleAdminId = saleAdminId;
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
     * getter for start time variable
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * setter for start time variable
     * @param startTime the start time to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * getter for end time variable
     * @return the end time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * setter for end time variable
     * @param endTime the end time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * getter for date variable
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * setter for date variable
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * getter for location variable
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter for location variable
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * getter for description variable
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for description variable
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * add an item to the item list
     * @param newItem the item to add
     */
    public void addItem(Item newItem) {
        itemList.add(newItem);
    }
}
