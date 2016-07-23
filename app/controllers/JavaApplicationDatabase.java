package controllers;

import javax.inject.Inject;

import models.Item;
import models.Sale;
import models.Transaction;
import models.User;
import play.mvc.Controller;
import play.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class JavaApplicationDatabase extends Controller {

    private static DB db;

    /**
     * db class constructor
     * @param db the database to connect to
     */
    @Inject
    public JavaApplicationDatabase(DB db) {
        this.db = db;
    }

    private static Connection conn = db.getConnection();


    //---------------------Profile logic----------------------------

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

                returnUser = new User();
                returnUser.setFirstName(rs.getString("first_name"));
                returnUser.setLastName(rs.getString("last_name"));
                returnUser.setUsername(rs.getString("username"));
                returnUser.setEmail(rs.getString("email"));
                returnUser.setPassword(rs.getString("password"));
                returnUser.setId(rs.getInt("id"));
                returnUser.setAdmin(rs.getString("admin"));
                returnUser.setLocked(rs.getBoolean("locked"));
                returnUser.setBookkeeper(rs.getBoolean("bookkeeper"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return returnUser;


    }

    /**
     * db access to get user by id
     * @param userId the id of the user
     * @return the found user (if any)
     */
    public static User getUser(int userId) {

        ResultSet rs = null;
        Statement stmt = null;
        User user = new User();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM user");
        buf.append(" WHERE id='" + userId + "'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {

                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setAdmin(rs.getString("admin"));
                if (rs.getInt("locked") == 1) {
                    user.setLocked(true);
                } else {
                    user.setLocked(false);
                }
                if (rs.getInt("bookkeeper") == 1) {
                    user.setBookkeeper(true);
                } else {
                    user.setBookkeeper(false);
                }


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the user");
        }

        return user;
    }

    /**
     * db access to get user by username
     * @param username the username of the user
     * @return the found user (if any)
     */
    public static User getUser(String username) {

        ResultSet rs = null;
        Statement stmt = null;
        User user = new User();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM user");
        buf.append(" WHERE username='" + username + "'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {

                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setAdmin(rs.getString("admin"));
                if (rs.getInt("locked") == 1) {
                    user.setLocked(true);
                } else {
                    user.setLocked(false);
                }
                if (rs.getInt("bookkeeper") == 1) {
                    user.setBookkeeper(true);
                } else {
                    user.setBookkeeper(false);
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the user");
        }

        return user;
    }

    /**
     * db access to update name
     * @param user the User to update
     * @param newFirstName the new first name
     * @param newLastName the new last name
     * @return the number of rows affected by the update
     */
    public static int updateName(
            User user, String newFirstName, String newLastName) {

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
        } catch (Exception e) {
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
        } catch (Exception e) {
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

    /**
     * db access to get list of all users
     * @return list of the found users (if any)
     */
    public static List<User> getUsers() {
        ResultSet rs = null;
        Statement stmt = null;
        List<User> returnList = new ArrayList<>();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM user");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs != null && rs.next()) {

                User user = new User();

                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setAdmin(rs.getString("admin"));
                if (rs.getInt("locked") == 1) {
                    user.setLocked(true);
                } else {
                    user.setLocked(false);
                }
                user.setLocked(rs.getBoolean("locked"));
                if (rs.getInt("bookkeeper") == 1) {
                    user.setBookkeeper(true);
                } else {
                    user.setBookkeeper(false);
                }
                user.setBookkeeper(rs.getBoolean("bookkeeper"));
                if (user != null) {
                    returnList.add(user);
                }
            }

            return returnList;

        } catch (SQLException e) {
            System.out.println(e.getMessage()
                    + "Fail to get the sales from the result set");
        }
        return returnList;
    }





    //--------------------Item logic----------------------------

    /**
     * db access to update item
     * @param itemId the id of the item to be updated
     * @param newItem the new info to be placed into the item
     * @return the number of rows affected by the update
     */
    public static int updateItem(int itemId, Item newItem) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE item");
        buf.append(" SET name='" + newItem.getName() + "'");
        buf.append(", description='" + newItem.getDescription() + "'");
        buf.append(", quantity='" + newItem.getQuantity() + "'");
        buf.append(", list_price='" + newItem.getListPrice() + "'");
        buf.append(", bottom_price='" + newItem.getBottomPrice() + "'");
        buf.append(", transaction_id='" + newItem.getTransactionId() + "'");
        buf.append(", sold=" + newItem.isSold());
        buf.append(" WHERE id=" + itemId);

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     * db access to get items within a sale
     * @param saleID the id of the sale
     * @return a list of items
     */
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

            while (rs != null && rs.next()) {

                Item returnItem = new Item();


                returnItem.setName(rs.getString("name"));
                returnItem.setDescription(rs.getString("description"));
                returnItem.setSaleId(rs.getInt("sale_id"));
                returnItem.setQuantity(rs.getInt("quantity"));
                returnItem.setListPrice(rs.getDouble("list_price"));
                returnItem.setBottomPrice(rs.getDouble("bottom_price"));
                returnItem.setId(rs.getInt("id"));
                returnItem.setTransactionId(rs.getInt("transaction_id"));
                if (rs.getInt("sold") == 1) {
                    returnItem.setSold(true);
                } else {
                    returnItem.setSold(false);
                }
                returnItem.setSold(rs.getBoolean("sold"));
                if (returnItem != null) {
                    returnList.add(returnItem);
                }


            }

            return returnList;

        } catch (SQLException e) {
            System.out.println(e.getMessage()
                    + "Fail to get the sales from the result set");
        }
        return returnList;
    }

    /**
     * db access to get item by its id
     * @param itemId the id of the item
     * @return the found item (if any)
     */
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
        buf.append(" WHERE id=" + itemId + "");

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
                item.setId(rs.getInt("id"));
                item.setTransactionId(rs.getInt("transaction_id"));
                if (rs.getInt("sold") == 1) {
                    item.setSold(true);
                } else {
                    item.setSold(false);
                }
                item.setSold(rs.getBoolean("sold"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the item");
        }

        return item;
    }


    /**
     * db access to sell an item
     * @param itemId the id of the item to be updated
     * @param transactionId the id of the transaction to sell the item to
     * @return the number of rows affected by the update
     */
    public static int sellItem(int itemId, int transactionId) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE item");
        buf.append(" SET sold='" + 1 + "'");
        buf.append(", transaction_id='" + transactionId + "'");
        buf.append(" WHERE id=" + itemId);

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public static Transaction getMostRecentTransaction() {

        ResultSet rs = null;
        Statement stmt = null;
        Transaction transaction = new Transaction();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM transaction ORDER BY id DESC LIMIT 1");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {

                transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the transaction");
        }

        return transaction;
    }



    //--------------------Search logic----------------------------

    /**
     * db access search item by name in a sale
     * @param sale the sale to be searched in
     * @param name the search string
     * @return a list of matching items
     */
    public static List<Item> searchItemInSale(Sale sale, String name) {

        ResultSet rs = null;
        Statement stmt = null;
        List<Item> returnList = new ArrayList<Item>();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM item");
        buf.append(" WHERE sale_id='" + sale.getId() + "'");
        buf.append(" AND name LIKE '%" + name + "%'");
        buf.append(" OR description LIKE '%" + name + "%'");

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
                returnItem.setId(rs.getInt("transaction_id"));
                if (rs.getInt("sold") == 1) {
                    returnItem.setSold(true);
                } else {
                    returnItem.setSold(false);
                }
                returnItem.setSold(rs.getBoolean("sold"));
                returnList.add(returnItem);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return returnList;
    }

    /**
     * db access to search all items
     * @param name the desired name of the item
     * @return the found items (if any)
     */
    public static List<Item> searchAllItems(String name) {

        ResultSet rs = null;
        Statement stmt = null;
        List<Item> returnList = new ArrayList<Item>();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM item");
        buf.append(" WHERE name LIKE '%" + name + "%'");
        buf.append(" OR description LIKE '%" + name + "%'");

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
                returnItem.setId(rs.getInt("transaction_id"));
                if (rs.getInt("sold") == 1) {
                    returnItem.setSold(true);
                } else {
                    returnItem.setSold(false);
                }
                returnItem.setSold(rs.getBoolean("sold"));
                returnList.add(returnItem);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return returnList;
    }

    /**
     * db access to search all sales
     * @param name the desired name of the sale
     * @return the found sales (if any)
     */
    public static List<Sale> searchAllSales(String name) {
        ResultSet rs = null;
        Statement stmt = null;
        List<Sale> returnList = new ArrayList<Sale>();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM sale");
        buf.append(" WHERE name LIKE '%" + name + "%'");
        buf.append(" OR description LIKE '%" + name + "%'");
        buf.append(" OR date LIKE '%" + name + "%'");
        buf.append(" OR location LIKE '%" + name + "%'");
        buf.append(" OR start_time LIKE '%" + name + "%'");
        buf.append(" OR end_time LIKE '%" + name + "%'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {

            while (rs.next()) {

                Sale returnSale = new Sale();
                returnSale.setName(rs.getString("name"));
                returnSale.setLocation(rs.getString("location"));
                returnSale.setStartTime(rs.getString("start_time"));
                returnSale.setEndTime(rs.getString("end_time"));
                returnSale.setDate(rs.getString("date"));
                returnSale.setId(rs.getInt("id"));

                returnList.add(returnSale);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return returnList;
    }







    //-------------------------------Sale logic------------------------

    /**
     * db access to get user's sales
     * @param saleAdminId the id of the user
     * @return a list of matching sales
     */
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

            while (rs != null && rs.next()) {

                Sale returnSale = new Sale();


                returnSale.setEndTime(rs.getString("start_time"));
                returnSale.setEndTime(rs.getString("end_time"));
                returnSale.setDate(rs.getString("date"));
                returnSale.setSaleAdminId(rs.getInt("sale_admin_id"));
                returnSale.setLocation(rs.getString("location"));
                returnSale.setDescription(rs.getString("description"));
                returnSale.setName(rs.getString("name"));
                returnSale.setId(rs.getInt("id"));

                if (returnSale != null) {
                    returnList.add(returnSale);
                }


            }

            return returnList;

        } catch (SQLException e) {
            System.out.println(e.getMessage()
                    + "Fail to get the sales from the resultset");
        }
        return returnList;
    }

    /**
     * db access to find a sale by its id
     * @param saleId the id of the sale
     * @return the found sale (if any)
     */
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

    /**
     * db access to get all sales in the system
     * @return the found sales (if any)
     */
    public static List<Sale> getAllSales() {

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


        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs != null && rs.next()) {

                Sale returnSale = new Sale();


                returnSale.setEndTime(rs.getString("start_time"));
                returnSale.setEndTime(rs.getString("end_time"));
                returnSale.setDate(rs.getString("date"));
                returnSale.setSaleAdminId(rs.getInt("sale_admin_id"));
                returnSale.setLocation(rs.getString("location"));
                returnSale.setDescription(rs.getString("description"));
                returnSale.setName(rs.getString("name"));
                returnSale.setId(rs.getInt("id"));

                if (returnSale != null) {
                    returnList.add(returnSale);
                }
            }
            return returnList;
        } catch (SQLException e) {
            System.out.println(e.getMessage()
                    + "Fail to get the sales from the resultset");
        }
        return returnList;
    }




    //-------------------Transaction logic-------------------------------

    /**
     * db access to get transaction for a user within a sale
     * @param userId the user id
     * @param saleId the sale id
     * @return the found transaction (if any)
     */
    public static Transaction getOpenTransaction(int userId, int saleId) {

        ResultSet rs = null;
        Statement stmt = null;
        Transaction transaction = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM transaction");
        buf.append(" WHERE user_id='" + userId + "'");
        buf.append(" AND sale_id='" + saleId + "'");
        buf.append(" AND closed<>'" + true + "'");


        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {
                transaction.setSaleId(rs.getInt("sale_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the item");
        }

        return transaction;
    }

    /**
     * db access to get a transaction by its id
     * @param transId the transaction id
     * @return the found transaction (if any)
     */
    public static Transaction getTransaction(int transId) {

        ResultSet rs = null;
        Statement stmt = null;
        Transaction transaction = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM transaction");
        buf.append(" WHERE id='" + transId + "'");


        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {
                transaction.setSaleId(rs.getInt("sale_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setId(rs.getInt("id"));
                transaction.setDate(rs.getString("date"));
                transaction.setTime(rs.getString("time"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the item");
        }

        return transaction;
    }

    /**
     * db access to get all transactions for a user
     * @param userId the user id
     * @return the found transactions (if any)
     */
    public static List<Transaction> viewTransactions(int userId) {

        ResultSet rs = null;
        Statement stmt = null;
        List<Transaction> returnList = new ArrayList<>();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM transaction");
        buf.append(" WHERE user_id='" + userId + "'");


        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {
                Transaction transaction = new Transaction();

                transaction.setSaleId(rs.getInt("sale_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setId(rs.getInt("id"));
                transaction.setDate(rs.getString("date"));
                transaction.setTime(rs.getString("time"));

                if (transaction != null) {
                    returnList.add(transaction);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the item");
        }
        return returnList;
    }

    /**
     * updates transaction
     * @param transId transaction id
     * @param newTransaction the new transaction to update
     * @return result if successful
     */
    public static int updateTransaction(
            int transId, Transaction newTransaction) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE transaction");
        buf.append(" SET paymentmethod='"
                + newTransaction.getPaymentMethod() + "'");
        buf.append(", date='" + newTransaction.getDate() + "'");
        buf.append(", time='" + newTransaction.getTime() + "'");
        buf.append(", items='" + newTransaction.getItems() + "'");
        buf.append(", closed=1");
        buf.append(" WHERE id=" + transId);

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * gets all transactions
     * @return a list of transactions
     */
    public static List<Transaction> getAllTransactions() {

        ResultSet rs = null;
        Statement stmt = null;
        List<Transaction> returnList = new ArrayList<>();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("SELECT * FROM transaction");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs.next()) {
                Transaction transaction = new Transaction();

                transaction.setSaleId(rs.getInt("sale_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setId(rs.getInt("id"));
                transaction.setDate(rs.getString("date"));
                transaction.setTime(rs.getString("time"));
                transaction.setPaymentMethod(rs.getString("paymentmethod"));
                transaction.setTotalPrice(rs.getDouble("total_price"));
                transaction.setClosed(rs.getBoolean("closed"));

                if (transaction != null) {
                    returnList.add(transaction);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Fail to get the item");
        }
        return returnList;
    }

    /**
     * db access to get items in a transaction
     * @param transid the transaction id to search
     * @return a list of items
     */
    public static List<Item> getTransactionItems(int transid) {

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
        buf.append(" WHERE transaction_id='" + transid + "'");

        System.out.println("Execute Query: " + buf.toString());
        try {
            rs = stmt.executeQuery(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Query Execute fail");
        }

        try {

            while (rs != null && rs.next()) {

                Item returnItem = new Item();


                returnItem.setName(rs.getString("name"));
                returnItem.setDescription(rs.getString("description"));
                returnItem.setSaleId(rs.getInt("sale_id"));
                returnItem.setQuantity(rs.getInt("quantity"));
                returnItem.setListPrice(rs.getDouble("list_price"));
                returnItem.setBottomPrice(rs.getDouble("bottom_price"));
                returnItem.setId(rs.getInt("id"));
                returnItem.setId(rs.getInt("transaction_id"));
                if (rs.getInt("sold") == 1) {
                    returnItem.setSold(true);
                } else {
                    returnItem.setSold(false);
                }
                returnItem.setSold(rs.getBoolean("sold"));
                if (returnItem != null) {
                    returnList.add(returnItem);
                }


            }

            return returnList;

        } catch (SQLException e) {
            System.out.println(e.getMessage()
                    + "Fail to get the sales from the result set");
        }
        return returnList;
    }






//--------------------------Admin logic--------------------

    /**
     * db access to lock or unlock a user
     * @param user the user to lock/unlock
     * @return the number of rows affected
     */
    public static int lockUnlockUser(User user) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE user");
        if (user.getLocked()) {
            buf.append(" SET locked=1");
        } else {
            buf.append(" SET locked=0");
        }
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
     * db access to toggle admin rights for a user
     * @param user the user to toggle admin rights for
     * @return the number of rows affected
     */
    public static int toggleAdmin(User user) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE user");
        buf.append(" SET admin='" + user.getAdmin() + "'");
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
     * db access to toggle bookkeeper rights for a user
     * @param user the user to toggle bookkeeper rights for
     * @return the number of rows affected
     */
    public static int toggleBookkeeper(User user) {

        int result = 0;
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        StringBuffer buf = new StringBuffer();
        buf.append("UPDATE user");
        if (user.isBookkeeper()) {
            buf.append(" SET bookkeeper=1");
        } else {
            buf.append(" SET bookkeeper=0");
        }
        buf.append(" WHERE id=" + user.getId());

        System.out.println("Execute Query: " + buf.toString());
        try {
            result = stmt.executeUpdate(buf.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}