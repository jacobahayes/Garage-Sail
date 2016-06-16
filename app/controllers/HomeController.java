package controllers;

import com.avaje.ebean.Model;
import models.User;
import play.data.Form;
import play.mvc.*;

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
        String username;
        String password;
        User loginUser = Form.form(User.class).bindFromRequest().get();
        username = loginUser.getUsername();
        password = loginUser.getPassword();
        int num = User.find().findRowCount();
        System.out.println(num);

        if (username.equals("user") && password.equals("pass")) {
            loggedInUser = loginUser;
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

        System.out.println("User Created");
        User registerUser = Form.form(User.class).bindFromRequest().get();
        registerUser.save();
        System.out.println(registerUser.getFirstName());
        loggedInUser = registerUser;
        return ok(homepage.render());
    }

    public Result cancelRegister(Http.Request resquest) {

        return redirect(routes.HomeController.index());
    }

    public Result editUser() {
        return ok(edituser.render());
    }

    public Result editName() {
        return TODO;
    }

    public Result editPass() {
        return TODO;
    }

}
