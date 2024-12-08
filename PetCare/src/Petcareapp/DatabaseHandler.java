/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petcareapp;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/petcareapp_db";
    private static final String DB_USER = "test";
    private static final String DB_PASSWORD = "test";

    private Connection connection;

    public DatabaseHandler() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database connection failed.");
        }
    }

    // Method to register a user
    public boolean registerUser(String username, String password, String role, String email, String phone) {
        String query = "INSERT INTO users (username, password, role, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Use hashing for passwords in production
            stmt.setString(3, role);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Return true if registration was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to validate login
    public boolean loginUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // In production, compare hashed passwords
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Return true if user exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
