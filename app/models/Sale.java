package models;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by spencer on 6/22/2016.
 */
public class Sale {

    @Id
    private int saleID;

    private String time;
    private String date;
    private String seller;
    private List<Item> itemList;

}
