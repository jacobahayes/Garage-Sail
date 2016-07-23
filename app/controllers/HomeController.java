package controllers;

import models.Item;
import models.Sale;
import models.User;
import models.Transaction;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.additem;
import views.html.adminpage;
import views.html.allsales;
import views.html.allsearchitems;
import views.html.basictag;
import views.html.bookkeeper;
import views.html.createsale;
import views.html.externalsale;
import views.html.homepage;
import views.html.index;
import views.html.item;
import views.html.maketransaction;
import views.html.profile;
import views.html.receipt;
import views.html.registration;
import views.html.salepage;
import views.html.sales;
import views.html.searchitemresults;
import views.html.searchsalesresults;
import views.html.similaritems;
import views.html.singletransaction;
import views.html.tag;
import views.html.transaction;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import java.text.SimpleDateFormat;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


    private static User loggedInUser = null;
    private static Sale saleInView = null;
    private static Transaction currentTransaction = null;
    private static int loginCount = 0;
    private static int transactionId;


    //-----------------------Homepage-ish logic------------------------

    /**
     * renders the initial login page
     * @return the HTTP response
     */
    public Result index() {
        loggedInUser = null;
        return ok(index.render("Your new application is ready."));
    }

    /**
     * renders the homepage
     * @return the HTTP response
     */
    public Result renderHome() {
        return ok(homepage.render());
    }

    /**
     * skeleton to login to the application
     * @return the HTTP response depending if the login was successful or not
     */
    public Result login() {

        User loginUser = Form.form(User.class).bindFromRequest().get();
        loginUser.addLoginAtempt();
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        User foundUser = JavaApplicationDatabase.attemptLogin(
                username, password);


        if (foundUser != null && !foundUser.getLocked() && loginCount < 3) {
            loggedInUser = foundUser;
            loginCount = 0;
            if (foundUser.getAdmin().equalsIgnoreCase("true")) {
                loggedInUser.setAdmin("true");
            } else {
                loggedInUser.setAdmin("false");
            }
            return ok(homepage.render());
        } else if (loginCount > 3) {

            User user = JavaApplicationDatabase.getUser(username);
            user.setLocked(true);
            JavaApplicationDatabase.lockUnlockUser(user);
            loginCount = 0;
            return ok(index.render("Garage Sail"));

        } else {

            loginCount++;

            return ok(index.render("Garage Sail"));
        }
    }

    /**
     * pulls up the registration page
     * @return the HTTP response to render the registration page
     */
    public Result registration() {
        return ok(registration.render());
    }

    /**
     * skeleton to set up registration
     * @return the HTTP response
     */
    public Result registerUser() {

        String[] postAction = request().body().asFormUrlEncoded().get(
                "action");
        String action = postAction[0];

        if ("register".equals(action)) {

            return completeRegister(request());

        } else if ("cancel".equals(action)) {

            return cancelRegister(request());

        } else {

            return badRequest("This action is not allowed");

        }
    }

    /**
     * completes registration for a user
     * @param request the HTTP request
     * @return the HTTP response
     */
    public Result completeRegister(Http.Request request) {

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");

        String dateNow = formatter.format(currentDate.getTime());

        User registerUser = Form.form(User.class).bindFromRequest().get();
        registerUser.setLocked(false);
        registerUser.setBookkeeper(false);
        registerUser.setDate(dateNow);
        registerUser.save();

        loggedInUser = registerUser;

        return ok(homepage.render());
    }

    /**
     * cancels registration for a user
     * @param request the HTTP request
     * @return the HTTP response
     */
    public Result cancelRegister(Http.Request request) {

        return redirect(routes.HomeController.index());
    }

    /**
     * returns the User to their homepage
     * @return the HTTP response
     */
    public Result cancel() {
        return ok(homepage.render());
    }




    //--------------------Profile logic--------------------------------

    /**
     * renders the my profile page
     * @return the HTTP response
     */
    public Result profile() {
        return ok(profile.render(loggedInUser));
    }

    /**
     * skeleton to change the User's name
     * @return the HTTP response
     */
    public Result editName() {

        String[] postAction = request().body().asFormUrlEncoded().get(
                "action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newFirstName = updateUser.getFirstName();
        String newLastName = updateUser.getLastName();

        int result = JavaApplicationDatabase.updateName(
                loggedInUser, newFirstName, newLastName);

        if (result == 1) {
            loggedInUser = JavaApplicationDatabase.getUser(
                    loggedInUser.getId());
            return ok(profile.render(loggedInUser));

        } else {

            return badRequest("An error occurred while saving");

        }
    }

    /**
     * skeleton to change the User's email
     * @return the HTTP response
     */
    public Result editEmail() {
        String[] postAction = request().body().asFormUrlEncoded().get(
                "action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newEmail = updateUser.getEmail();

        int result = JavaApplicationDatabase.updateEmail(
                loggedInUser, newEmail);
        System.out.println("RESULT: " + result);
        if (result == 1) {
            loggedInUser = JavaApplicationDatabase.getUser(
                    loggedInUser.getId());
            return ok(profile.render(loggedInUser));
        } else {
            return badRequest("An error occured while saving");
        }
    }

    /**
     * skeleton to change the User's username
     * @return the HTTP response
     */
    public Result editUsername() {

        String[] postAction = request().body().asFormUrlEncoded().get(
                "action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newUsername = updateUser.getUsername();

        int result = JavaApplicationDatabase.updateUsername(
                loggedInUser, newUsername);
        if (result == 1) {

            loggedInUser = JavaApplicationDatabase.getUser(
                    loggedInUser.getId());
            return ok(profile.render(loggedInUser));

        } else {

            return badRequest("An error occurred while saving");

        }
    }

    /**
     * skeleton to change the User's password
     * @return the HTTP response
     */
    public Result editPassword() {

        String[] postAction = request().body().asFormUrlEncoded().get(
                "action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newPassword = updateUser.getPassword();

        int result = JavaApplicationDatabase.updatePassword(
                loggedInUser, newPassword);
        if (result == 1) {
            loggedInUser = JavaApplicationDatabase.getUser(
                    loggedInUser.getId());
            return ok(profile.render(loggedInUser));
        } else {
            return badRequest("An error occured while saving");
        }
    }




    //----------------------------Sale logic--------------------------------

    /**
     * browse all sales in thes system
     * @return the HTTP response
     */
    public Result browseSales() {
        List<Sale> salesfromdb = new ArrayList<>();
        try {
            salesfromdb = JavaApplicationDatabase.getAllSales();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(allsales.render(salesfromdb));
    }

    /**
     * render a sale that doesn't belong to the user
     * @return the HTTP response
     */
    public Result externalSale() {

        Sale sale = Form.form(Sale.class).bindFromRequest().get();
        saleInView = JavaApplicationDatabase.getSale(sale.getId());
        System.out.println(saleInView.getId());
        System.out.println(saleInView.getDescription());
        Transaction transaction = JavaApplicationDatabase.getOpenTransaction(
                loggedInUser.getId(), saleInView.getId());
        if (transaction == null) {
            transaction = new Transaction();
            transaction.setSaleId(saleInView.getId());
            transaction.setUserId(loggedInUser.getId());
            transaction.save();
        }
        System.out.println(transaction.getId());
        System.out.println(transaction.getSaleId());
        System.out.println(transaction.getUserId());
        System.out.println(transaction.getId());
        transactionId = transaction.getId();
        List<Item> itemsfromdb = new ArrayList<>();
        try {
            itemsfromdb = JavaApplicationDatabase.getSaleItems(
                    saleInView.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(externalsale.render(saleInView, itemsfromdb));
    }

    /**
     * adds sale item to user then renders page to edit items on the sale page
     * @return the HTTP response
     */
    public Result addSale() {
        String[] postAction = request().body().asFormUrlEncoded().get(
                "action");
        Sale newSale = Form.form(Sale.class).bindFromRequest().get();


        //FOR TESTING IT DEFAULTS TO USERNAME STUART. ** REMOVE LATER **
        if (loggedInUser == null) {
            //System.out.println("no username");
            loggedInUser = new User();
            loggedInUser.setUsername("stuart");
            newSale.setSaleAdminId(loggedInUser.getId());
        } else {
            newSale.setSaleAdminId(loggedInUser.getId());
        }

        newSale.save();
        saleInView = newSale;

        return ok(salepage.render(saleInView, new ArrayList<>()));
    }


    /**
     * renders the sale screen with the user's sales
     * @return the HTTP response
     */
    public Result saleScreen() {
        List<Sale> salesfromdb = new ArrayList<>();

        try {
            salesfromdb = JavaApplicationDatabase.getMySales(
                    loggedInUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }



        return ok(sales.render(salesfromdb));
    }

    /**
     * renders the create sale screen
     * @return the HTTP response
     */
    public Result renderCreateSale() {
        return ok(createsale.render());
    }

    /**
     * renders the specific sale page
     * @return the HTTP response
     */
    public Result salePage() {

        Sale sale = Form.form(Sale.class).bindFromRequest().get();
        saleInView = JavaApplicationDatabase.getSale(sale.getId());


        List<Item> itemsfromdb = new ArrayList<>();
        try {
            itemsfromdb = JavaApplicationDatabase.getSaleItems(
                    saleInView.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(salepage.render(saleInView, itemsfromdb));
    }

    /**
     * allows the user to return to the sale page from the add item page
     * @return the HTTP response
     */
    public Result backToSalePage() {

        List<Item> itemsfromdb = new ArrayList<>();
        try {
            itemsfromdb = JavaApplicationDatabase.getSaleItems(
                    saleInView.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(salepage.render(saleInView, itemsfromdb));
    }




    //--------------------Item logic-------------------------------

    /**
     * renders the add item screen
     * @return the HTTP response
     */
    public Result renderAddItem() {
        return ok(additem.render(saleInView));
    }


    /**
     * controls flow when adding an item
     * @return the HTTP response
     */
    public Result addItem() {

        String[] postAction = request().body().asFormUrlEncoded().get(
                "action");
        String action = postAction[0];

        if ("additem".equals(action)) {

            return completeAddItem(request());

        } else if ("cancel".equals(action)) {

            return backToSalePage();

        } else {

            return badRequest("This action is not allowed");

        }
    }

    /**
     * completes the add item process, returns to add item page
     * @param request the HTTP request
     * @return the HTTP response
     */
    public Result completeAddItem(Http.Request request) {

        Item newItem = Form.form(Item.class).bindFromRequest().get();
        int quantity = newItem.getQuantity();
        newItem.setQuantity(1);
        while (quantity > 1) {
            Item repeatItem = new Item();
            repeatItem.setBottomPrice(newItem.getBottomPrice());
            repeatItem.setDescription(newItem.getDescription());
            repeatItem.setName(newItem.getName());
            repeatItem.setQuantity(1);
            repeatItem.setListPrice(newItem.getListPrice());
            repeatItem.setSaleId(saleInView.getId());
            repeatItem.save();
            quantity--;
        }
        newItem.setSaleId(saleInView.getId());
        newItem.save();

        return ok(additem.render(saleInView));

    }

    /**
     * renders the specific item page
     * @return the HTTP response
     */
    public Result renderItem() {

        Item selectedItem = Form.form(Item.class).bindFromRequest().get();
        System.out.println(selectedItem.getId());
        System.out.println(selectedItem.getName());
        Item itemToRender = JavaApplicationDatabase.getItem(
                selectedItem.getId());

        return ok(item.render(itemToRender));
    }

    /**
     * controls flow when updating an item
     * @return the HTTP response
     */
    public Result updateItem() {

        Item updatedItem = Form.form(Item.class).bindFromRequest().get();

        int result = JavaApplicationDatabase.updateItem(
                updatedItem.getId(), updatedItem);
        if (result == 1) {
            List<Item> itemsfromdb = JavaApplicationDatabase.getSaleItems(
                    saleInView.getId());
            //return ok(item.render(itemToRender));
            return ok(salepage.render(saleInView, itemsfromdb));

        } else {

            return badRequest("An error occurred while saving");

        }

    }

    /**
     * adds a new item by its id number
     * @return the HTTP response
     */
    public Result addItemById() {
        String[] postAction = request().body().asFormUrlEncoded().get(
                "action");
        String action = postAction[0];
        List<Item> itemsToAdd = new ArrayList<>();
        while (!action.isEmpty()) {
            Item item = JavaApplicationDatabase.getItem(
                    Integer.parseInt(
                            action.substring(0, action.indexOf(','))));
            action = action.substring(action.indexOf(',') + 1);
            itemsToAdd.add(item);
            if (action.equals(",")) {
                action = "";
            }
        }
        return TODO;
    }

    /**
     * shows the related items view
     * @return the HTTP response
     */
    public Result relatedItems(String itemName) {
        return ok(similaritems.render(itemName));
    }



    //-------------------Search logic-------------------------------

    /**
     * allows for searching an item in a sale by name
     * @return the HTTP response
     */
    public Result searchItemInSale() {

        // ??
        Sale searchSale = Form.form(Sale.class).bindFromRequest().get();

        List<Item> itemsfromdb = new ArrayList<>();
        try {
            itemsfromdb = JavaApplicationDatabase.searchItemInSale(
                    saleInView, searchSale.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ok(searchitemresults.render(saleInView, itemsfromdb));
    }

    /**
     * allows for searching all items in the system
     * @return the HTTP response
     */
    public Result searchAllItems() {

        // ??
        Sale searchSale = Form.form(Sale.class).bindFromRequest().get();

        List<Item> itemsfromdb = new ArrayList<>();
        try {
            itemsfromdb = JavaApplicationDatabase.searchAllItems(
                    searchSale.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ok(allsearchitems.render(saleInView, itemsfromdb));
    }

    /**
     * allows for searching all sales in the system
     * @return the HTTP response
     */
    public Result searchAllSales() {
        Sale searchSale = Form.form(Sale.class).bindFromRequest().get();
        List<Sale> foundSales = new ArrayList<>();
        try {
            foundSales = JavaApplicationDatabase.searchAllSales(
                    searchSale.getName());
            return ok(searchsalesresults.render(saleInView, foundSales));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(searchsalesresults.render(saleInView, foundSales));
    }





    //------------------Tag logic------------------------

    /**
     * renders the pring tage page for an item
     * @return the HTTP response
     */
    public Result printTag() {
        Item item = Form.form(Item.class).bindFromRequest().get();

        List<Item> itemsfromdb = new ArrayList<>();
        try {
            Item itemInView = JavaApplicationDatabase.getItem(item.getId());
            itemsfromdb.add(itemInView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(tag.render(itemsfromdb, saleInView, loggedInUser));
    }

    /**
     * renders a single basic tag for an item
     * @return the HTTP response
     */
    public Result singleBasicTag() {
        Item item = Form.form(Item.class).bindFromRequest().get();

        List<Item> itemsfromdb = new ArrayList<>();
        try {
            Item itemInView = JavaApplicationDatabase.getItem(item.getId());
            itemsfromdb.add(itemInView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(basictag.render(itemsfromdb, saleInView, loggedInUser));
    }

    /**
     * renders a list of tags for all items in a sale
     * @return the HTTP response
     */
    public Result printAllTags() {
        List<Item> itemsfromdb = new ArrayList<>();
        try {
            itemsfromdb = JavaApplicationDatabase.getSaleItems(
                    saleInView.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(tag.render(itemsfromdb, saleInView, loggedInUser));
    }

    /**
     * renders all tags for all items in the sale
     * @return the HTTP response
     */
    public Result basicTags() {
        List<Item> itemsfromdb = new ArrayList<>();
        try {
            itemsfromdb = JavaApplicationDatabase.getSaleItems(
                    saleInView.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(basictag.render(itemsfromdb, saleInView, loggedInUser));
    }





    //-------------------Transaction logic---------------------------

    /**
     * renders the transactions page
     * @return the HTTP response
     */
    public Result viewTransactions() {
        List<Transaction> transactionsList = new ArrayList<Transaction>();
        transactionsList = JavaApplicationDatabase.viewTransactions(
                loggedInUser.getId());
        List<Transaction> open = new ArrayList<>();
        List<Transaction> closed = new ArrayList<>();
        for (Transaction transaction : transactionsList) {
            if (transaction.getClosed()) {
                closed.add(transaction);
            } else {
                open.add(transaction);
            }
        }
        return ok(transaction.render(open, closed, loggedInUser));
    }

    /**
     * allows the user to view a single transaction
     * @return the HTTP response
     */
    public Result viewSingleTransaction() {
        List<Item> returnlist = new ArrayList<>();
        returnlist = JavaApplicationDatabase.getTransactionItems(
                transactionId);
        return ok(singletransaction.render(returnlist));
    }

    /**
     * renders a single transaction view
     * @return the HTTP response
     */
    public Result singleTransaction() {
        System.out.println("singleTransaction");
        Transaction transaction = Form.form(
                Transaction.class).bindFromRequest().get();
        Transaction returnTransaction = new Transaction();
        try {
            returnTransaction = JavaApplicationDatabase.getTransaction(
                    transaction.getId());
            System.out.println(returnTransaction.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        transactionId = returnTransaction.getId();
        return viewSingleTransaction();
    }

    /**
     * allows the user to add a transaction
     * @return the HTTP response
     */
    public Result addTransaction() {
        Transaction transaction = Form.form(
                Transaction.class).bindFromRequest().get();
        try {
            //if empty (not already a transaction), save it.
            // else, go to transaction page.
            if (JavaApplicationDatabase.getOpenTransaction(
                    loggedInUser.getId(), saleInView.getId()) == null) {
                transaction.setSaleId(saleInView.getId());
                transaction.setUserId(loggedInUser.getId());
                transaction.setClosed(false);
                transaction.save();
            }
        } catch (Exception e) {
            return viewTransactions();
        }
        return viewTransactions();
    }

    /**
     * allows user to add an item to their transaction
     * @return the HTTP response
     */
    public Result addItemToTransaction() {
        Item item = Form.form(Item.class).bindFromRequest().get();
        Item returnItem = new Item();
        System.out.println(returnItem.getId());
        Transaction currentTransaction = new Transaction();
        List<Item> itemlist = new ArrayList<>();

        try {
            returnItem = JavaApplicationDatabase.getItem(item.getId());
            currentTransaction = JavaApplicationDatabase.getTransaction(
                    transactionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(currentTransaction.getId());

        try {
            itemlist = JavaApplicationDatabase.getTransactionItems(
                    currentTransaction.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(returnItem.getDescription());
        System.out.println(returnItem.getId());
        System.out.println(currentTransaction.getId());
        returnItem.setTransactionId(currentTransaction.getId());
        JavaApplicationDatabase.updateItem(returnItem.getId(), returnItem);
        return viewSingleTransaction();
    }


    public Result makeTransaction() {

        currentTransaction = new Transaction();
        currentTransaction.setSaleId(saleInView.getId());
        currentTransaction.setItems(new ArrayList<String>());
        return ok(maketransaction.render(currentTransaction));
    }

    public Result addItemsToTransaction() {

        Item item = Form.form(Item.class).bindFromRequest().get();
        currentTransaction.addItem(item.getName());
        Item boughtItem = new Item();
        try {
            boughtItem = JavaApplicationDatabase.getItem(Integer.parseInt(item.getName()));
            currentTransaction.setTotalPrice(currentTransaction.getTotalPrice() + boughtItem.getListPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok(maketransaction.render(currentTransaction));
    }

    public Result processTransaction() {

        String[] paymentAction = request().body().asFormUrlEncoded().get(
                "paymentmethod");
        String paymentMethod = paymentAction[0];
        if (paymentMethod.equalsIgnoreCase("creditdebit")) {
            currentTransaction.setPaymentMethod("creditdebit");
        } else if (paymentMethod.equalsIgnoreCase("bitcoin")) {
            currentTransaction.setPaymentMethod("bitcoin");
        } else {
            currentTransaction.setPaymentMethod("cash");
        }

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
        SimpleDateFormat formattertime = new SimpleDateFormat("hh:mm:ss");
        String dateNow = formatter.format(currentDate.getTime());
        String timeNow = formattertime.format(currentDate.getTime());

        double totalPrice = 0.0;

        for (String itemId : currentTransaction.getItems()) {
            Item boughtItem = new Item();
            try {
                boughtItem = JavaApplicationDatabase.getItem(Integer.parseInt(itemId));
                totalPrice = totalPrice + boughtItem.getListPrice();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        currentTransaction.setDate(dateNow);
        currentTransaction.setTime(timeNow);
        currentTransaction.setTotalPrice(totalPrice);
        currentTransaction.setClosed(true);
        currentTransaction.setPaymentMethod(paymentMethod);
        currentTransaction.save();

        Transaction latestTransaction = new Transaction();
        for (String itemId : currentTransaction.getItems()) {
            Item boughtItem = new Item();
            try {
                boughtItem = JavaApplicationDatabase.getItem(Integer.parseInt(itemId));
                System.out.println(boughtItem.getName() + ": " + boughtItem.getId());
                latestTransaction = JavaApplicationDatabase.getMostRecentTransaction();
                JavaApplicationDatabase.sellItem(boughtItem.getId(), latestTransaction.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Item> itemsfromdb = new ArrayList<>();
        try {
            latestTransaction = JavaApplicationDatabase.getMostRecentTransaction();
            itemsfromdb = JavaApplicationDatabase.getTransactionItems(latestTransaction.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ok(receipt.render(currentTransaction, itemsfromdb));
    }



    //-------------------Processing logic------------------------------------

    /**
     * allows a user to process a transaction within a sale
     * @return the HTTP response
     */
    public Result processSale() {
        String[] paymentAction = request().body().asFormUrlEncoded().get(
                "paymentmethod");

        String itemString = "";
        Transaction transaction = new Transaction();

        String paymentMethod = paymentAction[0];
        if (paymentMethod.equalsIgnoreCase("creditdebit")) {
            transaction.setPaymentMethod("creditdebit");
        } else if (paymentMethod.equalsIgnoreCase("bitcoin")) {
            transaction.setPaymentMethod("bitcoin");
        } else {
            transaction.setPaymentMethod("cash");
        }

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
        SimpleDateFormat formattertime = new SimpleDateFormat("hh:mm:ss");
        String dateNow = formatter.format(currentDate.getTime());
        String timeNow = formattertime.format(currentDate.getTime());

        double totalPrice = 0.0;
        List<Item> itemsfromdb = new ArrayList<>();
        try {
            itemsfromdb = JavaApplicationDatabase.getTransactionItems(
                    transactionId);
        } catch (Exception e) {
            badRequest("Could not get items");
        }
        transaction.setDate(dateNow);
        transaction.setTime(timeNow);
        transaction.setTotalPrice(totalPrice);
        transaction.setClosed(true);
        transaction.setPaymentMethod(paymentMethod);

        System.out.println(transaction.getDate());
        System.out.println(transaction.getTotalPrice());
        System.out.println(transaction.getTime());
        System.out.println(transaction.getPaymentMethod());

        int result = JavaApplicationDatabase.updateTransaction(
                transactionId, transaction);

        if (result == 1) {
            return ok(receipt.render(transaction, itemsfromdb));
        } else {
            return badRequest("Could not complete transaction.");
        }
    }

    /**
     * renders the receipt page
     * @return the HTTP response
     */
    public Result renderReceipt() {
        Transaction transaction = Form.form(
                Transaction.class).bindFromRequest().get();
        Transaction returnTransaction = new Transaction();
        List<Item> items = new ArrayList<>();
        try {
            returnTransaction = JavaApplicationDatabase.getTransaction(
                    transactionId);
            items = JavaApplicationDatabase.getTransactionItems(transactionId);
        } catch (Exception e) {
            badRequest("Could not get items");
        }
        return ok(receipt.render(returnTransaction, items));
    }






    //-----------------Admin logic-------------------------------------------

    /**
     * renders the admin page if a user is an admin type
     * @return the HTTP response
     */
    public Result adminPage() {
        if (loggedInUser.getAdmin().equalsIgnoreCase("true")) {
            return ok(adminpage.render(JavaApplicationDatabase.getUsers()));
        } else {
            return ok(homepage.render());
        }
    }

    /**
     * allows an admin to lock or unlock any user
     * @return the HTTP response
     */
    public Result lockUnlock() {
        User user = Form.form(User.class).bindFromRequest().get();

        User foundUser = JavaApplicationDatabase.getUser(user.getId());

        if (foundUser.getLocked()) {
            foundUser.setLocked(false);
        } else {
            foundUser.setLocked(true);
        }

        int result = JavaApplicationDatabase.lockUnlockUser(foundUser);
        if (result == 1) {

            ok(adminpage.render(JavaApplicationDatabase.getUsers()));

        } else {

            return badRequest("An error occurred while saving");

        }

        return ok(adminpage.render(JavaApplicationDatabase.getUsers()));
    }

    /**
     * allows an admin to give or take away admin rights for any user
     * @return the HTTP response
     */
    public Result toggleAdmin() {
        User user = Form.form(User.class).bindFromRequest().get();

        User foundUser = JavaApplicationDatabase.getUser(user.getId());

        if (foundUser.getAdmin().equalsIgnoreCase("false")) {
            foundUser.setAdmin("true");
        } else if (foundUser.getAdmin().equalsIgnoreCase("true")) {
            foundUser.setAdmin("false");
        }

        int result = JavaApplicationDatabase.toggleAdmin(foundUser);
        if (result == 1) {

            ok(adminpage.render(JavaApplicationDatabase.getUsers()));

        } else {

            return badRequest("An error occurred while saving");

        }

        return ok(adminpage.render(JavaApplicationDatabase.getUsers()));
    }

    /**
     * allows an admin to toggle bookkeeper rights for any user
     * @return the HTTP response
     */
    public Result toggleBookkeeper() {
        User user = Form.form(User.class).bindFromRequest().get();

        User foundUser = JavaApplicationDatabase.getUser(user.getId());
        if (foundUser.isBookkeeper()) {
            foundUser.setBookkeeper(false);
        } else {
            foundUser.setBookkeeper(true);
        }
        int result = JavaApplicationDatabase.toggleBookkeeper(foundUser);
        if (result == 1) {

            ok(adminpage.render(JavaApplicationDatabase.getUsers()));

        } else {

            return badRequest("An error occurred while saving");

        }

        return ok(adminpage.render(JavaApplicationDatabase.getUsers()));
    }

    /**
     * renders the financial page for the bookkeeper
     * @return the HTTP response
     */
    public Result bookPage() {
        if (loggedInUser.isBookkeeper()) {
            return ok(bookkeeper.render(
                    JavaApplicationDatabase.getAllTransactions()));
        } else {
            return viewTransactions();
        }
    }

    /**
     * allows an admin to change their password
     * @return the HTTP response
     */
    public Result adminChangePassword() {
        String[] postAction = request().body().asFormUrlEncoded().get(
                "password");
        String[] postAction1 = request().body().asFormUrlEncoded().get("id");
        String password = postAction[0];
        int id = Integer.parseInt(postAction1[0]);
        JavaApplicationDatabase.updatePassword(
                JavaApplicationDatabase.getUser(id), password);
        return ok(adminpage.render(JavaApplicationDatabase.getUsers()));
    }
}
