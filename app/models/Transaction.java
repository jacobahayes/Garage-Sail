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
public class Transaction extends Model {

    @Id
    @Column(unique=true)
    private int id;

    private int saleId;
    private int userId;

    private String items;
    private String paymentmethod;

    private String date;
    private String time;
    private String startDate;
    private double totalPrice;
    private String receipt;
    private boolean closed;

    public boolean getClosed() { return closed; }
    public void setClosed(boolean closed) { this.closed = closed; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getPaymentMethod() {
        return paymentmethod;
    }

    public void setPaymentMethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
}
