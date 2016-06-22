package models;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by spencer on 6/22/2016.
 */
public class Transaction {

    @Id
    private int transactionID;
    private List<Item> items;
    private String date;
    private String time;
    private double totalPrice;
    private String receipt;

}
