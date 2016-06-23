package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by spencer on 6/22/2016.
 */
@Entity
public class Sale extends Model {

    @Id
    private int saleID;

    private String startTime;
    private String endTime;
    private String date;
    private String seller;
    private String location;
    private String description;
    private List<Item> itemList;

    public int getSaleID() {
        return saleID;
    }
    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addItem(Item newItem) {
        itemList.add(newItem);
    }
}
