package controllers;

import javax.inject.Inject;

import com.avaje.ebean.Model;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import models.Item;
import models.Sale;
import models.User;
import play.data.Form;
import play.mvc.*;
import play.db.*;

import views.html.*;

import java.util.List;
import static play.libs.Json.toJson;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


    User loggedInUser = null;
    Sale saleInView = null;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        loggedInUser = null;
        return ok(index.render("Your new application is ready."));
    }

    public Result renderHome() {
        return ok(homepage.render());
    }

    /**
     * skeleton to login to the application
     * @return the HTTP response depending if the login was successful or not
     */
    public Result login() {
        User loginUser = Form.form(User.class).bindFromRequest().get();
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();

        //List<User> dbList = User.find().where().eq("username", username).where().eq("password", password).findList();

        User foundUser = JavaApplicationDatabase.attemptLogin(username, password);

        if (foundUser != null) {
            loggedInUser = foundUser;
            return ok(homepage.render());
        } else {
            return ok(index.render("Your new application is ready."));
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

        String[] postAction = request().body().asFormUrlEncoded().get("action");
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

        User registerUser = Form.form(User.class).bindFromRequest().get();
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

    /**
     * renders the my profile page
     * @return the HTTP response
     */
    public Result profile() {
        return ok(profile.render());
    }

    /**
     * skeleton to change the User's name
     * @return the HTTP response
     */
    public Result editName() {

        String[] postAction = request().body().asFormUrlEncoded().get("action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newFirstName = updateUser.getFirstName();
        String newLastName = updateUser.getLastName();

        int result = JavaApplicationDatabase.updateName(loggedInUser, newFirstName, newLastName);
        System.out.println("RESULT: " + result);
        if (result == 1) {

            loggedInUser.setFirstName(newFirstName);
            loggedInUser.setLastName(newLastName);
            return ok(profile.render());

        } else {

            return badRequest("An error occurred while saving");

        }
    }

    /**
     * skeleton to change the User's email
     * @return the HTTP response
     */
    public Result editEmail() {
        String[] postAction = request().body().asFormUrlEncoded().get("action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newEmail = updateUser.getEmail();

        int result = JavaApplicationDatabase.updateEmail(loggedInUser, newEmail);
        System.out.println("RESULT: " + result);
        if (result == 1) {
            loggedInUser.setEmail(newEmail);
            return ok(profile.render());
        } else {
            return badRequest("An error occured while saving");
        }
    }

    /**
     * skeleton to change the User's username
     * @return the HTTP response
     */
    public Result editUsername() {

        String[] postAction = request().body().asFormUrlEncoded().get("action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newUsername = updateUser.getUsername();

        int result = JavaApplicationDatabase.updateUsername(loggedInUser, newUsername);
        System.out.println("RESULT: " + result);
        if (result == 1) {

            loggedInUser.setUsername(newUsername);
            return ok(profile.render());

        } else {

            return badRequest("An error occurred while saving");

        }
    }

    /**
     * skeleton to change the User's password
     * @return the HTTP response
     */
    public Result editPassword() {

        String[] postAction = request().body().asFormUrlEncoded().get("action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newPassword = updateUser.getPassword();

        int result = JavaApplicationDatabase.updatePassword(loggedInUser, newPassword);
        System.out.println("RESULT: " + result);
        if (result == 1) {
            loggedInUser.setPassword(newPassword);
            return ok(profile.render());
        } else {
            return badRequest("An error occured while saving");
        }
    }

    /**
     * shows all sales from all users
     */
    public Result browseScreen() {
        return ok(allsales.render());
    }

    /**
     * adds sale item to user then renders page to edit items on the sale page
     * @return the HTTP response
     */
    public Result addSale() {
        String[] postAction = request().body().asFormUrlEncoded().get("action");
        Sale newSale = Form.form(Sale.class).bindFromRequest().get();
        System.out.println(newSale.getId());
        System.out.println(newSale.getStartTime());
        System.out.println(newSale.getEndTime());
        System.out.println(newSale.getDate());
        System.out.println(newSale.getSeller());
        System.out.println(newSale.getLocation());
        System.out.println(newSale.getDescription());
        newSale.save();
        saleInView = newSale;

        return ok(additem.render());
    }

    public Result saleScreen() {
        return ok(sales.render());
    }

    public Result renderCreateSale() {
        return ok(createsale.render());
    }

    public Result addItem() {
        String[] postAction = request().body().asFormUrlEncoded().get("action");
        Item newItem = Form.form(Item.class).bindFromRequest().get();
        System.out.println(newItem.getName());
        System.out.println(newItem.getListPrice());
        System.out.println(newItem.getBottomPrice());
        System.out.println(newItem.getQuantity());
        System.out.println(newItem.getDescription());
        newItem.setSaleId(saleInView.getId());
        newItem.save();

        return ok(additem.render());
    }

    public Result updateItem() {

        return ok(sales.render());
    }

    public Result searchItemInSale() {

        // ??
        List<Item> returnList = JavaApplicationDatabase.searchItemInSale(saleInView, "name");

        return ok(homepage.render());
    }

    public Result salePage() {
        return ok(salepage.render());
    }

    public Result renderItem() {
        return ok(item.render());
    }

}
