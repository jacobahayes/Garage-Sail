package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * Created by spencer on 6/22/2016.
 */
@Entity
public class Transaction extends Model {

    @Id
    @Column(unique = true)
    private int id;

    private int saleId;
    private int userId;

    private ArrayList<String> items;
    private String paymentmethod;

    private String date;
    private String time;
    private String startDate;
    private double totalPrice;
    private String receipt;
    private boolean closed;

    /**
     * getter for closed variable
     * @return closed
     */
    public boolean getClosed() {
        return closed;
    }

    /**
     * setter for closed variable
     * @param closed the closed to set
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
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
     * getter for sale id variable
     * @return the sale id
     */
    public int getSaleId() {
        return saleId;
    }

    /**
     * setter for sale id variable
     * @param saleId the sale id to set
     */
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    /**
     * getter for user id variable
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setter for user id variable
     * @param userId the user id to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * getter for items variable
     * @return the items
     */
    public ArrayList<String> getItems() {
        return items;
    }

    /**
     * setter for items variable
     * @param items the items to set
     */
    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    /**
     * add an item to the transaction
     * @param itemId the items to set
     */
    public void addItem(String itemId) {
        items.add(itemId);
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
     * getter for start date variable
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * setter for start date variable
     * @param startDate start date to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * getter for time variable
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * setter for time variable
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * getter for total price variable
     * @return the total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * setter for total price variable
     * @param totalPrice the total price to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * getter for receipt variable
     * @return the receipt
     */
    public String getReceipt() {
        return receipt;
    }

    /**
     * setter for receipt variable
     * @param receipt the receipt to set
     */
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    /**
     * getter for payment method variable
     * @return the payment method
     */
    public String getPaymentMethod() {
        return paymentmethod;
    }

    /**
     * setter for payment method variable
     * @param paymentmethod the payment method to set
     */
    public void setPaymentMethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
