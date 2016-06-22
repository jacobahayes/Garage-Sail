package models;

import com.avaje.ebean.Model;

/**
 * Created by spencer on 6/22/2016.
 */

public class Item extends Model{

    private String tag;
    private String description;

    private int quantity;
    private double listPrice;
    private double bottomPrice;
    private double sellPrice;
    private double ebayPrice;

    //photo?

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return description;
    }
    public void setDesc(String description) {
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
    public void setBottomPrice(int bottomPrice) {
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

}
