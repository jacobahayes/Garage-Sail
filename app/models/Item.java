package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.image.BufferedImage;

/**
 * Created by spencer on 6/22/2016.
 */
@Entity
public class Item extends Model{

    public Item() {
        sold = false;
    }

    @Id
    @Column(unique=true)
    private int id;

    private String name;
    private String description;
    private int saleId;

    private int quantity;
    private double listPrice;
    private double bottomPrice;
    private double sellPrice;
    private double ebayPrice;

    private BufferedImage img;

    private int transactionId;
    private boolean sold;

    public boolean isSold() {
        return sold;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getListPrice() {
        return listPrice;
    }
    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getBottomPrice() {
        return bottomPrice;
    }
    public void setBottomPrice(double bottomPrice) {
        this.bottomPrice  = bottomPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getEbayPrice() {
        return ebayPrice;
    }
    public void setEbayPrice(int ebayPrice) {
        this.ebayPrice = ebayPrice;
    }

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

    public BufferedImage getImg() {
        return img;
    }
}
