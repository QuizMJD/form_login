package com.example.form_login.db;

import com.example.form_login.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAL extends Db {
    public static final String TABLE_NAME = "users";

    public User getUserByEmailAndPassword(String email, String password) {
        try {
            Connection connection = this.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (Exception e) {
            // Handle exceptions here
            return null;
        }
        return null;
    }
}
