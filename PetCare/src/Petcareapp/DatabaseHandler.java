/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Petcareapp;

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
    
    public String allpetid(int userId){
        String query = "SELECT pet_id FROM pets WHERE owner_id = ?";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, userId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int petId = resultSet.getInt("pet_id");
                

                // Append pet information with a line break after each row
                result.append(petId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching pet information.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No pets found for the given user ID.";
        
    }
    
    public String getallpetid(){
        String query = "SELECT pet_id FROM pets";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int petId = resultSet.getInt("pet_id");
                

                // Append pet information with a line break after each row
                result.append(petId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching pet information.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No pets found for the given user ID.";
        
    }
    
    public String getalluserid(){
        String query = "SELECT user_id FROM users";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                

                // Append pet information with a line break after each row
                result.append(userId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching User information.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No user found.";
        
    }
    
    public String allappointmentid(int userId){
        String query = "SELECT appointment_id FROM appointments WHERE user_id = ?";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, userId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("appointment_id");
                

                // Append pet information with a line break after each row
                result.append(appointmentId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching Appointment ID.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No appointment ID.";
        
    }
    
    public String alluserappointmentid(){
        String query = "SELECT appointment_id FROM appointments";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("appointment_id");
                

                // Append pet information with a line break after each row
                result.append(appointmentId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching User Appointment ID.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No Appointment ID found for the given user.";
        
    }
    
    public String allgrommerappointmentid(){
        String query = "SELECT appointment_id FROM appointments where service_type = 'Grooming'";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("appointment_id");
                

                // Append pet information with a line break after each row
                result.append(appointmentId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching Grommer Appointment ID.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No Grommer Appointment ID found.";
        
    }
    
    
    public String allbuyitemid(int userId){
        String query = "SELECT id FROM buy_items WHERE user_id = ?";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, userId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int itemId = resultSet.getInt("id");
                

                // Append pet information with a line break after each row
                result.append(itemId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching Buy Item ID.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No Buy Item ID found.";
        
    }
    
    public String allitemid(){
        String query = "SELECT id FROM item";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int itemId = resultSet.getInt("id");
                

                // Append pet information with a line break after each row
                result.append(itemId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching Item ID.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No Item ID found";
        
    }
    
    public String alltranningid(){
        String query = "SELECT program_id FROM training_programs";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int itemId = resultSet.getInt("program_id");
                

                // Append pet information with a line break after each row
                result.append(itemId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching Tranning ID.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No Tranning ID found.";
        
    }
    
    public String allpolicyid(){
        String query = "SELECT policy_id FROM insurance_policies";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int itemId = resultSet.getInt("policy_id");
                

                // Append policy information with a line break after each row
                result.append(itemId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching policy information.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No Policy ID found.";
        
    }
    
    public String getlogisticsid(){
        String query = "SELECT operation_id FROM logistics_operations";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int itemId = resultSet.getInt("operation_id");
                

                // Append policy information with a line break after each row
                result.append(itemId).append(" "); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching Logistics ID.";
    }
    
    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No Logistics ID found.";
        
    }
    
    
    public String petinfo(int userId) {
    String query = "SELECT pet_id, name, breed, age, weight, gender, medical_history FROM pets WHERE owner_id = ?";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, userId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int petId = resultSet.getInt("pet_id");
                String name = resultSet.getString("name");
                String breed = resultSet.getString("breed");
                int age = resultSet.getInt("age");
                double weight = resultSet.getDouble("weight");
                String gender = resultSet.getString("gender");
                String medicalHistory = resultSet.getString("medical_history");

                // Append pet information with a line break after each row
                result.append("Pet ID: ").append(petId).append("\n")
                      .append("Name: ").append(name).append("\n")
                      .append("Breed: ").append(breed).append("\n")
                      .append("Age: ").append(age).append("\n")
                      .append("Weight: ").append(weight).append("\n")
                      .append("Gender: ").append(gender).append("\n")
                      .append("Medical History: ").append(medicalHistory).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching pet information.";
    }

    // Return result or a message if no rows were found
    return result.length() > 0 ? result.toString() : "No pets found for the given user ID.";
}

    
    public Map<String, Object> getUserRole(String username, String password) {
    Map<String, Object> userDetails = new HashMap<>();
    String query = "SELECT user_id, role FROM users WHERE username = ? AND password = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                userDetails.put("user_id", resultSet.getInt("user_id"));
                userDetails.put("role", resultSet.getString("role"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return userDetails;
    }
    
    public Map<String, Object> getpetinfo(int petid) {
    Map<String, Object> petDetails = new HashMap<>();
    String query = "SELECT name, breed, age, weight, gender, medical_history FROM pets WHERE pet_id = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, petid);
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                petDetails.put("name", resultSet.getString("name"));
                petDetails.put("breed", resultSet.getString("breed"));
                petDetails.put("age", resultSet.getInt("age"));
                petDetails.put("weight", resultSet.getInt("weight"));
                petDetails.put("gender", resultSet.getString("gender"));
                petDetails.put("medical_history", resultSet.getString("medical_history"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return petDetails;
    }
    
    public Map<String, Object> getappointmentinfo(int id) {
    Map<String, Object> petDetails = new HashMap<>();
    String query = "SELECT user_id, service_type, appointment_date, status, notes FROM appointments WHERE appointment_id = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, id);
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                petDetails.put("user_id", resultSet.getInt("user_id"));
                petDetails.put("service_type", resultSet.getString("service_type"));
                petDetails.put("appointment_date", resultSet.getString("appointment_date"));
                petDetails.put("status", resultSet.getString("status"));
                petDetails.put("notes", resultSet.getString("notes"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return petDetails;
    }
    
    public Map<String, Object> getgrommerappointmentinfo() {
    Map<String, Object> petDetails = new HashMap<>();
    String query = "SELECT pet_id, user_id, appointment_date, status, notes FROM appointments WHERE service_type = 'Grooming'";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                petDetails.put("pet_id", resultSet.getInt("pet_id"));
                petDetails.put("user_id", resultSet.getInt("user_id"));
                petDetails.put("appointment_date", resultSet.getString("appointment_date"));
                petDetails.put("status", resultSet.getString("status"));
                petDetails.put("notes", resultSet.getString("notes"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return petDetails;
    }
    
    public Map<String, Object> getaniteminfo(int id) {
    Map<String, Object> petDetails = new HashMap<>();
    String query = "SELECT item_name, item_price FROM item WHERE id = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, id);
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                petDetails.put("item_name", resultSet.getString("item_name"));
                petDetails.put("item_price", resultSet.getInt("item_price"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return petDetails;
    }
    
    public String getallitems(int userId) {
    String query = "SELECT id, item_name, quantity, price FROM buy_items WHERE user_id = ?";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, userId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                String name = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("price");


                // Append pet information with a line break after each row
                result.append("Item ID: ").append(Id).append("\n")
                      .append("Name: ").append(name).append("\n")
                      .append("Quantity: ").append(quantity).append("\n")
                      .append("Price: ").append(price).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching All Items.";
    }
    return result.length() > 0 ? result.toString() : "No Items found.";
    }
    
    public String getallappointments(int userId) {
    String query = "SELECT appointment_id, service_type, appointment_date, status, notes FROM appointments WHERE user_id = ?";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, userId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int Id = resultSet.getInt("appointment_id");
                String name = resultSet.getString("service_type");
                String date = resultSet.getString("appointment_date");
                String status = resultSet.getString("status");
                String notes = resultSet.getString("notes");


                // Append pet information with a line break after each row
                result.append("Appointment ID: ").append(Id).append("\n")
                      .append("Service Type: ").append(name).append("\n")
                      .append("Date: ").append(date).append("\n")
                      .append("Status: ").append(status).append("\n")
                      .append("Notes: ").append(notes).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching All Appointment information.";
    }
    return result.length() > 0 ? result.toString() : "No Appointment information found.";
    }
    
    public String getalluserappointments() {
    String query = "SELECT appointment_id, user_id,  service_type, appointment_date, status, notes FROM appointments";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int Id = resultSet.getInt("appointment_id");
                int userid = resultSet.getInt("user_id");
                String name = resultSet.getString("service_type");
                String date = resultSet.getString("appointment_date");
                String status = resultSet.getString("status");
                String notes = resultSet.getString("notes");


                // Append pet information with a line break after each row
                result.append("Appointment ID: ").append(Id).append("\n")
                      .append("User ID: ").append(userid).append("\n")
                      .append("Service Type: ").append(name).append("\n")
                      .append("Date: ").append(date).append("\n")
                      .append("Status: ").append(status).append("\n")
                      .append("Notes: ").append(notes).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching User Appointment information.";
    }
    return result.length() > 0 ? result.toString() : "No User Appointment information found.";
    }
    
    public String getallgrommerappointments() {
    String query = "SELECT appointment_id, user_id,  appointment_date, status, notes FROM appointments where service_type = 'Grooming'";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {


        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int Id = resultSet.getInt("appointment_id");
                int userid = resultSet.getInt("user_id");
                String date = resultSet.getString("appointment_date");
                String status = resultSet.getString("status");
                String notes = resultSet.getString("notes");


                // Append pet information with a line break after each row
                result.append("Appointment ID: ").append(Id).append("\n")
                      .append("User ID: ").append(userid).append("\n")
                      .append("Date: ").append(date).append("\n")
                      .append("Status: ").append(status).append("\n")
                      .append("Notes: ").append(notes).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error  to fetch Groomer Appointment information.";
    }
    return result.length() > 0 ? result.toString() : "No Groomer Appointment information found.";
    }
    
    
    public String getallitems() {
    String query = "SELECT id, item_name, item_quantity, item_price FROM item";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int Id = resultSet.getInt("id");
                String name = resultSet.getString("item_name");
                int quantity = resultSet.getInt("item_quantity");
                int price = resultSet.getInt("item_price");
                


                // Append pet information with a line break after each row
                result.append("Item ID: ").append(Id).append("\n")
                      .append("Item Name: ").append(name).append("\n")
                      .append("Item Quantity: ").append(quantity).append("\n")
                      .append("Item Price: ").append(price).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching All Item information.";
    }
    return result.length() > 0 ? result.toString() : "No Items information found.";
    }
    
    public String getallpets() {
    String query = "SELECT pet_id, owner_id, name, breed, age, weight, gender, medical_history FROM pets";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int pet_id = resultSet.getInt("pet_id");
                int user_id = resultSet.getInt("owner_id");
                String name = resultSet.getString("name");
                String breed = resultSet.getString("breed");
                int age = resultSet.getInt("age");
                int weight = resultSet.getInt("weight");
                String gender = resultSet.getString("gender");
                String medical_history = resultSet.getString("medical_history");
                


                // Append pet information with a line break after each row
                result.append("Pet ID: ").append(pet_id).append("\n")
                      .append("User ID:").append(user_id).append("\n")
                      .append("Pet Name: ").append(name).append("\n")
                      .append("Pet Breed: ").append(breed).append("\n")
                      .append("Pet Age: ").append(age).append("\n")
                      .append("Pet Weight: ").append(weight).append("\n")
                      .append("Pet Gender: ").append(gender).append("\n")
                      .append("Medical History: ").append(medical_history).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching pet information.";
    }
    return result.length() > 0 ? result.toString() : "No Items found for the given user ID.";
    }
    
    public String getallinsurance() {
    String query = "SELECT policy_id, policy_number, pet_owner_id, pet_id, coverage_amount, premium_amount, policy_start_date, policy_end_date, status, created_at FROM insurance_policies";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int policy_id = resultSet.getInt("policy_id");
                String policy_number = resultSet.getString("policy_number");
                int pet_owner_id = resultSet.getInt("pet_owner_id");
                int pet_id = resultSet.getInt("pet_id");
                int coverage_amount = resultSet.getInt("coverage_amount");
                int premium_amount = resultSet.getInt("premium_amount");
                String policy_start_date = resultSet.getString("policy_start_date");
                String policy_end_date = resultSet.getString("policy_end_date");
                String status = resultSet.getString("status");
                String created_at = resultSet.getString("created_at");
                


                // Append policy information with a line break after each row
                result.append("Policy ID: ").append(policy_id).append("\n")
                      .append("Policy Number:").append(policy_number).append("\n")
                      .append("Pet Owner ID: ").append(pet_owner_id).append("\n")
                      .append("Pet ID: ").append(pet_id).append("\n")
                      .append("Coverage Amount: ").append(coverage_amount).append("\n")
                      .append("Premium Amount: ").append(premium_amount).append("\n")
                      .append("Policy Start Date: ").append(policy_start_date).append("\n")
                      .append("Policy End Date: ").append(policy_end_date).append("\n")
                      .append("Status: ").append(status).append("\n")
                      .append("Created At: ").append(created_at).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching Insurance Policy information.";
    }
    return result.length() > 0 ? result.toString() : "No Insurance Policy information found.";
    }
    
    public String getallusers() {
    String query = "SELECT user_id, username, password, role, email, phone, created_at FROM users";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String created = resultSet.getString("created_at");
                


                // Append pet information with a line break after each row
                result.append("User ID: ").append(user_id).append("\n")
                      .append("UserName:").append(username).append("\n")
                      .append("Password: ").append(password).append("\n")
                      .append("Role: ").append(role).append("\n")
                      .append("Email: ").append(email).append("\n")
                      .append("Phone: ").append(phone).append("\n")
                      .append("Created At: ").append(created).append("\n\n");// Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching user information.";
    }
    return result.length() > 0 ? result.toString() : "No user information found.";
    }
    
    public String getalllogistics() {
    String query = "SELECT operation_id, personnel_name, personnel_email, phone_number, role, task_type, item_description, sender_id, receiver_id, vehicle_details, request_date, delivery_date, status FROM logistics_operations";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String created = resultSet.getString("created_at");
                


                // Append pet information with a line break after each row
                result.append("User ID: ").append(user_id).append("\n")
                      .append("UserName:").append(username).append("\n")
                      .append("Password: ").append(password).append("\n")
                      .append("Role: ").append(role).append("\n")
                      .append("Email: ").append(email).append("\n")
                      .append("Phone: ").append(phone).append("\n")
                      .append("Created At: ").append(created).append("\n\n");// Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching user information.";
    }
    return result.length() > 0 ? result.toString() : "No user information found.";
    }
    
    
    public String getalltranning() {
    String query = "SELECT program_id, title, description, duration_in_weeks, fee, start_date, end_date FROM training_programs";
    StringBuilder result = new StringBuilder();

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int Id = resultSet.getInt("program_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int duration = resultSet.getInt("duration_in_weeks");
                int fee = resultSet.getInt("fee");
                String start_date = resultSet.getString("start_date");
                String end_date = resultSet.getString("end_date");
                


                // Append pet information with a line break after each row
                result.append("ID: ").append(Id).append("\n")
                      .append("Title: ").append(title).append("\n")
                      .append("Description: ").append(description).append("\n")
                      .append("Duration in weeks: ").append(duration).append("\n")
                      .append("Fee: ").append(fee).append("\n")
                      .append("Start Date: ").append(start_date).append("\n")
                      .append("End Date: ").append(end_date).append("\n\n"); // Blank line between rows
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching tranning information.";
    }
    return result.length() > 0 ? result.toString() : "No tranning information found.";
    }
    
    
    public boolean deleteappointment(int appointment_id) {
    String query = "DELETE FROM appointments WHERE appointment_id = ?";  // Query to delete pet by pet_id
    boolean isDeleted = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the pet_id parameter
        preparedStatement.setInt(1, appointment_id);

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        // If one or more rows were affected, the delete was successful
        if (rowsAffected > 0) {
            isDeleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isDeleted;
    }
    
    public boolean deleteuser(int user_id) {
    String query = "DELETE FROM users WHERE user_id = ?";  // Query to delete pet by pet_id
    boolean isDeleted = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the pet_id parameter
        preparedStatement.setInt(1, user_id);

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        // If one or more rows were affected, the delete was successful
        if (rowsAffected > 0) {
            isDeleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isDeleted;
    }
    
    public boolean deletepolicy(int policy_id) {
    String query = "DELETE FROM insurance_policies WHERE policy_id = ?";  // Query to delete pet by pet_id
    boolean isDeleted = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the pet_id parameter
        preparedStatement.setInt(1, policy_id);

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        // If one or more rows were affected, the delete was successful
        if (rowsAffected > 0) {
            isDeleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isDeleted;
    }
    
    public boolean deletelogistics(int operation_id) {
    String query = "DELETE FROM logistics_operations WHERE operation_id = ?";  // Query to delete pet by pet_id
    boolean isDeleted = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the pet_id parameter
        preparedStatement.setInt(1, operation_id);

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        // If one or more rows were affected, the delete was successful
        if (rowsAffected > 0) {
            isDeleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isDeleted;
    }
    
    public boolean deletetranning(int program_id) {
    String query = "DELETE FROM training_programs WHERE program_id = ?";  // Query to delete pet by pet_id
    boolean isDeleted = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the pet_id parameter
        preparedStatement.setInt(1, program_id);

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        // If one or more rows were affected, the delete was successful
        if (rowsAffected > 0) {
            isDeleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isDeleted;
    }
    
    public boolean deletenewitem(int item_id) {
    String query = "DELETE FROM item WHERE id = ?";  // Query to delete pet by pet_id
    boolean isDeleted = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the pet_id parameter
        preparedStatement.setInt(1, item_id);

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        // If one or more rows were affected, the delete was successful
        if (rowsAffected > 0) {
            isDeleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isDeleted;
    }
    
    public boolean deleteitems(int item_id) {
    String query = "DELETE FROM buy_items WHERE id = ?";  // Query to delete pet by pet_id
    boolean isDeleted = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the pet_id parameter
        preparedStatement.setInt(1, item_id);

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        // If one or more rows were affected, the delete was successful
        if (rowsAffected > 0) {
            isDeleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isDeleted;
    }
    
    
    public boolean deletePet(int petId) {
    String query = "DELETE FROM pets WHERE pet_id = ?";  // Query to delete pet by pet_id
    boolean isDeleted = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the pet_id parameter
        preparedStatement.setInt(1, petId);

        // Execute the delete query
        int rowsAffected = preparedStatement.executeUpdate();

        // If one or more rows were affected, the delete was successful
        if (rowsAffected > 0) {
            isDeleted = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isDeleted;
    }
    
    public boolean updatePet(int petId, String name, String breed, int age, double weight, String gender, String medicalHistory) {
    String query = "UPDATE pets SET name = ?, breed = ?, age = ?, weight = ?, gender = ?, medical_history = ? WHERE pet_id = ?";
    boolean isUpdated = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the parameters for the query
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, breed);
        preparedStatement.setInt(3, age);
        preparedStatement.setDouble(4, weight);
        preparedStatement.setString(5, gender);
        preparedStatement.setString(6, medicalHistory);
        preparedStatement.setInt(7, petId);

        // Execute the update query
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the update was successful
        if (rowsAffected > 0) {
            isUpdated = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isUpdated;
    }
    
    public boolean updategrommerappointment(int app_id, String status) {
    String query = "UPDATE appointments SET status = ? WHERE appointment_id = ?";
    boolean isUpdated = false;

    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        // Set the parameters for the query
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, app_id);

        // Execute the update query
        int rowsAffected = preparedStatement.executeUpdate();

        // Check if the update was successful
        if (rowsAffected > 0) {
            isUpdated = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return isUpdated;
    }


    
    public boolean addPet(int ownerId, String name, String breed, int age, double weight, String gender, String medicalHistory) {
        String query = "INSERT INTO pets (owner_id, name, breed, age, weight, gender, medical_history) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean isInserted = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ownerId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, breed);
            preparedStatement.setInt(4, age);
            preparedStatement.setDouble(5, weight);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, medicalHistory);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isInserted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }
    
    public boolean addlogisticoperation(String personnel_name, String personnel_email, String phone_number, String role, String task_type, String item_description, int sender_id, int receiver_id, String vehicle_details, String request_date, String delivery_date, String status) {
        String query = "INSERT INTO logistics_operations (personnel_name, personnel_email, phone_number, role, task_type, item_description, sender_id, receiver_id, vehicle_details, request_date, delivery_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean isInserted = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, personnel_name);
            preparedStatement.setString(2, personnel_email);
            preparedStatement.setString(3, phone_number);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, task_type);
            preparedStatement.setString(6, item_description);
            preparedStatement.setInt(7, sender_id);
            preparedStatement.setInt(8, receiver_id);
            preparedStatement.setString(9, vehicle_details);
            preparedStatement.setString(10, request_date);
            preparedStatement.setString(11, delivery_date);
            preparedStatement.setString(12, status);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isInserted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }
    
    public boolean addinsurancepolicy(String policy_number, int pet_owner_id, int pet_id, int coverage_amount, int premium_amount, String policy_start_date, String policy_end_date, String status) {
        String query = "INSERT INTO insurance_policies (policy_number, pet_owner_id, pet_id, coverage_amount, premium_amount, policy_start_date, policy_end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean isInserted = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, policy_number);
            preparedStatement.setInt(2, pet_owner_id);
            preparedStatement.setInt(3, pet_id);
            preparedStatement.setInt(4, coverage_amount);
            preparedStatement.setInt(5, premium_amount);
            preparedStatement.setString(6, policy_start_date);
            preparedStatement.setString(7, policy_end_date);
            preparedStatement.setString(8, status);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isInserted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }
    
    public boolean addnewbuyitem(String item_name, int user_id, int quantity, int price) {
        String query = "INSERT INTO buy_items (item_name, user_id, quantity, price) VALUES (?, ?, ?, ?)";
        boolean isInserted = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, item_name);
            preparedStatement.setInt(2, user_id);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, price);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isInserted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }
    
    public boolean addtranning(int trannerId, String title, String description, int duration, int fee, String start_date, String end_date) {
        String query = "INSERT INTO training_programs (trainer_id, title, description, duration_in_weeks, fee, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean isInserted = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, trannerId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, duration);
            preparedStatement.setInt(5, fee);
            preparedStatement.setString(6, start_date);
            preparedStatement.setString(7, end_date);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isInserted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }
    
    public boolean addnewitem(String name, int quantity, int price) {
        String query = "INSERT INTO item (item_name, item_quantity, item_price) VALUES (?, ?, ?)";
        boolean isInserted = false;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setInt(3, price);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isInserted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isInserted;
    }



    // Method to close database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
