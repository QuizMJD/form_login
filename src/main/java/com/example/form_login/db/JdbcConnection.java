package com.example.form_login.db;

import com.example.form_login.models.User;

import java.sql.*;

public class JdbcConnection {
    private static String users;
    private static final String TABLE_NAME = users ;

    public User getUserByEmailAndPassword(String email, String password) {
        try {
            Connection connection = this.createConnection();
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


    private static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "123456");
    }

    // Method to close the connection and other resources
    private static void closeResources(Connection conn, Statement stm, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert a new brand into the database
    private static int insertBrand(Connection conn, String name) throws SQLException {
        String insertQuery = "INSERT INTO users (username,password) VALUES (?)";
        PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
        insertStatement.setString(1, name);
        return insertStatement.executeUpdate();
    }

    // Method to update the name of a brand based on its ID
    private static int updateBrandName(Connection conn, int id, String newName) throws SQLException {
        String updateQuery = "UPDATE users SET username = ? WHERE id = ?";
        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        updateStatement.setString(1, newName);
        updateStatement.setInt(2, id);
        return updateStatement.executeUpdate();
    }

    // Method to delete a brand based on its ID
    private static int deleteBrandById(Connection conn, int id) throws SQLException {
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
        deleteStatement.setInt(1, id);
        return deleteStatement.executeUpdate();
    }

    // Method to search for a brand by its name
    private static void searchBrandByName(Connection conn, String searchKeyword) throws SQLException {
        String searchQuery = "SELECT * FROM users WHERE username LIKE ?";
        PreparedStatement searchStatement = conn.prepareStatement(searchQuery);
        searchStatement.setString(1, "%" + searchKeyword + "%");
        ResultSet rs = searchStatement.executeQuery();

        // Display search results
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("ID: " + id + ", Name: " + name);
        }
    }


}
