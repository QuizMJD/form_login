package com.example.form_login.db;

import java.sql.Connection;
import java.sql.DriverManager;
public class Db {
    public  Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "123456");
        } catch (Exception e) {
//            e.printStackTrace();

            //return null;
        }
        return null;
    }

}
