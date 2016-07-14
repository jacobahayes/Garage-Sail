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
public class Item extends Model {

    /**
     * contructor
     * automatically sets sold to be false wehen item created
     */
    public Item() {
        sold = false;
    }

    @Id
    @Column(unique = true)
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


    /**
     * getter for is sold variable
     * @return is sold
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * setter for sold variable
     * @param sold the sold to set
     */
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    /**
     * getter for transaction id variable
     * @return the image
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * setter for transaction id variable
     * @param transactionId the transaction id to set
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * getter for name variable
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name variable
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * getter for quantity variable
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * setter for quantity variable
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * getter for list price variable
     * @return the list price
     */
    public double getListPrice() {
        return listPrice;
    }

    /**
     * setter for list price variable
     * @param listPrice the price to set
     */
    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    /**
     * getter for bottom price variable
     * @return the bottom price
     */
    public double getBottomPrice() {
        return bottomPrice;
    }

    /**
     * setter for bottom price variable
     * @param bottomPrice the bottom price to set
     */
    public void setBottomPrice(double bottomPrice) {
        this.bottomPrice  = bottomPrice;
    }

    /**
     * getter for sell price variable
     * @return the sell price
     */
    public double getSellPrice() {
        return sellPrice;
    }

    /**
     * setter for sell price variable
     * @param sellPrice the sell price to set
     */
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    /**
     * getter for ebay price variable
     * @return the ebay price
     */
    public double getEbayPrice() {
        return ebayPrice;
    }

    /**
     * setter for ebay price variable
     * @param ebayPrice the ebay price to set
     */
    public void setEbayPrice(int ebayPrice) {
        this.ebayPrice = ebayPrice;
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
     * getter for image variable
     * @return the image
     */
    public BufferedImage getImg() {
        return img;
    }
}
