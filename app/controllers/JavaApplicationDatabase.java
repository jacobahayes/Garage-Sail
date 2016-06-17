package controllers;

import javax.inject.Inject;

import play.mvc.*;
import play.db.*;
import java.sql.*;

class JavaApplicationDatabase extends Controller {

    private static DB db;

    @Inject
    public JavaApplicationDatabase(DB db) {
        this.db = db;
    }
    
    static Connection conn = db.getConnection();

    public static void select() {
        try {
            Statement s = conn.createStatement();
            String str = "SELECT * FROM user";
            ResultSet rs = s.executeQuery(str);
            while (rs.next()) {
                System.out.println(rs.getString("first_name"));
            }
        } catch (Exception e) {
            System.out.println("Stu is real stupid.");
        }
    }
}
