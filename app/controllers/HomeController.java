package controllers;

import javax.inject.Inject;

import com.avaje.ebean.Model;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
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

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {

        return ok(index.render("Your new application is ready."));
    }

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
            return redirect(routes.HomeController.index());
        }
    }

    public Result registration() {
        return ok(registration.render());
    }

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

    public Result completeRegister(Http.Request request) {

        User registerUser = Form.form(User.class).bindFromRequest().get();
        registerUser.save();

        loggedInUser = registerUser;

        return ok(homepage.render());
    }

    public Result cancelRegister(Http.Request resquest) {

        return redirect(routes.HomeController.index());
    }

    public Result cancelShit() {
        return ok(homepage.render());
    }

    public Result profile() {
        return ok(profile.render());
    }

    public Result editName() {

        //List<User> dbList = User.find().where().eq("username", username).where().eq("password", password).findList();
        //RawSql sql = RawSqlBuilder.parse("SELECTß").create();
        //User.find().setRawSql(sql);

        return TODO;
    }

    public Result editEmail() {

        return TODO;
    }

    public Result editUsername() {

        String[] postAction = request().body().asFormUrlEncoded().get("action");
        User updateUser = Form.form(User.class).bindFromRequest().get();
        String newUsername = updateUser.getUsername();

        int result = JavaApplicationDatabase.updateUsername(loggedInUser, newUsername);
        System.out.println("RESULT: " + result);
        if (result == 1) {

            return ok(profile.render());

        } else {

            return badRequest("An error occurred while saving");

        }
    }

    public Result editPassword() {

        return TODO;
    }

}
