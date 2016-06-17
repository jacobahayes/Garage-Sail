package controllers;

import javax.inject.Inject;

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
}
