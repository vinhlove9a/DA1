/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utility.DBConnect;

/**
 *
 * @author Admin
 */
public class LoginRepo_1 {

     public String checkLogin(String username, String password) {
        String query = "SELECT VaiTro FROM Users WHERE TenDangNhap = ? AND MatKhau = ?";
        
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("VaiTro"); // Return role if found
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if login fails
    }

    public boolean add(String username, String password, String role) {
        String query = "INSERT INTO Users (TenDangNhap, MatKhau, VaiTro) VALUES (?, ?, ?)";

        try ( Connection conn = DBConnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Returns true if the user was successfully registered
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
