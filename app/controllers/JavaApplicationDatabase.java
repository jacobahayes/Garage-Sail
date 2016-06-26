package controllers;

import javax.inject.Inject;

import models.Item;
import models.Sale;
import models.User;
import play.mvc.*;
import play.db.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class JavaApplicationDatabase extends Controller {

    private static DB db;

    @Inject
    public JavaApplicationDatabase(DB db) {
        this.db = db;
    }
    
    static Connection conn = db.getConnection();

    /**
     * db access to attempt login
     * @param username the username to attempt
     * @param password the password to attempt
     * @return the User that was found in the database, null if not found
     */
    public static User attemptLogin(String username, String password) {

        ResultSet rs = null;
        Statement stmt = null;
        User returnUser = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM user");
        buf.append(" WHERE username='" + username + "'");
        buf.append(" AND password='" + password + "'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {

            while (rs.next()) {

                returnUser= new User();
                returnUser.setFirstName(rs.getString("first_name"));
                returnUser.setLastName(rs.getString("last_name"));
                returnUser.setUsername(rs.getString("username"));
                returnUser.setEmail(rs.getString("email"));
                returnUser.setId(rs.getInt("id"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return returnUser;


    }

    /**
     * db access to update name
     * @param user the User to update
     * @param newFirstName the new first name
     * @param newLastName the new last name
     * @return the number of rows affected by the update
     */
    public static int updateName(User user, String newFirstName, String newLastName) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE user");
        buf.append(" SET first_name='" + newFirstName + "'");
        buf.append(" AND last_name='" + newLastName + "'");
        buf.append(" WHERE id=" + user.getId());

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return result;

    }


    /**
     * db access to edit username
     * @param user the User to update
     * @param newUsername the new username
     * @return the number of rows affected by the update
     */
    public static int updateUsername(User user, String newUsername) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE user");
        buf.append(" SET username='" + newUsername + "'");
        buf.append(" WHERE id=" + user.getId());

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return result;

    }

    /**
     * db access to edit password
     * @param user the User to update
     * @param newPassword the new password
     * @return the number of rows affected by the update
     */
    public static int updatePassword(User user, String newPassword) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE user");
        buf.append(" SET password='" + newPassword + "'");
        buf.append(" WHERE id=" + user.getId());

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     * db access to edit email
     * @param user the User to update
     * @param newEmail the new email
     * @return the number of rows affected by the update
     */
    public static int updateEmail(User user, String newEmail) {

        int result = 0;
        Statement stmt = null;
        User returnUser = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE user");
        buf.append(" SET email='" + newEmail + "'");
        buf.append(" WHERE id=" + user.getId());

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static int updateItem(Item item, Item newItem) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE item");
        buf.append(" SET name='" + newItem.getName() + "'");
        buf.append(", description='" + newItem.getDescription() + "'");
        buf.append(", quantity='" + newItem.getQuantity() + "'");
        buf.append(", list_price='" + newItem.getListPrice() + "'");
        buf.append(", bottom_price='" + newItem.getBottomPrice() + "'");
        buf.append(" WHERE id=" + item.getId());

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static List<Item> searchItemInSale(Sale sale, String name) {

        ResultSet rs = null;
        Statement stmt = null;
        List<Item> returnList = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM item");
        buf.append(" WHERE sale_id='" + sale.getId() + "'");
        buf.append(" AND name LIKE '%" + name + "%'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {

            while (rs.next()) {

                Item returnItem = new Item();
                returnItem.setName(rs.getString("name"));
                returnItem.setListPrice(rs.getDouble("list_price"));
                returnItem.setBottomPrice(rs.getDouble("bottom_price"));
                returnItem.setDescription(rs.getString("description"));
                returnItem.setQuantity(rs.getInt("quantity"));
                returnItem.setId(rs.getInt("id"));

                returnList.add(returnItem);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return returnList;
    }

    //Attempted sql query, but not used yet
    public static List<Sale> getMySales(int saleAdminId) {
        
        ResultSet rs = null;
        Statement stmt = null;
        List<Sale> returnList = new ArrayList<>();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM sale");
        buf.append(" WHERE sale_admin_id='" + saleAdminId + "'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs!=null && rs.next()) {

                Sale returnSale = new Sale();


                returnSale.setEndTime(rs.getString("start_time"));
                returnSale.setEndTime(rs.getString("end_time"));
                returnSale.setDate(rs.getString("date"));
                returnSale.setSaleAdminId(rs.getInt("sale_admin_id"));
                returnSale.setLocation(rs.getString("location"));
                returnSale.setDescription(rs.getString("description"));
                returnSale.setName(rs.getString("name"));
                returnSale.setId(rs.getInt("id"));

                //System.out.println(rs.getString("seller"));
                //System.out.println(rs.getString("seller"));
                //System.out.println(rs.getString("seller"));
                if (returnSale != null) {
                    returnList.add(returnSale);
                }
                //returnList.add(returnSale);


            }
            //System.out.println(returnList);
            //System.out.println(">>>");
            return returnList;

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the sales from the resultset");
        }
        return returnList;
    }

    public static List<Item> getSaleItems(int saleID) {

        ResultSet rs = null;
        Statement stmt = null;
        List<Item> returnList = new ArrayList<>();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM item");
        buf.append(" WHERE sale_id='" + saleID + "'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs!=null && rs.next()) {

                Item returnItem = new Item();


                returnItem.setName(rs.getString("name"));
                returnItem.setDescription(rs.getString("description"));
                returnItem.setSaleId(rs.getInt("sale_id"));
                returnItem.setQuantity(rs.getInt("quantity"));
                returnItem.setListPrice(rs.getDouble("list_price"));
                returnItem.setBottomPrice(rs.getDouble("bottom_price"));
                returnItem.setId(rs.getInt("id"));

                //System.out.println(rs.getString("seller"));
                //System.out.println(rs.getString("seller"));
                //System.out.println(rs.getString("seller"));
                if (returnItem != null) {
                    returnList.add(returnItem);
                }
                //returnList.add(returnSale);


            }
            //System.out.println(returnList);
            //System.out.println(">>>");
            return returnList;

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the sales from the result set");
        }
        return returnList;
    }

    public static Sale getSale(int saleId) {

        ResultSet rs = null;
        Statement stmt = null;
        Sale sale = new Sale();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM sale");
        buf.append(" WHERE id='" + saleId + "'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {

                sale.setName(rs.getString("name"));
                sale.setDescription(rs.getString("description"));
                sale.setId(rs.getInt("id"));
                sale.setLocation(rs.getString("location"));
                sale.setDate(rs.getString("date"));
                sale.setStartTime(rs.getString("start_time"));
                sale.setEndTime(rs.getString("end_time"));
                sale.setSaleAdminId(rs.getInt("sale_admin_id"));


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the sale");
        }

        return sale;
    }

    public static Item getItem(int itemId) {

        ResultSet rs = null;
        Statement stmt = null;
        Item item = new Item();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM item");
        buf.append(" WHERE id='" + itemId + "'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {

                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setSaleId(rs.getInt("sale_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setListPrice(rs.getDouble("list_price"));
                item.setBottomPrice(rs.getDouble("bottom_price"));


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the item");
        }

        return item;
    }
}
