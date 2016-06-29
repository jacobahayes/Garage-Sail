package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by spencer on 6/22/2016.
 */
public class Transaction extends Model {

    @Id
    @Column(unique=true)
    private int transactionId;
    private int saleId;
    private List<Item> items;
    private String date;
    private String time;
    private double totalPrice;
    private String receipt;
    private String paymentMethod;

}
